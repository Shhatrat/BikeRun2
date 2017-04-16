package com.shhatrat.bikerun2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.shhatrat.bikerun2.view.fragment.MapaFragment;
import com.shhatrat.bikerun2.view.fragment.SomeFragment;

/**
 * Created by szymon on 16.04.17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SomeFragment();
            default:
                return new MapaFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
