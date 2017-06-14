package com.shhatrat.bikerun2.view.fragment.data;


import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lb.auto_fit_textview.AutoResizeTextView;
import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.db.RealmLocation;
import com.shhatrat.bikerun2.model.SimpleValue;
import com.shhatrat.bikerun2.utils.DataConverter;
import com.shhatrat.bikerun2.view.fragment.container.IContainer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;
import butterknife.Unbinder;

import static com.shhatrat.bikerun2.utils.SettingsUtil.getSpeedSetting;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends BaseDataFragment {

    Unbinder unbinder;
    @BindView(R.id.data_fr_title)
    AutoResizeTextView dataFrTitle;
    @BindView(R.id.data_fr_value)
    AutoResizeTextView dataFrValue;
    @BindView(R.id.data_fr_recycleview)
    RelativeLayout dataFrRecycleview;

    DataSetting setting;

    @Override
    public void onResume() {
        super.onResume();
        loadSettings();
    }

    private void loadSettings() {
        switch (normalData.getDataType()) {
            case DATA_POSITION:
                break;
            case DATA_SPEED:
//                setting = getSpeedSetting("pref_datafr_speed");
                break;
            case DATA_BEARING:
                break;
            case DATA_ACCURACY:
                setting = getSpeedSetting("pref_datafr_gps_accurancy", this.getContext());
                break;
            case DATA_ALTITUDE:
                break;
            case DATA_TIME:
                break;
            case DATA_AVG_SPEED:
                break;
            case DATA_DISTANCE:
                break;
        }
    }

    public DataFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    void prepareField() {
        IContainer f = (IContainer) getParentFragment();
        f.setDataField(normalData.getFieldName());
    }

    private void setPosition(Location l) {
        dataFrTitle.setText("Position");
        dataFrValue.setText(l.getLatitude() + "\n" + l.getLongitude());
    }

    private void setSpeed(Location l) {
        dataFrTitle.setText("Speed");
        SimpleValue sv = new SimpleValue(l.getSpeed(), SimpleValue.EnumSpeedType.M_PER_S);
        sv = new DataConverter.Builder<SimpleValue.EnumSpeedType>(sv)
                .convertType(SimpleValue.EnumSpeedType.valueOf(setting.getUnitType()))
                .convertAccurancy(Integer.parseInt(setting.getAccurancy()))
                .build();
        dataFrValue.setText(sv.getNewType() + " " + sv.getNewType());
    }

    private void setBearing(Location l) {
        dataFrTitle.setText("Bearing");
        dataFrValue.setText(l.getBearing() + "°");
    }

    private void setAccurancy(Location l) {
        dataFrTitle.setText("Accurancy");
        SimpleValue sv = new SimpleValue(l.getAccuracy(), SimpleValue.EnumMetricType.M);
        sv = new DataConverter.Builder<SimpleValue.EnumMetricType>(sv)
                .convertType(SimpleValue.EnumMetricType.valueOf(setting.getUnitType()))
//                .convertAccurancy(Integer.parseInt(setting.getAccurancy()))
                .build();
        dataFrValue.setText(sv.getNewType() + " " + sv.getNewType());
//        dataFrValue.setText(l.getAccuracy() + "m");
    }

    private void setAltitude(Location l) {
        dataFrTitle.setText("Altitude");
        dataFrValue.setText(l.getAltitude() + "m");
    }

    private void setTime(Location l) {
        dataFrTitle.setText("Time");
        dataFrValue.setText(l.getTime() + "");
    }

    private void setAvgSpeed(RealmLocation rl) {
        dataFrTitle.setText("Avg speed");
        dataFrValue.setText(rl.getAvgSpeed() + "km/h");
    }

    private void setDistance(RealmLocation rl) {
        dataFrTitle.setText("Distance");
        dataFrValue.setText(rl.getDistance() + "m");
    }

    @OnLongClick(R.id.data_fr_recycleview)
    boolean changeFragment() {
        super.changeFragment(normalData.getFieldName());
        return true;
    }

    @Override
    void subscribeData() {
        if(setting==null)
            loadSettings();
        switch (normalData.getDataType()) {
            case DATA_DISTANCE:
                sub = mService.training.getCalculatedSubject().subscribe(this::setDistance);
                break;
            case DATA_AVG_SPEED:
                sub = mService.training.getCalculatedSubject().subscribe(this::setAvgSpeed);
                break;
            case DATA_POSITION:
                sub = mService.getRawGpsSubject().subscribe(this::setPosition);
                break;
            case DATA_SPEED:
                sub = mService.getRawGpsSubject().subscribe(this::setSpeed);
                break;
            case DATA_BEARING:
                sub = mService.getRawGpsSubject().subscribe(this::setBearing);
                break;
            case DATA_ACCURACY:
                sub = mService.getRawGpsSubject().subscribe(this::setAccurancy);
                break;
            case DATA_ALTITUDE:
                sub = mService.getRawGpsSubject().subscribe(this::setAltitude);
                break;
            case DATA_TIME:
                sub = mService.getRawGpsSubject().subscribe(this::setTime);
                break;
        }
        mService.getRawGpsSubject().onNext(new Location(""));
    }
}
