package com.shhatrat.bikerun2.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by szymon on 18.04.17.
 */

public class RealmLocation extends RealmObject{

    @PrimaryKey
    private int id;
    private double latitude;
    private double longitude;
    private long time;

    public RealmLocation() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
