package com.shhatrat.bikerun2.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.annimon.stream.Stream;
import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.adapter.SportsViewPagerAdapter;
import com.shhatrat.bikerun2.db.NormalContainer;
import com.shhatrat.bikerun2.exception.ListException;
import com.shhatrat.bikerun2.exception.ParameterException;
import com.shhatrat.bikerun2.service.EnumSportType;
import com.shhatrat.bikerun2.utils.RealmUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class PreapreSingleContainer extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preapre_single_container);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String id = getIntent().getExtras().getString("container_id");
        EnumSportType enumSportType = EnumSportType.valueOf(getIntent().getExtras().getString("sport_type"));
        List<NormalContainer> list = null;
        try {
            list = RealmUtils.prepareNormalContainerFromRealm(RealmUtils.getContainerList(getRealm(), enumSportType));
            list = Stream.of(list).filter(e -> e.getId().equals(id)).toList();
        } catch (ParameterException e) {
            e.printStackTrace();
        } catch (ListException e) {
            e.printStackTrace();
        }
        viewpager.setAdapter(new SportsViewPagerAdapter(getSupportFragmentManager(), list));
    }

    //// TODO: 6/14/17 remove this and inject 
    public Realm getRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        return Realm.getInstance(config);
    }
}
