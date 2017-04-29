package com.shhatrat.bikerun2.service;

import android.content.Context;
import android.location.Location;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by szymon on 29.04.17.
 */

public class Training {
    
    private Context c;
    private SportType sportType;
    private PublishSubject<Location> locationPublishSubject;
    
    private boolean running=false;
    private boolean pause=false;

    public Training(Context c, SportType sportType, PublishSubject<Location> locationPublishSubject) {
        this.c = c;
        this.sportType = sportType;
        this.locationPublishSubject = locationPublishSubject;
    }

    public boolean startTraining()
    {
        //// TODO: 29.04.17  
        return !running;
    }
    
    public boolean pauseTraining()
    {
        //// TODO: 29.04.17  
        pause=!pause;
        return pause;
    }
    
    public boolean stopTraining()
    {
        //// TODO: 29.04.17  
        return running;        
    }
}
