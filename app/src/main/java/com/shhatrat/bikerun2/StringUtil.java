package com.shhatrat.bikerun2;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by szymon on 27.05.17.
 */

public class StringUtil {

    public static String getRandomString()
    {
        return new BigInteger(130, new Random()).toString(32).substring(0,6);
    }
}
