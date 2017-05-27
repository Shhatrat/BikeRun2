package com.shhatrat.bikerun2.presenter.activity.models;

import com.shhatrat.bikerun2.db.RealmContainer;

import java.util.List;

/**
 * Created by szymon on 26.05.17.
 */

public interface IPrepareContainersPresenter {

    public void loadConfigFromDB();
    public void saveConfigFromScreen(List<RealmContainer> list);
}
