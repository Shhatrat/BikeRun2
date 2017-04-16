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
import com.shhatrat.bikerun2.presenter.activity.models.IStravaStatsPresenter;
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

import io.reactivex.Observable;

/**
 * Created by szymon on 4/3/17.
 */

public class MenuActivityPresenter implements IMenuActivityPresenter {

    private BaseActivity baseActivity;
    private IMenuActivityView menuActivityView;
    private UtilImpl util;
    private NetImpl net;
    private String error;

    public MenuActivityPresenter(BaseActivity baseActivity, IMenuActivityView menuActivityView, UtilImpl util, NetImpl net) {
        this.menuActivityView = menuActivityView;
        this.net = net;
        this.util = util;
        this.baseActivity = baseActivity;
        this.error = baseActivity.getString(R.string.LOG_ERROR);
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
        Observable<Athlete> athleteSingle = net.getCurrentAthlete();
        athleteSingle
                .compose(RxLifecycleAndroid.bindActivity(baseActivity.lifecycle()))
                .subscribe(this::downloadStatsAndsaveAthlete, e-> Log.d(error, e.getMessage()));
    }

    private void downloadStatsAndsaveAthlete(Athlete athlete)
    {
        StaticFields.athlete = athlete;
        net.getAthleteStats(athlete.getID())
                .compose(RxLifecycleAndroid.bindActivity(baseActivity.lifecycle()))
                .subscribe(e -> StaticFields.stats = e, e-> Log.d(error, e.getMessage()));
    }

    @Override
    public void loginResultCode(String code) {

        Observable<LoginResult> resultLogin = net.getResultLogin(code);
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
        if(StaticFields.stats!=null){
            List<AthleteDataToStats.AthleteData> data = prepareList(StaticFields.stats, StaticFields.athlete);
            startStatsActivity(data);
        }
        else
        {
            menuActivityView.showInfoSnacky(baseActivity.getString(R.string.downloading__));
            downloadAllDataForUserRequest();
        }
    }

    private void startStatsActivity(List<AthleteDataToStats.AthleteData> data)
    {
        AthleteDataToStats athleteDataToStats = new AthleteDataToStats();
        athleteDataToStats.setListdata(data);
        Bundle bundle = new Bundle();
        bundle.putParcelable(IStravaStatsPresenter.PARCEL_LIST, Parcels.wrap(athleteDataToStats));
        Intent i = new Intent(baseActivity, StravaStatsActivity.class);
        i.putExtras(bundle);
        baseActivity.startActivity(i);
    }

    private void downloadAllDataForUserRequest()
    {
        Observable<Athlete> singleAthlete = net.getCurrentAthlete()
                .compose(RxLifecycleAndroid.bindActivity(baseActivity.lifecycle()));
        net.getAthleteStats(util.getAppSettings().getUserId())
                .zipWith(singleAthlete, this::prepareList)
                .subscribe(this::startStatsActivity, e -> menuActivityView.showErrorSnacky(e));
    }

    private List<AthleteDataToStats.AthleteData> prepareList(Stats s, Athlete a)
    {
        //// TODO: 16.04.17 Fix this list
        List<AthleteDataToStats.AthleteData> list = new ArrayList<>();
        list.add(new AthleteDataToStats.AthleteData(baseActivity.getString(R.string.user), a.getFirstName()+ " " + a.getLastName()));
        list.add(new AthleteDataToStats.AthleteData(baseActivity.getString(R.string.friends_count), a.getFriendCount()+""));
        list.add(new AthleteDataToStats.AthleteData(baseActivity.getString(R.string.followers_count), a.getFollowerCount()+""));
        list.add(new AthleteDataToStats.AthleteData(baseActivity.getString(R.string.rides), s.getAllRideTotals().getCount()+ baseActivity.getString(R.string.rides__distance)+s.getAllRideTotals().getDistance()));
        return list;
    }
}