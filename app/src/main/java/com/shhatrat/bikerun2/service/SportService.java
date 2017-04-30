package com.shhatrat.bikerun2.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.view.activity.MenuActivityView;

import io.reactivex.subjects.PublishSubject;

public class SportService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private LocationManager locationManager;
    private PublishSubject<Location> locationPublishSubject = PublishSubject.create();
    private LocationListener locationListener;
    public Training training;
    public SportService() {
    }

    public void createForegroundNotification()
    {
        Intent notificationIntent = new Intent(this, MenuActivityView.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Training service")
                .setContentIntent(pendingIntent).build();
        startForeground(getResources().getInteger(R.integer.strava_nofitication_tag), notification);
    }

    public void stopForegroundNotification()
    {
        stopForeground(true);
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
        //// TODO: 29.04.17 permissions
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        training = new Training(this, SportType.BIKE, locationPublishSubject);
    }

    private void prepareListener()
    {
     locationListener =   new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                locationPublishSubject.onNext(location);
                Log.d("GPSGPS", "save from GPS");
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
        locationPublishSubject.onComplete();
        locationManager.removeUpdates(locationListener);
    }
    public PublishSubject<Location> getRawGpsSubject()
    {
        return locationPublishSubject;
    }
}
