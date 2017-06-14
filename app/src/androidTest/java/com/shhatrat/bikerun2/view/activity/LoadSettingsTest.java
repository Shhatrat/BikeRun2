package com.shhatrat.bikerun2.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.utils.SettingsUtil;
import com.shhatrat.bikerun2.view.fragment.data.DataSetting;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by szymon on 6/13/17.
 */

@RunWith(AndroidJUnit4.class)
public class LoadSettingsTest {

    @BeforeClass
    public static void setup() {
        PreferenceManager.setDefaultValues(InstrumentationRegistry.getTargetContext(), R.xml.pref_data_fragment, false);
    }

    Context appContext = InstrumentationRegistry.getTargetContext();
    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(appContext);

    @Test
    public void loadPositionFormat() throws Exception {
        String syncConnPref = sharedPref.getString("pref_datafr_scan_value", "");
        Assert.assertEquals(5 + "", syncConnPref);
    }

    @Test
    public void loadDefaultAuto() throws Exception {
        Boolean v = sharedPref.getBoolean("pref_datafr_scan_auto", true);
        Assert.assertEquals(v, Boolean.FALSE);
    }

    @Test
    public void loadDefaultPosition() throws Exception {
        String v = sharedPref.getString("pref_datafr_position_value", "");
        Assert.assertEquals(v, "DDD.DDDDD");
    }

    @Test
    public void loadDefaultSpeedUnitType() throws Exception {
        String v = sharedPref.getString("pref_datafr_speed_unit", "");
        Assert.assertEquals(v, "KM_PER_H");
    }

    @Test
    public void loadDefaultAcurancy() throws Exception {
        String v = sharedPref.getString("pref_datafr_speed_accurancy", "");
        Assert.assertEquals(v, "1");
    }

    @Test
    public void speedSettings() throws Exception {
        DataSetting s = SettingsUtil.getSettings("pref_datafr_gps_accurancy", appContext);
        Assert.assertEquals(s.getAuto(), Boolean.FALSE);
        Assert.assertEquals(s.getAccurancy(), 1 + "");
        Assert.assertEquals(s.getUnitType(), "M");
    }

}
