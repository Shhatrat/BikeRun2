package com.shhatrat.bikerun2.view.fragment.container;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shhatrat.bikerun2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends BaseContainer {


    public ListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        enumContainerType = EnumContainerType.LIST;
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareView();
    }

    @Override
    List<Integer> getListOfIds() {
        List<Integer> list = new ArrayList<>();
        list.add(R.id.fr_list_1);
        list.add(R.id.fr_list_2);
        list.add(R.id.fr_list_3);
        list.add(R.id.fr_list_4);
        list.add(R.id.fr_list_5);
        list.add(R.id.fr_list_6);
        return list;
    }
}
