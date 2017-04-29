package com.shhatrat.bikerun2.service;

import android.content.Context;
import android.location.Location;

import com.shhatrat.bikerun2.db.RealmLocation;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by szymon on 29.04.17.
 */

public class Training {
    
    private Context c;
    private SportType sportType;
    private PublishSubject<Location> locationPublishSubject;
    
    private boolean running=false;
    private boolean pause=false;
    Disposable gpsDisposable;

    public Training(Context c, SportType sportType, PublishSubject<Location> locationPublishSubject) {
        this.c = c;
        this.sportType = sportType;
        this.locationPublishSubject = locationPublishSubject;
    }

    public boolean startTraining()
    {
        if(!running)
        {
            gpsDisposable = locationPublishSubject.subscribe(this::saveRealLocationToDatabase);
            return running;
        }
        else
        return true;
    }

    private void saveRealLocationToDatabase(Location l)
    {
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm realm = Realm.getInstance(config);
        realm.beginTransaction();
        RealmLocation rl = realm.createObject(RealmLocation.class);
        rl.setAccuracy(l.getAccuracy());
        rl.setAltitude(l.getAltitude());
        rl.setBearing(l.getBearing());
        rl.setLatitude(l.getLatitude());
        rl.setLongitude(l.getLongitude());
        rl.setSpeed(l.getSpeed());
        rl.setTime(l.getTime());
        //// TODO: 29.04.17
        realm.commitTransaction();
    }
    
    public boolean pauseTraining()
    {
        //// TODO: 29.04.17  
        pause=!pause;
        return pause;
    }
    
    public boolean stopTraining()
    {
        if(running) {
            gpsDisposable.dispose();
            running = false;
            return false;
        }
        else return true;
    }
}
