package com.shhatrat.bikerun2.view.activity;

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
import com.shhatrat.bikerun2.service.SportType;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

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
        SportType sportType = (SportType) getIntent().getSerializableExtra("type");
        sportPresenter = new SportPresenter(this,this,sportType, utils.getRealm());
        sportPresenter.preapreScreen();
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

    @Override
    public void putNewAdapter(ViewPagerAdapter adapter) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
    }
}
