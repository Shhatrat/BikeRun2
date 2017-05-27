package com.shhatrat.bikerun2.presenter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.db.RealmContainer;
import com.shhatrat.bikerun2.presenter.activity.models.ISportPresenter;
import com.shhatrat.bikerun2.service.EnumSportType;
import com.shhatrat.bikerun2.view.activity.ISportActivity;
import com.shhatrat.bikerun2.view.activity.PrepareContainersActivity;

import io.realm.Realm;

import static com.shhatrat.bikerun2.RealmUtils.checkConfAvailable;

/**
 * Created by szymon on 01.05.17.
 */

public class SportPresenter implements ISportPresenter {

    private ISportActivity sportActivity;
    private AppCompatActivity appCompatActivity;
    private EnumSportType enumSportType;
    private Realm realm;
    public SportPresenter(ISportActivity sportActivity, AppCompatActivity appCompatActivity, EnumSportType enumSportType, Realm realm) {
        this.sportActivity = sportActivity;
        this.appCompatActivity = appCompatActivity;
        this.enumSportType = enumSportType;
        this.realm = realm;
    }

    @Override
    public void prepareScreen() {
        if(checkConfAvailable(realm, enumSportType))
            prepareScreenFromConfig(enumSportType);
        else
            prepareNewScreenConfiguration(enumSportType);
    }

    private void prepareNewScreenConfiguration(EnumSportType enumSportType) {
        Intent i  = new Intent(appCompatActivity, PrepareContainersActivity.class);
        i.putExtra(appCompatActivity.getResources().getString(R.string.config_screen), enumSportType);
        appCompatActivity.startActivityForResult(i, appCompatActivity.getResources().getInteger(R.integer.config_screen));
    }

    public void prepareScreenConfiguration()
    {
        prepareNewScreenConfiguration(enumSportType);
    }

    private void prepareScreenFromConfig(EnumSportType enumSportType) {
        //// TODO: 26.05.17  
    }
}