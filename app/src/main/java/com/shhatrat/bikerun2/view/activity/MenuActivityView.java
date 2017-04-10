package com.shhatrat.bikerun2.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.lid.lib.LabelImageView;
import com.shhatrat.bikerun2.BRApplication;
import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.di.NetImpl;
import com.shhatrat.bikerun2.di.UtilImpl;
import com.shhatrat.bikerun2.presenter.activity.IMenuActivityPresenter;
import com.shhatrat.bikerun2.presenter.activity.MenuActivityPresenter;
import com.sweetzpot.stravazpot.authenticaton.ui.StravaLoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;

public class MenuActivityView extends BaseActivity implements IMenuActivityView {

    @BindView(R.id.menu_imagebutton_running)
    ImageButton menuImagebuttonRunning;
    @BindView(R.id.menu_imagebutton_bike)
    ImageButton menuImagebuttonBike;
    @BindView(R.id.menu_labelimagebutton_strava)
    LabelImageView menuLabelimagebuttonStrava;
    @BindView(R.id.menu_imagebutton_strava)
    ImageButton menuImagebuttonStrava;
    @BindView(R.id.cm_imagebutton_trainer)
    ImageButton cmImagebuttonTrainer;
    private IMenuActivityPresenter menuActivityPresenter;

    @Inject
    UtilImpl utils;
    @Inject
    NetImpl net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        ((BRApplication) getApplication()).getComponent().inject(this);
        ((BRApplication) getApplication()).getNetworkComponent().inject(this);
        menuActivityPresenter = new MenuActivityPresenter(this, this, utils, net);
        menuActivityPresenter.refreshImages();
    }

    @OnClick(R.id.menu_labelimagebutton_strava)
    public void clickOnStravaButton()
    {
        startActivity(new Intent(this, StravaActivity.class));
    }

    @OnClick(R.id.menu_imagebutton_strava)
    public void clickOnLoggingStravaButton()
    {
        menuActivityPresenter.logUser();
    }

    @OnClick({R.id.menu_imagebutton_bike, R.id.menu_imagebutton_running, R.id.cm_imagebutton_trainer})
    public void startSportActivity(ImageButton button)
    {
     //todo add Intents
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == getResources().getInteger(R.integer.strava_request_login) && resultCode == RESULT_OK && data != null) {
            String code = data.getStringExtra(StravaLoginActivity.RESULT_CODE);
            menuActivityPresenter.loginResultCode(code);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setOfflineIcon() {
        menuLabelimagebuttonStrava.setVisibility(GONE);
        menuImagebuttonStrava.setVisibility(View.VISIBLE);
    }

    @Override
    public void setLoggedIcon(String name) {
        menuImagebuttonStrava.setVisibility(GONE);
        menuLabelimagebuttonStrava.setLabelText(name);
        menuLabelimagebuttonStrava.setVisibility(View.VISIBLE);
    }
}
