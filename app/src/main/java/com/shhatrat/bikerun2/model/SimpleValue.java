package com.shhatrat.bikerun2.model;

import com.shhatrat.bikerun2.utils.DataConverter;

/**
 * Created by szymon on 08.06.17.
 */

public class SimpleValue {

    public interface BaseEnum{}

    public enum EnumMetricType implements BaseEnum{
        KM, M, CM
    }

    public enum EnumSpeedType implements BaseEnum{
        KM_PER_H, M_PER_S
    }

    public enum EnumTimeType implements BaseEnum{
        HOURS, MINUTES, SECONDS
    }


    public <T extends BaseEnum> SimpleValue(Float orginalValue, T orginalType) {
        this.orginalValue = orginalValue;
        this.orginalType = orginalType;
        this.newType = orginalType;
        this.newValue = orginalValue;
    }

    public Float getNewValue() {
        return newValue;
    }

    public BaseEnum getNewType() {
        return newType;
    }

    public void setNewValue(Float newValue) {
        this.newValue = newValue;
    }

    public void setNewType(BaseEnum newType) {
        this.newType = newType;
    }

    Float orginalValue;
    BaseEnum orginalType;

    Float newValue;
    BaseEnum newType;
}
