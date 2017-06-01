package com.shhatrat.bikerun2.presenter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.adapter.ViewPagerAdapter;
import com.shhatrat.bikerun2.db.NormalContainer;
import com.shhatrat.bikerun2.exception.RealmException;
import com.shhatrat.bikerun2.presenter.activity.models.ISportPresenter;
import com.shhatrat.bikerun2.service.EnumSportType;
import com.shhatrat.bikerun2.utils.RealmUtils;
import com.shhatrat.bikerun2.view.activity.ISportActivity;
import com.shhatrat.bikerun2.view.activity.PrepareContainersActivity;

import java.util.List;

import io.realm.Realm;

import static com.shhatrat.bikerun2.utils.RealmUtils.checkConfAvailable;

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
        appCompatActivity.startActivityForResult(i, appCompatActivity.getResources().getInteger(R.integer.config_screen)    );
    }

    public void prepareScreenConfiguration()
    {
        prepareNewScreenConfiguration(enumSportType);
    }

    @Override
    public void prepareScreenConfigurationFromIntent() {
        if(checkConfAvailable(realm, enumSportType))
            prepareScreenFromConfig(enumSportType);
        else
            appCompatActivity.finish();
    }

    private void prepareScreenFromConfig(EnumSportType enumSportType) {
        try {
            List<NormalContainer> list = RealmUtils.prepareNormalContainerFromRealm(RealmUtils.getContainerList(realm, enumSportType));
            sportActivity.putNewAdapter(new ViewPagerAdapter(appCompatActivity.getSupportFragmentManager(), list));
        } catch (RealmException e) {
            e.printStackTrace();
        }
        //// TODO: 26.05.17  
    }
}