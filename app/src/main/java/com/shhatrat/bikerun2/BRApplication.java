package com.shhatrat.bikerun2;

import android.app.Application;

import com.shhatrat.bikerun2.di.components.ApplicationComponent;
import com.shhatrat.bikerun2.di.components.DaggerApplicationComponent;
import com.shhatrat.bikerun2.di.components.DaggerNetworkComponent;
import com.shhatrat.bikerun2.di.components.NetworkComponent;
import com.shhatrat.bikerun2.di.modules.ApplicationModule;
import com.shhatrat.bikerun2.di.modules.NetworkModule;

/**
 * Created by szymon on 09.04.17.
 */

public class BRApplication extends Application {

    private ApplicationComponent applicationComponent;
    private NetworkComponent networkComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(
                        new ApplicationModule(getApplicationContext()))
                .build();
        networkComponent = DaggerNetworkComponent
                .builder()
                .networkModule(new NetworkModule(getApplicationContext()))
                .build();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
    public NetworkComponent getNetworkComponent() {return networkComponent;
    }
}