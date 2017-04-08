package com.shhatrat.bikerun2.di;

import android.content.Context;
import com.github.brunodles.simplepreferences.lib.DaoPreferences;

import javax.inject.Inject;

/**
 * Created by szymon on 08.04.17.
 */

public class UtilImpl {

    @Inject
    public UtilImpl(Context context) {
        c= context;
    }
    Context c;

    public DaoPreferences getEasyPreferences()
    {
        return new DaoPreferences(c);
    }
}
