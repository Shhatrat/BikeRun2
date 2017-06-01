package com.shhatrat.bikerun2.db;

import com.shhatrat.bikerun2.view.fragment.data.EnumDataType;

/**
 * Created by szymon on 6/1/17.
 */

public class NormalData {
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

    public NormalData() {
    }

    public NormalData(String config, String fieldName, String enumDataType) {
        this.config = config;
        this.fieldName = fieldName;
        this.enumDataType = enumDataType;
    }

    public NormalData(RealmData realmData) {
        this.config = realmData.getConfig();
        this.saveDataType(realmData.getDataType());
        this.fieldName = realmData.getFieldName();
    }
}
