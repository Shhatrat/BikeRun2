package com.shhatrat.bikerun2.presenter.activity;

import android.support.v7.app.AppCompatActivity;

import com.shhatrat.bikerun2.db.RealmSportActivity;
import com.shhatrat.bikerun2.presenter.activity.models.ISportPresenter;
import com.shhatrat.bikerun2.service.SportType;
import com.shhatrat.bikerun2.view.activity.ISportActivity;

import io.realm.Realm;

/**
 * Created by szymon on 01.05.17.
 */

public class SportPresenter implements ISportPresenter {

    private ISportActivity sportActivity;
    private AppCompatActivity appCompatActivity;
    private SportType sportType;
    private Realm realm;
    public SportPresenter(ISportActivity sportActivity, AppCompatActivity appCompatActivity, SportType sportType, Realm realm) {
        this.sportActivity = sportActivity;
        this.appCompatActivity = appCompatActivity;
        this.sportType = sportType;
        this.realm = realm;
    }

    @Override
    public void preapreScreen() {
        if(checkConfAvailable(sportType))
        {
            prepareScreenFromConfig(sportType);
        }
        else
        {
            prepareNewScreenConfiguration(sportType);
        }
    }

    private void prepareNewScreenConfiguration(SportType sportType) {

    }

    private void prepareScreenFromConfig(SportType sportType) {

    }

    private boolean checkConfAvailable(SportType sportType)
    {
        RealmSportActivity result = realm.where(RealmSportActivity.class)
                .like("type", sportType.name())
                .findFirst();
        return result!=null;
    }
}