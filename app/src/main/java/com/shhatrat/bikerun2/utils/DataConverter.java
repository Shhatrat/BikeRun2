package com.shhatrat.bikerun2.utils;

import com.shhatrat.bikerun2.model.SimpleValue;

public class DataConverter {


    public static class Builder<T extends SimpleValue.BaseEnum> {

        private Float value;
        private T type;
        private SimpleValue simpleValue;

        public Builder(SimpleValue simpleValue) {
            this.value = simpleValue.getNewValue();
            this.type = (T) simpleValue.getNewType();
            this.simpleValue = simpleValue;
        }

//        public Builder(SimpleValue simpleValue)
//        {
//            this.value = simpleValue.getNewValue();
//        }

        private Float getValue() {
            return value;
        }

        private T getType() {
            return type;
        }

        public Builder ChangeSpeed(SimpleValue.EnumSpeedType enumSpeedType)
        {
            if(type instanceof SimpleValue.EnumSpeedType)
            {
                switch ((SimpleValue.EnumSpeedType) type) {
                    case KM_PER_H:
                        if(enumSpeedType == SimpleValue.EnumSpeedType.M_PER_S)
                            value = value * 3.6f;
                            type = (T) SimpleValue.EnumSpeedType.M_PER_S;
                        break;
                    case M_PER_S:
                        if(enumSpeedType == SimpleValue.EnumSpeedType.KM_PER_H)
                            value = value * 0.27f;
                        type = (T) SimpleValue.EnumSpeedType.KM_PER_H;

                        break;
                }
            }
            return this;
        }

        public SimpleValue build()
        {
            simpleValue.setNewType(type);
            simpleValue.setNewValue(value);
           return simpleValue;
        }
    }

//    static class SpeedBuilder{
//
//        private Float value;
//        private SimpleValue.EnumSpeedType type;
//        private SimpleValue simpleValue;
//
//        public SpeedBuilder(SimpleValue simpleValue) {
//            this.value = simpleValue.getNewValue();
//            this.type = (SimpleValue.EnumSpeedType) simpleValue.getNewType();
//            this.simpleValue = simpleValue;
//        }
//
//        private Float getValue() {
//            return value;
//        }
//
//        private SimpleValue.EnumSpeedType getType() {
//            return type;
//        }
//
//        public SpeedBuilder ChangeSpeed(SimpleValue.EnumSpeedType enumSpeedType)
//        {
//                switch ((SimpleValue.EnumSpeedType) type) {
//                    case KM_PER_H:
//                        if(enumSpeedType == SimpleValue.EnumSpeedType.M_PER_S)
//                            value = value * 3.6f;
//                        break;
//                    case M_PER_S:
//                        if(enumSpeedType == SimpleValue.EnumSpeedType.KM_PER_H)
//                            value = value * 0.27f;
//                        break;
//            }
//            return this;
//        }
//
//        public SimpleValue build()
//        {
//            simpleValue.setNewType(type);
//            simpleValue.setNewValue(value);
//            return simpleValue;
//        }
//
//    }

//    public static SimpleValue unitMetricChanger() throws ConverterException
//    {
//        //key = m
//        if(key.equals("km"))
//            return new SimpleValue("km", value, value/100+"");
//        if(key.equals("m"))
//            return new SimpleValue("m", value, value+"");
//        if(key.equals("cm"))
//            return new SimpleValue("cm", value , ""+value*100);
//        throw new ConverterException("wrong key!");
//    }
//
//    public static SimpleValue setAccurancy(SimpleValue val , String key)
//    {
//        int pl = key.length()-2;
//        BigDecimal bd = new BigDecimal(val.getFormatted());
//        bd = bd.setScale(pl, BigDecimal.ROUND_HALF_UP);
//        val.setFormatted(bd.toEngineeringString());
//        return val;
//    }
//
//    public static SimpleValue unitSpeedChanger(SimpleValue sv, String key) throws ConverterException
//    {
//        //key = m/s
//        if(key.equals("km/h"))
//        {
//            sv.setFormatted(Float.parseFloat(sv.getFormatted()) * 3.6+"");
//            return new SimpleValue("km/h", (Float.parseFloat(sv.getFormatted()) * 3.6f));
//        }
//        if(key.equals("m/s"))
//            sv.setFormatted(Float.parseFloat(sv.getFormatted()) * 3.6+"");
//        throw new ConverterException("wrong key!");
//    }
}
