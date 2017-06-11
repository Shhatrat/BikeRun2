package com.shhatrat.bikerun2.model;

/**
 * Created by szymon on 08.06.17.
 */

public class SimpleValue<T extends SimpleValue.BaseEnum> {


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

    public SimpleValue(Float orginalValue, T orginalType) {
        this.orginalValue = orginalValue;
        this.orginalType = orginalType;
        this.newType = orginalType;
        this.newValue = orginalValue;
    }

    public Float getNewValue() {
        return newValue;
    }

    public T getOrginalType() {
        return orginalType;
    }

    public T getNewType() {
        return newType;
    }

    public void setNewType(T newType) {this.newType = newType;}
    public void setNewValue(Float newValue) {
        this.newValue = newValue;
    }

    Float orginalValue;
    T orginalType;

    Float newValue;
    T newType;
}
