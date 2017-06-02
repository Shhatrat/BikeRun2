package com.shhatrat.bikerun2.view.fragment.data;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.shhatrat.bikerun2.db.NormalData;

/**
 * Created by szymon on 4/27/17.
 */

public class DataFragmentFactory {

    public static String BUNDLE_KEY = "normalContainer";

    public static Fragment getInstance(NormalData normalData)
    {
        switch (normalData.getDataType()) {
            case BUTTON_START:
                return newButtonInstance(normalData);
            case BUTTON_STOP:
                return newButtonInstance(normalData);
            case BUTTON_STARTSTOP:
                return newButtonInstance(normalData);
            case BUTTON_PAUSE:
                return newButtonInstance(normalData);
            case BUTTON_MOVE_LEFT:
                return newButtonInstance(normalData);
            case BUTTON_MOVE_RIGHT:
                return newButtonInstance(normalData);
            case BUTTON_BLANK:
                return newButtonInstance(normalData);

            case DATA_POSITION:
            case DATA_SPEED:
            case DATA_BEARING:
            case DATA_ACCURACY:
            case DATA_ALTITUDE:
            case DATA_TIME:
            case DATA_AVG_SPEED:
            case DATA_DISTANCE:
                return newDataInstance(normalData);



            case MAP:
                return newMapInstance(normalData);
        }
        return newDataInstance(normalData);
    }

    private static ButtonFragment newButtonInstance(NormalData normalData) {
        ButtonFragment buttonFragment = new ButtonFragment();
        buttonFragment.setArguments(createBundle(normalData));
        return buttonFragment;
    }

    private static DataFragment newDataInstance(NormalData normalData) {
        DataFragment buttonFragment = new DataFragment();
        buttonFragment.setArguments(createBundle(normalData));
        return buttonFragment;
    }

    private static MapFragment newMapInstance(NormalData normalData) {
        MapFragment buttonFragment = new MapFragment();
        buttonFragment.setArguments(createBundle(normalData));
        return buttonFragment;
    }

    private static Bundle createBundle(NormalData normalData)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_KEY, normalData.serialize());
        return bundle;
    }


}
