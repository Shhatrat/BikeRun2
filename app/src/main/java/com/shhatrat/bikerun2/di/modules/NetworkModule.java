package com.shhatrat.bikerun2.di.modules;

import android.content.Context;

import com.shhatrat.bikerun2.di.NetImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by szymon on 08.04.17.
 */
@Module
public class NetworkModule {

    private Context context;

    public NetworkModule(Context context) {
        this.context = context;
    }

    @Provides
    Context providesContext() {
        return context;
    }

    @Provides
    @Singleton
    NetImpl provideNet(Context context) {
        return new NetImpl(context);
    }
}
