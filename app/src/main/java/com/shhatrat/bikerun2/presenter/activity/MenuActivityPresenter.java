package com.shhatrat.bikerun2.presenter.activity;

import android.app.Activity;
import android.content.Intent;
import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.view.activity.IMenuActivityView;
import com.sweetzpot.stravazpot.authenticaton.api.AccessScope;
import com.sweetzpot.stravazpot.authenticaton.api.ApprovalPrompt;
import com.sweetzpot.stravazpot.authenticaton.api.StravaLogin;

/**
 * Created by szymon on 4/3/17.
 */

public class MenuActivityPresenter implements IMenuActivityPresenter {

    Activity activity;
    IMenuActivityView menuActivityView;

    public MenuActivityPresenter(Activity activity, IMenuActivityView menuActivityView) {
        this.activity = activity;
        this.menuActivityView = menuActivityView;
    }

    @Override
    public void refreshImages() {

    }

    @Override
    public void loginResultCode(String code) {

    }

    @Override
    public void logUser() {
        Intent intent = StravaLogin.withContext(activity)
                .withClientID(R.integer.strava_key)
                .withRedirectURI(activity.getString(R.string.strava_redirect_page))
                .withApprovalPrompt(ApprovalPrompt.AUTO)
                .withAccessScope(AccessScope.VIEW_PRIVATE_WRITE)
                .makeIntent();
        activity.startActivityForResult(intent, R.integer.strava_request_login);
    }
}
