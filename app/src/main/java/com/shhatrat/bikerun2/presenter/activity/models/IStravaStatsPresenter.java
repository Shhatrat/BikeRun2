package com.shhatrat.bikerun2.presenter.activity.models;

import com.shhatrat.bikerun2.model.AthleteDataToStats;

/**
 * Created by szymon on 15.04.17.
 */

public interface IStravaStatsPresenter {
    String PARCEL_LIST="PARCEL_LIST";
    void getIntentData(AthleteDataToStats data);
}
