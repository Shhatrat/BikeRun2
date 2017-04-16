package com.shhatrat.bikerun2.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SportService extends Service {
    public SportService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
