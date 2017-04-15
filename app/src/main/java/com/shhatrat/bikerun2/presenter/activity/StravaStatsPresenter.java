package com.shhatrat.bikerun2.presenter.activity;

import com.shhatrat.bikerun2.model.AthleteDataToStats;
import com.shhatrat.bikerun2.presenter.activity.models.IStravaStatsPresenter;
import com.shhatrat.bikerun2.view.activity.BaseActivity;
import com.shhatrat.bikerun2.view.activity.models.IStravaStatsActivity;

/**
 * Created by szymon on 15.04.17.
 */

public class StravaStatsPresenter implements IStravaStatsPresenter {
    IStravaStatsActivity stravaStatsActivity;
    BaseActivity baseActivity;

    public StravaStatsPresenter(IStravaStatsActivity stravaStatsActivity, BaseActivity baseActivity) {
        this.stravaStatsActivity = stravaStatsActivity;
        this.baseActivity = baseActivity;
    }

    @Override
    public void getIntentData(AthleteDataToStats data) {
        //for future
        stravaStatsActivity.setDataToAdapter(data);
    }
}
