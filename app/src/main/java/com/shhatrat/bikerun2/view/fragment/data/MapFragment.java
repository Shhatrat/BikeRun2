package com.shhatrat.bikerun2.view.fragment.data;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.shhatrat.bikerun2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends BaseDataFragment implements OnMapReadyCallback {

    public MapFragment() {
    }
    MapView mMapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_map2, container, false);
        mMapView = (MapView) v.findViewById(R.id.mapViewYOLO);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
            mMapView.getMapAsync(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
    }

    @Override
    boolean changeFragment(String tag) {
        //// TODO: 27.05.17
        return true;
    }

    @Override
    void subscribeData() {
        //// TODO: 27.05.17  
    }
}
