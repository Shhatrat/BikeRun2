package com.shhatrat.bikerun2.utils;

import com.shhatrat.bikerun2.model.SimpleValue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by szymon on 08.06.17.
 */
public class DataConverterTest {
    @Test
    public void unitMetricChanger() throws Exception {
        SimpleValue simpleValue = new SimpleValue(10.3245f, SimpleValue.EnumSpeedType.M_PER_S);
//        SimpleValue simpleValue = new SimpleValue(10.3245f, SimpleValue.EnumTimeType.HOURS);

        SimpleValue output = new DataConverter.Builder(simpleValue).ChangeSpeed(SimpleValue.EnumSpeedType.KM_PER_H).build();
        Assert.assertEquals(((SimpleValue.EnumSpeedType)output.getNewType()).name(), SimpleValue.EnumSpeedType.KM_PER_H.name());
    }

}