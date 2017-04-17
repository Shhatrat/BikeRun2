package com.shhatrat.bikerun2.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.LocationProvider;
import io.nlopez.smartlocation.location.config.LocationParams;
import io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesProvider;
import io.nlopez.smartlocation.location.providers.LocationManagerProvider;
import io.nlopez.smartlocation.location.providers.MultiFallbackProvider;
import io.nlopez.smartlocation.rx.ObservableFactory;
import io.reactivex.Observable;

public class SportService extends Service{

    private final IBinder mBinder = new LocalBinder();

    public SportService() {
    }

    public class LocalBinder extends Binder{
        public SportService getService() {
            return SportService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public Observable<Location> getLocalizationObservable()
    {
        SmartLocation.LocationControl dd = SmartLocation.with(this).location().config(LocationParams.NAVIGATION);
        Log.d("dddd" , "status "+ dd.state().isGpsAvailable());
        Observable<Location> locationObservable = ObservableFactory.from(dd);
        return locationObservable;
    }
}
