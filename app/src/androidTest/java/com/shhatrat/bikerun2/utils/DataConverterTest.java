package com.shhatrat.bikerun2.utils;

import com.shhatrat.bikerun2.model.SimpleValue;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by szymon on 08.06.17.
 */
public class DataConverterTest {

    @Test
    public void accurancyChanger() throws Exception {
        SimpleValue simple2Value = new SimpleValue(10.3245f, SimpleValue.EnumTimeType.MINUTES);
       SimpleValue oo = new  DataConverter.Builder<SimpleValue.EnumMetricType>(simple2Value)
               .convertAccurancy(1).build();
        Assert.assertEquals(oo.getNewValue(), 10.3f);
    }

    @Test
    public void accurancyChangerTo3() throws Exception {
        SimpleValue simple2Value = new SimpleValue(10.3249f, SimpleValue.EnumTimeType.MINUTES);
        SimpleValue oo = new  DataConverter.Builder<SimpleValue.EnumMetricType>(simple2Value)
                .convertAccurancy(3).build();
        Assert.assertEquals(oo.getNewValue(), 10.325f);
    }

    @Test
    public void kmPerHourToMPerSec() throws Exception {
        SimpleValue simple2Value = new SimpleValue(10.0f, SimpleValue.EnumSpeedType.M_PER_S);
        SimpleValue oo = new  DataConverter.Builder<SimpleValue.EnumSpeedType>(simple2Value)
                .convertType(SimpleValue.EnumSpeedType.KM_PER_H).build();
        Assert.assertEquals(oo.getNewValue(), 36.0f);
    }

    @Test
    public void kmPerHourToMPerSecType() throws Exception {
        SimpleValue simple2Value = new SimpleValue(10.0f, SimpleValue.EnumSpeedType.M_PER_S);
        SimpleValue oo = new  DataConverter.Builder<SimpleValue.EnumSpeedType>(simple2Value)
                .convertType(SimpleValue.EnumSpeedType.KM_PER_H).build();
        Assert.assertEquals(((SimpleValue.EnumSpeedType)oo.getNewType()).name(), SimpleValue.EnumSpeedType.KM_PER_H.name());
    }

    @Test
    public void mPerSecToKmPerHourType() throws Exception {
        SimpleValue simple2Value = new SimpleValue(10.0f, SimpleValue.EnumSpeedType.KM_PER_H);
        SimpleValue oo = new  DataConverter.Builder<SimpleValue.EnumSpeedType>(simple2Value)
                .convertType(SimpleValue.EnumSpeedType.M_PER_S).build();
        Assert.assertEquals(((SimpleValue.EnumSpeedType)oo.getNewType()).name(), SimpleValue.EnumSpeedType.M_PER_S.name());
    }
}