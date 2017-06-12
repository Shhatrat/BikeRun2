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
                if (speedValueExport((SimpleValue.EnumSpeedType) newType)) return this;
            }

            if (btype instanceof SimpleValue.EnumMetricType) {
                if (metricValueExport((SimpleValue.EnumMetricType) newType)) return this;
            }

            if (btype instanceof SimpleValue.EnumTimeType) {
                if (timeValueExport((SimpleValue.EnumTimeType) newType)) return this;
            }

            return this;
        }

        private boolean timeValueExport(SimpleValue.EnumTimeType newType) {
            switch (newType) {
                case HOURS:
                    if (btype == SimpleValue.EnumTimeType.MINUTES) {
                        bvalue = bvalue / 60;
                        btype = (T) SimpleValue.EnumTimeType.HOURS;
                        return true;
                    }
                    if (btype == SimpleValue.EnumTimeType.SECONDS) {
                        bvalue = bvalue / 3600;
                        btype = (T) SimpleValue.EnumTimeType.HOURS;
                        return true;
                    }
                case MINUTES:
                    if (btype == SimpleValue.EnumTimeType.HOURS) {
                        bvalue = bvalue * 60;
                        btype = (T) SimpleValue.EnumTimeType.MINUTES;
                        return true;
                    }
                    if (btype == SimpleValue.EnumTimeType.SECONDS) {
                        bvalue = bvalue / 60;
                        btype = (T) SimpleValue.EnumTimeType.MINUTES;
                        return true;
                    }
                case SECONDS:
                    if (btype == SimpleValue.EnumTimeType.HOURS) {
                        bvalue = bvalue * 3600;
                        btype = (T) SimpleValue.EnumTimeType.SECONDS;
                        return true;
                        }
                    if (btype == SimpleValue.EnumTimeType.MINUTES) {
                        bvalue = bvalue * 60;
                        btype = (T) SimpleValue.EnumTimeType.SECONDS;
                        return true;
                        }
            }
            return false;
        }

        private boolean speedValueExport(SimpleValue.EnumSpeedType newType) {
            switch (newType) {
                case KM_PER_H:
                    if (btype == SimpleValue.EnumSpeedType.M_PER_S) {
                        bvalue = bvalue * 3.6f;
                        btype = (T) SimpleValue.EnumSpeedType.KM_PER_H;
                        return true;
                    }
                case M_PER_S:
                    if (btype == SimpleValue.EnumSpeedType.KM_PER_H) {
                        bvalue = bvalue * 0.27f;
                        btype = (T) SimpleValue.EnumSpeedType.M_PER_S;
                        return true;
                    }
            }
            return false;
        }

        private boolean metricValueExport(SimpleValue.EnumMetricType newType) {
            switch (newType) {
                case KM:
                    if (btype == SimpleValue.EnumMetricType.M) {
                        bvalue = bvalue * 1000;
                        btype = (T) SimpleValue.EnumMetricType.KM;
                        return true;
                    }
                    if (btype == SimpleValue.EnumMetricType.CM) {
                        bvalue = bvalue * 100000;
                        btype = (T) SimpleValue.EnumMetricType.KM;
                        return true;
                    }
                case M:
                    if (btype == SimpleValue.EnumMetricType.KM) {
                        bvalue = bvalue / 1000;
                        btype = (T) SimpleValue.EnumMetricType.M;
                        return true;
                    }
                    if (btype == SimpleValue.EnumMetricType.CM) {
                        bvalue = bvalue * 100;
                        btype = (T) SimpleValue.EnumMetricType.M;
                        return true;
                    }
                case CM:
                    if (btype == SimpleValue.EnumMetricType.KM) {
                        bvalue = bvalue / 100000;
                        btype = (T) SimpleValue.EnumMetricType.M;
                        return true;
                    }
                    if (btype == SimpleValue.EnumMetricType.M) {
                        bvalue = bvalue * 100;
                        btype = (T) SimpleValue.EnumMetricType.M;
                        return true;
                    }
            }
            return false;
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
