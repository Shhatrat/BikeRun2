package com.shhatrat.bikerun2.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.lid.lib.LabelImageView;
import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.presenter.activity.IMenuActivityPresenter;
import com.shhatrat.bikerun2.presenter.activity.MenuActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivityView extends AppCompatActivity implements IMenuActivityView {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menuActivityPresenter = new MenuActivityPresenter(this, this);
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

    }

    @Override
    public void setLoggedIcon(String name) {

    }
}
