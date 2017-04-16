package com.shhatrat.bikerun2.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.adapter.StravaStatsAdapter;
import com.shhatrat.bikerun2.model.AthleteDataToStats;
import com.shhatrat.bikerun2.presenter.activity.StravaStatsPresenter;
import com.shhatrat.bikerun2.presenter.activity.models.IStravaStatsPresenter;
import com.shhatrat.bikerun2.view.activity.models.IStravaStatsActivity;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StravaStatsActivity extends BaseActivity implements IStravaStatsActivity {


    @BindView(R.id.strava_stats_recycle_view)
    RecyclerView stravaStatsRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strava_stats);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolbar(toolbar);
        IStravaStatsPresenter stravaStatsPresenter  = new StravaStatsPresenter(this,this);
        stravaStatsPresenter.getIntentData(Parcels.unwrap(getIntent().getParcelableExtra(IStravaStatsPresenter.PARCEL_LIST)));
    }

    @Override
    public void setDataToAdapter(AthleteDataToStats data) {
        stravaStatsRecycleView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        stravaStatsRecycleView.setLayoutManager(mLayoutManager);
        stravaStatsRecycleView.setAdapter(new StravaStatsAdapter(data.getListdata()));
    }
}
