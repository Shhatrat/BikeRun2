package com.shhatrat.bikerun2.db;

import com.shhatrat.bikerun2.view.fragment.data.EnumDataType;

import io.realm.RealmObject;

/**
 * Created by szymon on 01.05.17.
 */

public class RealmData extends RealmObject {
    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    private String config;
    private String fieldName;
    private String enumDataType;

    public void saveDataType(EnumDataType val) {
        this.enumDataType = val.toString();
    }

    public EnumDataType getDataType() {
        return EnumDataType.valueOf(enumDataType);
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public RealmData() {
    }

    public RealmData(String config, String fieldName, String enumDataType) {
        this.config = config;
        this.fieldName = fieldName;
        this.enumDataType = enumDataType;
    }

    public RealmData(NormalData realmData) {
        this.config = realmData.getConfig();
        this.saveDataType(realmData.getDataType());
        this.fieldName = realmData.getFieldName();
    }
}