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
