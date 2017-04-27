package com.shhatrat.bikerun2.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import io.reactivex.subjects.PublishSubject;

public class SportService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private LocationManager locationManager;
    private PublishSubject<Location> last = PublishSubject.create();
    private LocationListener locationListener;
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

    @Override
    public void onCreate() {
        super.onCreate();
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        prepareListener();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, locationListener);
    }

    private void prepareListener()
    {
     locationListener =   new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                last.onNext(location);
            }

             @Override
             public void onStatusChanged(String provider, int status, Bundle extras) {

             }

         @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        last.onComplete();
        locationManager.removeUpdates(locationListener);
    }

    public PublishSubject<Location> getRawGpsSubject()
    {
        return last;
    }
}
