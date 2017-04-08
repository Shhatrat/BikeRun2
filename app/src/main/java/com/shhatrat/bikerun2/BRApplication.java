package com.shhatrat.bikerun2;

import android.app.Application;

import com.shhatrat.bikerun2.di.components.ApplicationComponent;
import com.shhatrat.bikerun2.di.components.DaggerApplicationComponent;
import com.shhatrat.bikerun2.di.modules.ApplicationModule;

/**
 * Created by szymon on 09.04.17.
 */

public class BRApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(
                        new ApplicationModule(getApplicationContext()))
                .build();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

}
