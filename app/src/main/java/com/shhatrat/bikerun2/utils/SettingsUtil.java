package com.shhatrat.bikerun2.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.shhatrat.bikerun2.view.fragment.data.DataSetting;

/**
 * Created by szymon on 6/13/17.
 */

public class SettingsUtil {

    public static DataSetting getSpeedSetting(String key, Context c) {
        DataSetting ds = new DataSetting();
        ds.setUnitType(getStringSettings(key + "_unit", c));
        ds.setAccurancy(getStringSettings(key + "_accurancy", c));
        ds.setAuto(getBoolSettings(key + "_auto", c));
        return ds;
    }

    private static String getStringSettings(String key, Context c) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(c);
        String syncConnPref = sharedPref.getString(key, "");
        if (!syncConnPref.isEmpty())
            return syncConnPref;
        else
            return sharedPref.getString(key + "_def", "");
    }

    private static Boolean getBoolSettings(String key, Context c) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(c);
        Boolean syncConnPref = sharedPref.getBoolean(key, false);
        return syncConnPref;
    }
}
