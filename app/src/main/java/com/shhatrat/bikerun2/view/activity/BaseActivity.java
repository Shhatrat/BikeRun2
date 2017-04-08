package com.shhatrat.bikerun2.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shhatrat.bikerun2.di.UtilImpl;
import com.shhatrat.bikerun2.di.components.ApplicationComponent;
import com.shhatrat.bikerun2.di.components.DaggerApplicationComponent;
import com.shhatrat.bikerun2.di.modules.ApplicationModule;

import javax.inject.Inject;

/**
 * Created by szymon on 08.04.17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


}
