package com.shhatrat.bikerun2.view.fragment;


import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shhatrat.bikerun2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends BaseFragment {

    Unbinder unbinder;

    public DataFragment() {
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

    @Override
    void subscribeData() {

        Log.d("dddd", "PODLACZONE");
        mService.getLocalizationObservable()
                //.subscribe(e -> Log.d("dddd", e.getLatitude()+" " + e.getAccuracy() +" "+ e.getProvider()));
        .subscribe(new Observer<Location>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("dddd", "onsubsc" + d.isDisposed());
            }

            @Override
            public void onNext(Location e) {
                Log.d("dddd", e.getLatitude()+" " + e.getAccuracy() +" "+ e.getProvider());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("dddd", "onerror " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("dddd", "onComplete");
            }
        });
    }
}
