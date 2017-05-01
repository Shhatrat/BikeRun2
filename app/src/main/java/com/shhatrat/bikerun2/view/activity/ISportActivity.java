package com.shhatrat.bikerun2.view.activity;

import com.shhatrat.bikerun2.adapter.ViewPagerAdapter;

/**
 * Created by szymon on 4/27/17.
 */

public interface ISportActivity {
    public void moveToLeftActivity();
    public void moveToRightActivity();
    public void putNewAdapter(ViewPagerAdapter adapter);
}
