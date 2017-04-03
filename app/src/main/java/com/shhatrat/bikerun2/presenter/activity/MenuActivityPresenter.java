package com.shhatrat.bikerun2.presenter.activity;

import android.app.Activity;

import com.shhatrat.bikerun2.view.activity.IMenuActivityView;

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
}
