package com.shhatrat.bikerun2.di.modules;

import android.content.Context;

import com.shhatrat.bikerun2.di.UtilImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by szymon on 08.04.17.
 */
@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    Context providesContext() {
        return context;
    }

    @Provides
    @Singleton
    UtilImpl provideUtils(Context context) {
        return new UtilImpl(context);
    }
}
