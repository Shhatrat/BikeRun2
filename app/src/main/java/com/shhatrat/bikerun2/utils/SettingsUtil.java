package com.shhatrat.bikerun2.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.shhatrat.bikerun2.view.fragment.data.DataSetting;

/**
 * Created by szymon on 6/13/17.
 */

public class SettingsUtil {

    public static DataSetting getSettings(String key, Context c) {
        DataSetting ds = new DataSetting();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(c);
        String type = sharedPref.getString(key + "_unit", "");
        String accurancy = sharedPref.getString(key + "_accurancy", "");
        Boolean auto = sharedPref.getBoolean(key + "_auto", false);
        ds.setUnitType(type);
        ds.setAccurancy(accurancy);
        ds.setAuto(auto);
        return ds;
    }
}
