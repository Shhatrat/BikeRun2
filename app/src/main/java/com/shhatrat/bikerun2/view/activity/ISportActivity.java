package com.shhatrat.bikerun2.view.activity;

import com.shhatrat.bikerun2.adapter.ViewPagerAdapter;

/**
 * Created by szymon on 4/27/17.
 */

public interface ISportActivity {
    void moveToLeftActivity();
    void moveToRightActivity();
    void putNewAdapter(ViewPagerAdapter adapter);
}
