package com.shhatrat.bikerun2.presenter.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.di.NetImpl;
import com.shhatrat.bikerun2.di.UtilImpl;
import com.shhatrat.bikerun2.view.activity.IMenuActivityView;
import com.sweetzpot.stravazpot.authenticaton.api.AccessScope;
import com.sweetzpot.stravazpot.authenticaton.api.ApprovalPrompt;
import com.sweetzpot.stravazpot.authenticaton.api.StravaLogin;
import com.sweetzpot.stravazpot.authenticaton.model.LoginResult;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by szymon on 4/3/17.
 */

public class MenuActivityPresenter implements IMenuActivityPresenter {

    Activity activity;
    IMenuActivityView menuActivityView;
    UtilImpl util;
    NetImpl net;

    public MenuActivityPresenter(Activity activity, IMenuActivityView menuActivityView, UtilImpl util, NetImpl net) {
        this.activity = activity;
        this.menuActivityView = menuActivityView;
        this.net = net;
        this.util = util;
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
        Single<LoginResult> resultLogin = net.getStravaApi().getResultLogin(activity.getResources().getInteger(R.integer.strava_app_id), activity.getString(R.string.strava_secret), code);
        resultLogin.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( e -> menuActivityView.setLoggedIcon(e.getAthlete().getLastName()));
    }

    @Override
    public void logUser() {
        Intent intent = StravaLogin.withContext(activity)
                .withClientID(activity.getResources().getInteger(R.integer.strava_key))
                .withRedirectURI(activity.getString(R.string.strava_redirect_page))
                .withApprovalPrompt(ApprovalPrompt.AUTO)
                .withAccessScope(AccessScope.VIEW_PRIVATE_WRITE)
                .makeIntent();
        activity.startActivityForResult(intent, activity.getResources().getInteger(R.integer.strava_request_login));
    }
}
