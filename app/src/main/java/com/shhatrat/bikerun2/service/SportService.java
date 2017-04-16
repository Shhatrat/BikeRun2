package com.shhatrat.bikerun2.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

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
    public int getRandomNumber() {
        return new Random().nextInt(100);
    }
}
