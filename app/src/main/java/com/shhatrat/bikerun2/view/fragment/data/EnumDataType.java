package com.shhatrat.bikerun2.view.fragment.data;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.model.SingleData;

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

        DATA_POSITION_LAT,
        DATA_POSITION_LON,
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
                list.add(new SingleData("Start", "Start training", R.drawable.ic_play_arrow_white_24dp, BUTTON_START.name()));
                list.add(new SingleData("Stop", "Stop training", R.drawable.ic_stop_white_24dp, BUTTON_STOP.name()));
                list.add(new SingleData("Start/Stop", "Start/Stop training", R.drawable.ic_replay_white_24dp, BUTTON_STARTSTOP.name()));
                list.add(new SingleData("Pause", "Pause/Unpause training", R.drawable.ic_pause_white_24dp, BUTTON_PAUSE.name()));
                list.add(new SingleData("Left page", "Move to left page", R.drawable.ic_arrow_back_white_24dp, BUTTON_MOVE_LEFT.name()));
                list.add(new SingleData("Right page", "Move to right page", R.drawable.ic_arrow_forward_white_24dp, BUTTON_MOVE_RIGHT.name()));
                list.add(new SingleData("Scan screens", "Move screens automatically", R.drawable.ic_replay_white_24dp, BUTTON_SCAN.name()));
                list.add(new SingleData("Latitude", "Latitude", R.drawable.ic_place_white_24dp, DATA_POSITION_LAT.name()));
                list.add(new SingleData("Longitude", "Latitude", R.drawable.ic_place_white_24dp, DATA_POSITION_LON.name()));
                list.add(new SingleData("Speed", "Instant speed", R.drawable.ic_transfer_within_a_station_white_24dp, DATA_SPEED.name()));
                list.add(new SingleData("Bearing", "Direction of the user", R.drawable.ic_near_me_white_24dp, DATA_BEARING.name()));
                list.add(new SingleData("Accuracy", "GPS accuracy", R.drawable.ic_satellite_white_24dp, DATA_ACCURACY.name()));
                list.add(new SingleData("ALtitude", "Altitude of user", R.drawable.ic_publish_white_24dp, DATA_ALTITUDE.name()));
                list.add(new SingleData("Time", "Time from GPS", R.drawable.ic_av_timer_white_24dp, DATA_TIME.name()));
                list.add(new SingleData("AVG speed", "Avg speed of training", R.drawable.ic_transfer_within_a_station_white_24dp, DATA_AVG_SPEED.name()));
                list.add(new SingleData("Disance", "Distance of training", R.drawable.ic_timeline_white_24dp, DATA_DISTANCE.name()));
                list.add(new SingleData("Map", "Google maps", R.drawable.ic_tab_white_24dp, MAP.name()));

                return list;
        }
}