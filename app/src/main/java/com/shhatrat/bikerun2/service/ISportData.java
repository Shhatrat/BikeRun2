package com.shhatrat.bikerun2.service;

import android.location.Location;

import io.reactivex.Observable;

/**
 * Created by szymon on 16.04.17.
 */

interface ISportData {

    public boolean startService();
    public boolean stopService();
    public Observable<Location> getLocation();
    public Observable<Long> getAvgSpeed();
    public Observable<Long> getDistance();
}
