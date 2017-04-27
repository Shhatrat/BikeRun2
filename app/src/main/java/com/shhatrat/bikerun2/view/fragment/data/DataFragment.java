package com.shhatrat.bikerun2.view.fragment.data;


import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.view.fragment.DataType;
import com.shhatrat.bikerun2.view.fragment.container.IContainer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.grantland.widget.AutofitTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.data_fr_title)
    AutofitTextView dataFrTitle;
    @BindView(R.id.data_fr_value)
    AutofitTextView dataFrValue;

    public DataFragment() {
    }

    public static DataFragment newInstance(DataType buttonType) {
        DataFragment dataFragment = new DataFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataType", buttonType);
        dataFragment.setArguments(bundle);
        return dataFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle b = this.getArguments();
        dataType = (DataType) b.get("dataType");
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
        unbinder.unbind();
    }

    void prepareField() {
        IContainer f = (IContainer) getParentFragment();
        f.setDataField();
    }

    private void setPosition(Location l) {
        dataFrTitle.setText("Position"+ l.getTime());
        dataFrValue.setText(l.getLatitude()+"\n"+l.getLongitude());
    }

    private void setSpeed(Location l)
    {
        dataFrTitle.setText("Speed");
        dataFrValue.setText(l.getSpeed()+"km/h");
    }

    private void setBearing(Location l)
    {
        dataFrTitle.setText("Bearing");
        dataFrValue.setText(l.getBearing()+"°");
    }

    private void setAccurancy(Location l)
    {
        dataFrTitle.setText("Accurancy");
        dataFrValue.setText(l.getAccuracy()+"m");
    }

    private void setAltitude(Location l)
    {
        dataFrTitle.setText("Altitude");
        dataFrValue.setText(l.getAltitude()+"m");
    }

    private void setTime(Location l)
    {
        dataFrTitle.setText("Time");
        dataFrValue.setText(l.getTime()+"");
    }

    @Override
    void subscribeData() {
        switch (dataType) {
            case DATA_POSITION:
                mService.getL().subscribe(this::setPosition);
                break;
            case DATA_SPEED:
                mService.getL().subscribe(this::setSpeed);
                break;
            case DATA_BEARING:
                mService.getL().subscribe(this::setBearing);
                break;
            case DATA_ACCURACY:
                mService.getL().subscribe(this::setAccurancy);
                break;
            case DATA_ALTITUDE:
                mService.getL().subscribe(this::setAltitude);
                break;
            case DATA_TIME:
                mService.getL().subscribe(this::setTime);
                break;
        }
    }
}
