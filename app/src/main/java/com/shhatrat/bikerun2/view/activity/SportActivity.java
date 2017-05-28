package com.shhatrat.bikerun2.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.shhatrat.bikerun2.BRApplication;
import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.adapter.ViewPagerAdapter;
import com.shhatrat.bikerun2.di.UtilImpl;
import com.shhatrat.bikerun2.presenter.activity.SportPresenter;
import com.shhatrat.bikerun2.presenter.activity.models.ISportPresenter;
import com.shhatrat.bikerun2.service.EnumSportType;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SportActivity extends AppCompatActivity implements ISportActivity{

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Inject
    UtilImpl utils;
    ISportPresenter sportPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((BRApplication) getApplication()).getComponent().inject(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        EnumSportType enumSportType = (EnumSportType) getIntent().getSerializableExtra(getResources().getString(R.string.sport_type));
        sportPresenter = new SportPresenter(this,this, enumSportType, utils.getRealm());
        sportPresenter.prepareScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sport, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == getResources().getInteger(R.integer.config_screen))
        {
            sportPresenter.prepareScreenConfigurationFromIntent();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_sport_settings) {
            sportPresenter.prepareScreenConfiguration();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void moveToLeftActivity() {
        viewpager.setCurrentItem(viewpager.getCurrentItem()-1);
    }

    @Override
    public void moveToRightActivity() {
        viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
    }

    @Override
    public void putNewAdapter(ViewPagerAdapter adapter) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), new ArrayList<>());
        viewpager.setAdapter(adapter);
    }
}
