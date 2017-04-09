package com.shhatrat.bikerun2.presenter.activity;

import android.content.Intent;
import android.util.Log;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.di.NetImpl;
import com.shhatrat.bikerun2.di.UtilImpl;
import com.shhatrat.bikerun2.view.activity.BaseActivity;
import com.shhatrat.bikerun2.view.activity.IMenuActivityView;
import com.sweetzpot.stravazpot.authenticaton.api.AccessScope;
import com.sweetzpot.stravazpot.authenticaton.api.ApprovalPrompt;
import com.sweetzpot.stravazpot.authenticaton.api.StravaLogin;
import com.sweetzpot.stravazpot.authenticaton.model.LoginResult;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
            //// TODO: 09.04.17  
            menuActivityView.setLoggedIcon("todo");}
        else
            menuActivityView.setOfflineIcon();
    }

    @Override
    public void loginResultCode(String code) {
        Single<LoginResult> resultLogin = net.getStravaApi().getResultLogin(baseActivity.getResources().getInteger(R.integer.strava_app_id), baseActivity.getString(R.string.strava_secret), code);
        resultLogin
                .compose(RxLifecycleAndroid.bindActivity(baseActivity.lifecycle()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(e -> menuActivityView.setLoggedIcon(e.getAthlete().getFirstName()) , throwable -> Log.e("dddd", throwable.toString()));
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
