package com.shhatrat.bikerun2.di.components;

import com.shhatrat.bikerun2.di.modules.NetworkModule;
import com.shhatrat.bikerun2.view.activity.MenuActivityView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by szymon on 09.04.17.
 */
@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    public void inject(MenuActivityView menuActivityView);
}