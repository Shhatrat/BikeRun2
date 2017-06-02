package com.shhatrat.bikerun2.view.fragment.data;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.model.SingleData;

import java.util.ArrayList;
import java.util.List;

import static com.shhatrat.bikerun2.R.mipmap.ic_launcher_round;

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

        public static List<SingleData> getSingleListData() {
                ArrayList<SingleData> list = new ArrayList<>();
                list.add(new SingleData("Button start", "This button allow to start", R.drawable.ic_done_white_24dp, BUTTON_START.name()));
                list.add(new SingleData("Button stop", "This button allow to start", R.drawable.common_google_signin_btn_icon_dark_focused, BUTTON_STOP.name()));
                list.add(new SingleData("Button start stop", "This button allow to start", R.drawable.alerter_ic_face, BUTTON_STARTSTOP.name()));
                list.add(new SingleData("Button pause", "This button allow to start", ic_launcher_round, BUTTON_PAUSE.name()));
                return list;
        }
}