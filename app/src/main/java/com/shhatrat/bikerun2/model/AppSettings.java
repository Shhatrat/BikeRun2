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
    @Property
    String token;
    @Property
    String name;
    @Property
    long userId;

    public long getUserId() {
        reload();
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
        apply();
    }

    public String getName() {
        reload();
        return name;
    }

    public void setName(String name) {
        this.name = name;
        apply();
    }

    public String getToken() {
        reload();
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        apply();
    }

    public Boolean getAccountSaved() {
        reload();
        return isAccountSaved;
    }

    public void setAccountSaved(Boolean accountSaved) {
        isAccountSaved = accountSaved;
        apply();
    }
}
