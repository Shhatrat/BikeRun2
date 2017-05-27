package com.shhatrat.bikerun2.view.fragment.container;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szymon on 01.05.17.
 */

public enum EnumContainerType {

    LIST,
    NORMAL;

    public static List<EnumContainerType> getEnumList()
    {
        ArrayList<EnumContainerType> list = new ArrayList<>();
        for(EnumContainerType e : EnumContainerType.values())
        list.add(e);
        return list;
    }
}
