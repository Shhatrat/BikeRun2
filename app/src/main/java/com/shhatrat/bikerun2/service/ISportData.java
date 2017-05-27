package com.shhatrat.bikerun2.service;

import android.location.Location;

import io.reactivex.Observable;

/**
 * Created by szymon on 16.04.17.
 */

interface ISportData {

    boolean startService();
    boolean stopService();
    Observable<Location> getLocation();
    Observable<Long> getAvgSpeed();
    Observable<Long> getDistance();
}
