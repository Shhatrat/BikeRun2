package com.shhatrat.bikerun2.view.fragment.container;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.view.fragment.data.DataFragmentFactory;
import com.shhatrat.bikerun2.view.fragment.data.EnumDataType;

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
    public void setDataField(String tag) {
        //// TODO: 27.05.17  
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Fragment childFragment = DataFragmentFactory.getInstance(EnumDataType.BUTTON_BLANK, "1");
        Fragment childFragment2 = DataFragmentFactory.getInstance(EnumDataType.BUTTON_START, "2");
        Fragment childFragment3 = DataFragmentFactory.getInstance(EnumDataType.BUTTON_START, "3");
        Fragment childFragment4 = DataFragmentFactory.getInstance(EnumDataType.BUTTON_BLANK, "4");
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction
                .replace(R.id.fr_list_1, childFragment)
                .replace(R.id.fr_list_2, childFragment3)
                .replace(R.id.fr_list_3, childFragment4)
                .replace(R.id.fr_list_6, childFragment2).commit();
    }
}
