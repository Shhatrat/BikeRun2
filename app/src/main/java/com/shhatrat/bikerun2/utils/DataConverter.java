package com.shhatrat.bikerun2.utils;

import com.shhatrat.bikerun2.model.SimpleValue;

import java.math.BigDecimal;

public class DataConverter {


    public static class Builder<T extends SimpleValue.BaseEnum> {

        private Float bvalue;
        private T btype;
        private SimpleValue simpleValue;

        public Builder(SimpleValue simpleValue) {
            this.bvalue = simpleValue.getNewValue();
            this.btype = (T) simpleValue.getNewType();
            this.simpleValue = simpleValue;
        }

        public Builder<T> convertType(T newType)
        {
            if(btype instanceof SimpleValue.EnumSpeedType)
            {
                switch ((SimpleValue.EnumSpeedType) newType) {
                    case KM_PER_H:
                        if(btype == SimpleValue.EnumSpeedType.M_PER_S)
                        {
                            bvalue = bvalue * 3.6f;
                            btype = (T) SimpleValue.EnumSpeedType.KM_PER_H;
                            return this;
                        }
                    case M_PER_S:
                        if(btype == SimpleValue.EnumSpeedType.KM_PER_H) {
                            bvalue = bvalue * 0.27f;
                            btype = (T) SimpleValue.EnumSpeedType.M_PER_S;
                            return this;
                        }
                }
            }

            if (btype instanceof SimpleValue.EnumMetricType) {
                switch ((SimpleValue.EnumMetricType) newType) {
                    case KM:
                        if (btype == SimpleValue.EnumMetricType.M) {
                            bvalue = bvalue * 1000;
                            btype = (T) SimpleValue.EnumMetricType.KM;
                            return this;
                        }
                        if (btype == SimpleValue.EnumMetricType.CM) {
                            bvalue = bvalue * 100000;
                            btype = (T) SimpleValue.EnumMetricType.KM;
                            return this;
                        }
                    case M:
                        if (btype == SimpleValue.EnumMetricType.KM) {
                            bvalue = bvalue / 1000;
                            btype = (T) SimpleValue.EnumMetricType.M;
                            return this;
                        }
                        if (btype == SimpleValue.EnumMetricType.CM) {
                            bvalue = bvalue * 100;
                            btype = (T) SimpleValue.EnumMetricType.M;
                            return this;
                        }
                    case CM:
                        if (btype == SimpleValue.EnumMetricType.KM) {
                            bvalue = bvalue / 100000;
                            btype = (T) SimpleValue.EnumMetricType.M;
                            return this;
                        }
                        if (btype == SimpleValue.EnumMetricType.M) {
                            bvalue = bvalue * 100;
                            btype = (T) SimpleValue.EnumMetricType.M;
                            return this;
                        }
                }
            }

            return this;
        }

        public Builder<T> convertAccurancy(int accurancy)
        {
            BigDecimal bd = new BigDecimal(this.bvalue);
            bd = bd.setScale(accurancy, BigDecimal.ROUND_HALF_UP);
            bvalue = bd.floatValue();
            return this;
        }

        public SimpleValue build()
        {
            simpleValue.setNewType(btype);
            simpleValue.setNewValue(bvalue);
           return simpleValue;
        }
    }


}
