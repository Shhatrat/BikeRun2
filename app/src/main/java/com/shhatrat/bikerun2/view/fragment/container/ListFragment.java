package com.shhatrat.bikerun2.view.fragment.container;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shhatrat.bikerun2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends BaseContainer implements IContainer {


    public ListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        enumContainerType = EnumContainerType.LIST;
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void setDataField(String tag) {
        //// TODO: 27.05.17  
    }
}
