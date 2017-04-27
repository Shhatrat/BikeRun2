package com.shhatrat.bikerun2.di.components;

import com.shhatrat.bikerun2.di.modules.ApplicationModule;
import com.shhatrat.bikerun2.view.activity.MenuActivityView;
import com.shhatrat.bikerun2.view.activity.SportActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by szymon on 08.04.17.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    public void inject(MenuActivityView menuActivityView);
    public void inject(SportActivity sportActivity);
}