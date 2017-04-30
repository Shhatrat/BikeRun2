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
import com.shhatrat.bikerun2.service.SportService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SportActivity extends AppCompatActivity implements ISportActivity{

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Inject
    UtilImpl utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((BRApplication) getApplication()).getComponent().inject(this);
        if(utils.isMyServiceRunning(SportService.class))
        startService(new Intent(this, SportService.class));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        viewpager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sport, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            openSettings();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSettings()
    {

    }

    @Override
    public void moveToLeftActivity() {
        viewpager.setCurrentItem(viewpager.getCurrentItem()-1);
    }

    @Override
    public void moveToRightActivity() {
        viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
    }
}
