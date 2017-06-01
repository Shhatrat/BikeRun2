package com.shhatrat.bikerun2.view.fragment.container;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.shhatrat.bikerun2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NormalFragment extends BaseContainer {


    public NormalFragment() {
    }

//    @Override
//    public void setDataField(String tag)
//    {
//        Log.d("dddd", "JESTEM SOME FRAGMENTEM, tag " + tag);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_some, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FrameLayout fl = (FrameLayout) view.findViewById(R.id.framelayout_some);
        for (int i = 0; i < fl.getChildCount(); i++) {
            Log.d("pripri", "id = " + fl.getChildAt(i).getId());
        }
        prepareView();
    }

    @Override
    List<Integer> getListOfIds() {
        ArrayList<Integer> listOfIds = new ArrayList<>();
        listOfIds.add(R.id.parent_fragment_container);
        listOfIds.add(R.id.parent_fragment_third);
        listOfIds.add(R.id.parent_fragment_second3);
        listOfIds.add(R.id.parent_fragment_second);
        return listOfIds;
    }
}
