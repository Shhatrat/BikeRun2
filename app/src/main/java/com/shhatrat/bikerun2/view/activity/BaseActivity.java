package com.shhatrat.bikerun2.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by szymon on 08.04.17.
 */

public abstract class BaseActivity extends RxAppCompatActivity{

    void setToolbar(Toolbar toolbar)
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
