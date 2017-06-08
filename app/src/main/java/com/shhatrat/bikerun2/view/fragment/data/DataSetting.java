package com.shhatrat.bikerun2.view.fragment.data;

/**
 * Created by szymon on 08.06.17.
 */

public class DataSetting {

    private String unitType;
    private String accurancy;
    private Boolean auto;

    public DataSetting() {
    }

    public DataSetting(String unitType, String accurancy, Boolean auto) {
        this.unitType = unitType;
        this.accurancy = accurancy;
        this.auto = auto;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getAccurancy() {
        return accurancy;
    }

    public void setAccurancy(String accurancy) {
        this.accurancy = accurancy;
    }

    public Boolean getAuto() {
        return auto;
    }

    public void setAuto(Boolean auto) {
        this.auto = auto;
    }
}
