package com.shhatrat.bikerun2.presenter.activity;

import android.content.Intent;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.di.NetImpl;
import com.shhatrat.bikerun2.di.UtilImpl;
import com.shhatrat.bikerun2.model.StaticFields;
import com.shhatrat.bikerun2.presenter.activity.models.IMenuActivityPresenter;
import com.shhatrat.bikerun2.view.activity.BaseActivity;
import com.shhatrat.bikerun2.view.activity.models.IMenuActivityView;
import com.sweetzpot.stravazpot.athlete.model.Athlete;
import com.sweetzpot.stravazpot.authenticaton.api.AccessScope;
import com.sweetzpot.stravazpot.authenticaton.api.ApprovalPrompt;
import com.sweetzpot.stravazpot.authenticaton.api.StravaLogin;
import com.sweetzpot.stravazpot.authenticaton.model.LoginResult;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import io.reactivex.Single;

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
                .subscribe(this::downloadStatsAndsaveAthlete, e -> e.getCause()); //todo
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
                .subscribe(this::setUp, e -> e.getCause()); //todo
    }

    private void setUp(LoginResult loginResult){
        util.getAppSettings().setToken(loginResult.getToken().toString());
        util.getAppSettings().setAccountSaved(true);
        util.getAppSettings().setName(loginResult.getAthlete().getFirstName());
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
}