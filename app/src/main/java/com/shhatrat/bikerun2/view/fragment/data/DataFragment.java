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

    void d() {
        IContainer f = (IContainer) getParentFragment();
        f.setDataField();
    }

    private void setPosition(Location l) {
        dataFrTitle.setText("Position");
        dataFrValue.setText(l.getLatitude()+"\n"+l.getLongitude());
    }

    private void setSpeed(Location l)
    {
        dataFrTitle.setText("Speed");
        dataFrValue.setText(l.getSpeed()+"km/h");
    }

    @Override
    void subscribeData() {

        switch (dataType) {
            case DATA_POSITION:
                mService.getLocalizationObservable().subscribe(this::setPosition);
                break;
            case DATA_SPEED:
                break;
        }
//        Log.d("ddd", dataType+"");
//        Log.d("ddd", dataType+"");
//        Log.d("dddd", "PODLACZONE");
//        mService.getLocalizationObservable()
//                //.subscribe(e -> Log.d("dddd", e.getLatitude()+" " + e.getAccuracy() +" "+ e.getProvider()));
//        .subscribe(new Observer<Location>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d("dddd", "onsubsc" + d.isDisposed());
//            }
//
//            @Override
//            public void onNext(Location e) {
//                //Log.d("dddd", e.getLatitude()+" " + e.getAccuracy() +" "+ e.getProvider());
//                d();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("dddd", "onerror " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d("dddd", "onComplete");
//            }
//        });
    }
}
