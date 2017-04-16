package com.shhatrat.bikerun2.view.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.shhatrat.bikerun2.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import de.mateware.snacky.Snacky;

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

    public void showErrorSnacky(Throwable e)
    {
        Log.w(getString(R.string.LOG_ERROR), e.getMessage());
        Snacky.builder()
                .setActivty(this)
                .centerText()
                .setText(R.string.problem_with_downloading)
                .setTextColor(ContextCompat.getColor(this, R.color.white))
                .error()
                .show();
    }

    public void showInfoSnacky(String message)
    {
        Log.w(getString(R.string.LOG_WARNING), message);
        Snacky.builder()
                .setActivty(this)
                .info()
                .setText(message)
                .show();
    }
}
