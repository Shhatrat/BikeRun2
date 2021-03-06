package com.shhatrat.bikerun2.view.activity;

import com.shhatrat.bikerun2.adapter.SportsViewPagerAdapter;

/**
 * Created by szymon on 4/27/17.
 */

public interface ISportActivity {
    void moveToLeftActivity();
    void moveToRightActivity();

    boolean startStopScan();

    boolean isScanStarted();

    void putNewAdapter(SportsViewPagerAdapter adapter);
}
