package com.shhatrat.bikerun2.view.fragment.container;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szymon on 01.05.17.
 */

public enum  EnumContainer {

    LIST,
    NORMAL;

    public static List<EnumContainer> getEnumList()
    {
        ArrayList<EnumContainer> list = new ArrayList<>();
        for(EnumContainer e : EnumContainer.values())
        list.add(e);
        return list;
    }
}
