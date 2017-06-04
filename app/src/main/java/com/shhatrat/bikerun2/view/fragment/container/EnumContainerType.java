package com.shhatrat.bikerun2.view.fragment.container;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.model.SingleData;

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

    public static List<SingleData> getContainersList() {
        ArrayList<SingleData> list = new ArrayList<>();
        list.add(new SingleData("List", "6 items vertically", R.drawable.list, LIST.name()));
        list.add(new SingleData("Normal", "4 items", R.drawable.some, NORMAL.name()));
        return list;
    }
}
