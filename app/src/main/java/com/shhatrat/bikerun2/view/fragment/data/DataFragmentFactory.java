package com.shhatrat.bikerun2.view.fragment.data;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by szymon on 4/27/17.
 */

public class DataFragmentFactory {
    
    public static Fragment getInstance(EnumDataType enumDataType, String tag)
    {
        switch (enumDataType) {
            case BUTTON_START:
            return   newButtonInstance(enumDataType, tag);
            case BUTTON_STOP:
                return   newButtonInstance(enumDataType, tag);
            case BUTTON_STARTSTOP:
                return   newButtonInstance(enumDataType, tag);
            case BUTTON_PAUSE:
                return   newButtonInstance(enumDataType, tag);
            case BUTTON_MOVE_LEFT:
                return   newButtonInstance(enumDataType, tag);
            case BUTTON_MOVE_RIGHT:
                return   newButtonInstance(enumDataType, tag);
            case BUTTON_BLANK:
                return   newButtonInstance(enumDataType, tag);

            case DATA_POSITION:
                return   newDataInstance(enumDataType, tag);
            case DATA_SPEED:
                return   newDataInstance(enumDataType, tag);
            case DATA_BEARING:
                return   newDataInstance(enumDataType, tag);
            case DATA_ACCURACY:
                return   newDataInstance(enumDataType, tag);
            case DATA_ALTITUDE:
                return   newDataInstance(enumDataType, tag);
            case DATA_TIME:
                return   newDataInstance(enumDataType, tag);
            case DATA_AVG_SPEED:
                return   newDataInstance(enumDataType, tag);
            case DATA_DISTANCE:
                return   newDataInstance(enumDataType, tag);


            case MAP:
                return newMapInstance(enumDataType, tag);
        }
        return newDataInstance(EnumDataType.DATA_TIME, tag);
    }

    private static ButtonFragment newButtonInstance(EnumDataType buttonType, String tag) {
        ButtonFragment buttonFragment = new ButtonFragment();
        buttonFragment.setArguments(createBundle(buttonType, tag));
        return buttonFragment;
    }

    private static DataFragment newDataInstance(EnumDataType buttonType, String tag) {
        DataFragment buttonFragment = new DataFragment();
        buttonFragment.setArguments(createBundle(buttonType, tag));
        return buttonFragment;
    }

    private static MapFragment newMapInstance(EnumDataType buttonType, String tag) {
        MapFragment buttonFragment = new MapFragment();
        buttonFragment.setArguments(createBundle(buttonType, tag));
        return buttonFragment;
    }

    private static Bundle createBundle(EnumDataType buttonType, String tag)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable("enumDataType", buttonType);
        bundle.putString("tag", tag);
        return bundle;
    }


}
