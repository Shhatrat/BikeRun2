package com.shhatrat.bikerun2.db;

import io.realm.RealmObject;

/**
 * Created by szymon on 01.05.17.
 */

public class DataRealm extends RealmObject {
    private String val;
    private String fieldName;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public DataRealm() {
    }

    public DataRealm(String val, String fieldName) {
        this.val = val;
        this.fieldName = fieldName;
    }
}