package com.shhatrat.bikerun2.presenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.di.NetImpl;
import com.shhatrat.bikerun2.di.UtilImpl;
import com.shhatrat.bikerun2.model.AthleteDataToStats;
import com.shhatrat.bikerun2.model.StaticFields;
import com.shhatrat.bikerun2.presenter.activity.models.IMenuActivityPresenter;
import com.shhatrat.bikerun2.view.activity.BaseActivity;
import com.shhatrat.bikerun2.view.activity.StravaStatsActivity;
import com.shhatrat.bikerun2.view.activity.models.IMenuActivityView;
import com.sweetzpot.stravazpot.athlete.model.Athlete;
import com.sweetzpot.stravazpot.athlete.model.Stats;
import com.sweetzpot.stravazpot.authenticaton.api.AccessScope;
import com.sweetzpot.stravazpot.authenticaton.api.ApprovalPrompt;
import com.sweetzpot.stravazpot.authenticaton.api.StravaLogin;
import com.sweetzpot.stravazpot.authenticaton.model.LoginResult;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;

/**
 * Created by szymon on 4/3/17.
 */

public class MenuActivityPresenter implements IMenuActivityPresenter {

    private BaseActivity baseActivity;
    private IMenuActivityView menuActivityView;
    private UtilImpl util;
    private NetImpl net;

    public MenuActivityPresenter(BaseActivity baseActivity, IMenuActivityView menuActivityView, UtilImpl util, NetImpl net) {
        this.menuActivityView = menuActivityView;
        this.net = net;
        this.util = util;
        this.baseActivity = baseActivity;
    }

    @Override
    public void refreshImages() {
        if(util.getAppSettings().getAccountSaved())
        {
            menuActivityView.setLoggedIcon(util.getAppSettings().getName());
            downloadAthlete();
        }
        else
            menuActivityView.setOfflineIcon();
    }

    private void downloadAthlete() {
        Single<Athlete> athleteSingle = net.getCurrentAthlete();
        athleteSingle
                .compose(RxLifecycleAndroid.bindActivity(baseActivity.lifecycle()))
                .subscribe(this::downloadStatsAndsaveAthlete);
    }

    private void downloadStatsAndsaveAthlete(Athlete athlete)
    {
        StaticFields.athlete = athlete;
        net.getAthleteStats(athlete.getID())
                .compose(RxLifecycleAndroid.bindActivity(baseActivity.lifecycle()))
                .subscribe(e -> StaticFields.stats = e);
    }

    @Override
    public void loginResultCode(String code) {

        Single<LoginResult> resultLogin = net.getResultLogin(code);
        resultLogin
                .compose(RxLifecycleAndroid.bindActivity(baseActivity.lifecycle()))
                .subscribe(this::setUp, e -> menuActivityView.showErrorSnacky(e));
    }

    private void setUp(LoginResult loginResult){
        util.getAppSettings().setToken(loginResult.getToken().toString());
        util.getAppSettings().setAccountSaved(true);
        util.getAppSettings().setName(loginResult.getAthlete().getFirstName());
        util.getAppSettings().setUserId(loginResult.getAthlete().getID());
        StaticFields.athlete = loginResult.getAthlete();
        refreshImages();
    }

    @Override
    public void logUser() {
        Intent intent = StravaLogin.withContext(baseActivity)
                .withClientID(baseActivity.getResources().getInteger(R.integer.strava_key))
                .withRedirectURI(baseActivity.getString(R.string.strava_redirect_page))
                .withApprovalPrompt(ApprovalPrompt.AUTO)
                .withAccessScope(AccessScope.VIEW_PRIVATE_WRITE)
                .makeIntent();
        baseActivity.startActivityForResult(intent, baseActivity.getResources().getInteger(R.integer.strava_request_login));
    }

    @Override
    public void isStravaReady() {
        List<AthleteDataToStats.AthleteData> list = new ArrayList<>();
        if(StaticFields.stats!=null){
            List<AthleteDataToStats.AthleteData> data = prepareList(StaticFields.stats, StaticFields.athlete);
            startStatsActivity(data);
        }
        else
        {
            menuActivityView.showInfoSnacky("Downloading..");
            downloadAllDataForUserRequest();
        }
    }

    private void startStatsActivity(List<AthleteDataToStats.AthleteData> data)
    {
        AthleteDataToStats athleteDataToStats = new AthleteDataToStats();
        athleteDataToStats.setListdata(data);
        Bundle bundle = new Bundle();
        bundle.putParcelable("example", Parcels.wrap(athleteDataToStats));
        Intent i = new Intent(baseActivity, StravaStatsActivity.class);
        i.putExtras(bundle);
        baseActivity.startActivity(i);
    }

    private void downloadAllDataForUserRequest()
    {
        Single<Athlete> singleAthlete = net.getCurrentAthlete()
                .onErrorResumeNext(new Single<Athlete>() {
                    @Override
                    protected void subscribeActual(SingleObserver<? super Athlete> observer) {
                        Log.e("dd", "dd");
                    }
                })
                .compose(RxLifecycleAndroid.bindActivity(baseActivity.lifecycle()));
            net.getAthleteStats(util.getAppSettings().getUserId())
                .zipWith(singleAthlete, this::prepareList)
                .subscribe(this::startStatsActivity, e -> menuActivityView.showErrorSnacky(e));
    }

    private List<AthleteDataToStats.AthleteData> prepareList(Stats s, Athlete a)
    {
        List<AthleteDataToStats.AthleteData> list = new ArrayList<>();
        list.add(new AthleteDataToStats.AthleteData("User", a.getFirstName()+ " " + a.getLastName()));
        list.add(new AthleteDataToStats.AthleteData("Friends count", a.getFriendCount()+""));
        list.add(new AthleteDataToStats.AthleteData("Followers count", a.getFollowerCount()+""));
        list.add(new AthleteDataToStats.AthleteData("Rides", s.getAllRideTotals().getCount()+ " rides, distance "+s.getAllRideTotals().getDistance()));
        return list;
    }
}