package com.shhatrat.bikerun2.di;

import android.app.ActivityManager;
import android.content.Context;
import com.github.brunodles.simplepreferences.lib.DaoPreferences;
import com.shhatrat.bikerun2.model.AppSettings;

import javax.inject.Inject;

/**
 * Created by szymon on 08.04.17.
 */

public class UtilImpl {

    @Inject
    public UtilImpl(Context context) {
        c= context;
    }
    Context c;

    public DaoPreferences getEasyPreferences()
    {
        return new DaoPreferences(c);
    }

    public AppSettings getAppSettings()
    {
        return new AppSettings(c);
    }

    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
