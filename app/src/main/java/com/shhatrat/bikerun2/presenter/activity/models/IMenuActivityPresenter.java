package com.shhatrat.bikerun2.presenter.activity.models;

/**
 * Created by szymon on 4/3/17.
 */

public interface IMenuActivityPresenter {
    void refreshImages();
    void loginResultCode(String code);
    void logUser();
    void isStravaReady();
}
