package com.shhatrat.bikerun2.service;

import android.location.Location;
import android.util.Log;
import com.shhatrat.bikerun2.db.RealmLocation;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by szymon on 29.04.17.
 */

public class Training {
    
    private SportService sportService;
    private SportType sportType;
    private PublishSubject<Location> locationPublishSubject;
    private PublishSubject<RealmLocation> calculatedPublishSubject;
    private boolean trainingRunning =false;
    private boolean pause=false;
    private Disposable gpsDisposable;
    private RealmConfiguration config = new RealmConfiguration.Builder().build();
    private Realm realm = Realm.getInstance(config);


    public Training(SportService sportService, SportType sportType, PublishSubject<Location> locationPublishSubject) {
        this.sportService = sportService;
        this.sportType = sportType;
        this.locationPublishSubject = locationPublishSubject;
        this.calculatedPublishSubject = PublishSubject.create();
    }

    public boolean startTraining()
    {
        if(!trainingRunning)
        {
            trainingRunning =true;
            sportService.createForegroundNotification();
            startSubscribingGPS(0);
            return trainingRunning;
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
        RealmLocation last;
        try {
            last = realm.where(RealmLocation.class)
                    .findAllSorted("time")
                    .last();
        }catch (Throwable e)
        {
            last=null;
        }
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
        rl.setAvgSpeed(calculateAvgSpeed(last, l, segment));
        rl.setDistance(calculateDistance(last, l, segment));
        //// TODO: 29.04.17
        realm.commitTransaction();
        calculatedPublishSubject.onNext(rl);
    }

    private double calculateDistance(RealmLocation last, Location l, int segment) {
        if(last==null) {
            return 0;
        }

        if(last.getSegment() == segment)
        {
            Location b = new Location("FromDB");
            b.setLongitude(last.getLongitude());
            b.setLatitude(last.getLatitude());
           return last.getDistance()+ l.distanceTo(b);
        }
        else
        {
            return last.getDistance();
        }
    }


    private double calculateAvgSpeed(RealmLocation last, Location l, int segment)
    {
        if(last==null) {
            return l.getSpeed();
        }
        if(last.getSegment()==segment)
        {
            long count = realm.where(RealmLocation.class)
                    .count();
            double avg = last.getAvgSpeed();
            return (avg + l.getSpeed()) / (count + 1);
        }
        else
        {
            return last.getAvgSpeed();
        }
    }

    public PublishSubject<RealmLocation> getCalculatedSubject()
    {
        return calculatedPublishSubject;
    }


    public boolean pauseTraining() {
        pause = !pause;
        if (pause && trainingRunning) {
            gpsDisposable.dispose();
        }
        if (!pause && trainingRunning)
        {
            RealmLocation locations = realm
                    .where(RealmLocation.class)
                    .findAllSorted("segment")
                    .last();
            startSubscribingGPS(locations.getSegment()+1);
        }
        return pause;
    }

    public boolean isTrainingRunning()
    {
        return trainingRunning;
    }

    public boolean isTrainingPaused()
    {
        return pause;
    }

    public boolean stopTraining()
    {
        //// TODO: 29.04.17 clear db, save to GPX
        calculatedPublishSubject.onComplete();
        sportService.stopForegroundNotification();
        if(trainingRunning) {
            gpsDisposable.dispose();
            trainingRunning = false;
            return false;
        }
        else return true;
    }

    private void clearDB()
    {
        realm
                .where(RealmLocation.class)
                .findAll().deleteAllFromRealm();
    }
}
