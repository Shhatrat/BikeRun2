package com.shhatrat.bikerun2.model;

import android.content.Context;
import com.github.brunodles.simplepreferences.lib.ActivePreferences;
import com.github.brunodles.simplepreferences.lib.Property;

/**
 * Created by szymon on 09.04.17.
 */

public class AppSettings extends ActivePreferences {
    public AppSettings(Context context) {
        super(context);
    }

    @Property
    Boolean isAccountSaved;

    public Boolean getAccountSaved() {
        reload();
        return isAccountSaved;
    }

    public void setAccountSaved(Boolean accountSaved) {
        isAccountSaved = accountSaved;
        apply();
    }
}
