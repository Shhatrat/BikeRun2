package com.shhatrat.bikerun2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.shhatrat.bikerun2.db.NormalContainer;
import com.shhatrat.bikerun2.view.fragment.data.MapFragment;
import com.shhatrat.bikerun2.view.fragment.container.NormalFragment;

import java.util.List;

/**
 * Created by szymon on 16.04.17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<NormalContainer> list;

    public ViewPagerAdapter(FragmentManager fm, List<NormalContainer> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NormalFragment();
            default:
                return new MapFragment();
        }
    }

    @Override
    public int getCount() {
//       return list.size();
        return 1;
    }
}
