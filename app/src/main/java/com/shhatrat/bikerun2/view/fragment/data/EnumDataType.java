package com.shhatrat.bikerun2.view.fragment.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szymon on 17.04.17.
 */

public enum EnumDataType {
        BUTTON_START,
        BUTTON_STOP,
        BUTTON_STARTSTOP,
        BUTTON_PAUSE,
        BUTTON_MOVE_LEFT,
        BUTTON_MOVE_RIGHT,
        BUTTON_BLANK,
        BUTTON_SCAN,

        DATA_POSITION,
        DATA_SPEED,
        DATA_BEARING,
        DATA_ACCURACY,
        DATA_ALTITUDE,
        DATA_TIME,
        DATA_AVG_SPEED,
        DATA_DISTANCE,

        MAP;

        public static List<EnumDataType> getEnumList() {
                ArrayList<EnumDataType> list = new ArrayList<>();
                for (EnumDataType e : EnumDataType.values())
                        list.add(e);
                return list;
        }
}