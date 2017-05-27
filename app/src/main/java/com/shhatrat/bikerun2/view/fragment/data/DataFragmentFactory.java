package com.shhatrat.bikerun2.view.fragment.data;

import android.support.v4.app.Fragment;

/**
 * Created by szymon on 4/27/17.
 */

public class DataFragmentFactory {
    
    public static Fragment getInstance(EnumDataType enumDataType)
    {
        switch (enumDataType) {
            case BUTTON_START:
            return   ButtonFragment.newInstance(enumDataType);
            case BUTTON_STOP:
                return   ButtonFragment.newInstance(enumDataType);
            case BUTTON_STARTSTOP:
                return   ButtonFragment.newInstance(enumDataType);
            case BUTTON_PAUSE:
                return   ButtonFragment.newInstance(enumDataType);
            case BUTTON_MOVE_LEFT:
                return   ButtonFragment.newInstance(enumDataType);
            case BUTTON_MOVE_RIGHT:
                return   ButtonFragment.newInstance(enumDataType);
            case DATA_POSITION:
                return   DataFragment.newInstance(enumDataType);
            case DATA_SPEED:
                return   DataFragment.newInstance(enumDataType);
            case DATA_BEARING:
                return   DataFragment.newInstance(enumDataType);
            case DATA_ACCURACY:
                return   DataFragment.newInstance(enumDataType);

            case DATA_ALTITUDE:
                return   DataFragment.newInstance(enumDataType);
            case DATA_TIME:
                return   DataFragment.newInstance(enumDataType);
            case DATA_AVG_SPEED:
                return   DataFragment.newInstance(enumDataType);
            case DATA_DISTANCE:
                return   DataFragment.newInstance(enumDataType);
            case MAP:
                return MapFragment.newInstance(enumDataType);
        }
        return DataFragment.newInstance(EnumDataType.DATA_TIME);
    }
}
