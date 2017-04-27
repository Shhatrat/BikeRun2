package com.shhatrat.bikerun2.view.fragment.data;

import android.support.v4.app.Fragment;

import com.shhatrat.bikerun2.view.fragment.DataType;

/**
 * Created by szymon on 4/27/17.
 */

public class DataFragmentFactory {
    
    public static Fragment getInstance(DataType dataType)
    {
        switch (dataType) {
            case BUTTON_START:
            return   ButtonFragment.newInstance(dataType);
            case BUTTON_STOP:
                return   ButtonFragment.newInstance(dataType);
            case BUTTON_MOVE_LEFT:
                return   ButtonFragment.newInstance(dataType);
            case BUTTON_MOVE_RIGHT:
                return   ButtonFragment.newInstance(dataType);
            case DATA_POSITION:
                return   DataFragment.newInstance(dataType);
            case DATA_SPEED:
                return   DataFragment.newInstance(dataType);
            case DATA_BEARING:
                return   DataFragment.newInstance(dataType);
            case DATA_ACCURACY:
                return   DataFragment.newInstance(dataType);

            case DATA_ALTITUDE:
                return   DataFragment.newInstance(dataType);
            case DATA_TIME:
                return   DataFragment.newInstance(dataType);
            case MAP:
                return MapFragment.newInstance(dataType);
        }
        return DataFragment.newInstance(DataType.DATA_TIME);
    }
}
