package com.shhatrat.bikerun2.service;

import android.location.Location;
import android.util.Log;

import com.shhatrat.bikerun2.db.RealmLocation;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by szymon on 29.04.17.
 */

public class Training {
    
    private SportService sportService;
    private SportType sportType;
    private PublishSubject<Location> locationPublishSubject;
    
    private boolean running=false;
    private boolean pause=false;
    private Disposable gpsDisposable;
    private RealmConfiguration config = new RealmConfiguration.Builder().build();
    private Realm realm = Realm.getInstance(config);


    public Training(SportService sportService, SportType sportType, PublishSubject<Location> locationPublishSubject) {
        this.sportService = sportService;
        this.sportType = sportType;
        this.locationPublishSubject = locationPublishSubject;
    }

    public boolean startTraining()
    {
        if(!running)
        {
            running=true;
            sportService.createForegroundNotification();
            startSubscribingGPS(0);
            return running;
        }
        else
        return true;
    }

    private void startSubscribingGPS(int segment)
    {
        gpsDisposable = locationPublishSubject
                .subscribe(e -> saveRealLocationToDatabase(e,segment));
    }

    private void saveRealLocationToDatabase(Location l, int segment)
    {
        Log.d("GPSGPS", "save from training "+ segment);
        realm.beginTransaction();
        RealmLocation rl = realm.createObject(RealmLocation.class);
        rl.setAccuracy(l.getAccuracy());
        rl.setAltitude(l.getAltitude());
        rl.setBearing(l.getBearing());
        rl.setLatitude(l.getLatitude());
        rl.setLongitude(l.getLongitude());
        rl.setSpeed(l.getSpeed());
        rl.setTime(l.getTime());
        rl.setSegment(segment);
        //// TODO: 29.04.17
        realm.commitTransaction();
    }
    
    public boolean pauseTraining() {
        pause = !pause;
        if (pause && running) {
            gpsDisposable.dispose();
        }
        if (!pause && running)
        {
            RealmLocation locations = realm
                    .where(RealmLocation.class)
                    .findAllSorted("segment")
                    .last();
            startSubscribingGPS(locations.getSegment()+1);
        }
        return pause;
    }
    
    public boolean stopTraining()
    {
        sportService.stopForegroundNotification();
        if(running) {
            gpsDisposable.dispose();
            running = false;
            return false;
        }
        else return true;
    }
}
