package com.shhatrat.bikerun2.view.fragment.container;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shhatrat.bikerun2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NormalFragment extends BaseContainer {


    public NormalFragment() {
    }

    @Override
    public void setDataField(String tag)
    {
        Log.d("dddd", "JESTEM SOME FRAGMENTEM, tag " + tag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_some, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Fragment childFragment = DataFragmentFactory.getInstance(EnumDataType.BUTTON_BLANK, "1");
//        Fragment childFragment2 = DataFragmentFactory.getInstance(EnumDataType.BUTTON_START, "2");
//        Fragment childFragment3 = DataFragmentFactory.getInstance(EnumDataType.BUTTON_START, "3");
//        Fragment childFragment4 = DataFragmentFactory.getInstance(EnumDataType.BUTTON_BLANK, "4");
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction
//                .replace(R.id.parent_fragment_container, childFragment)
//                .replace(R.id.parent_fragment_third, childFragment3)
//                .replace(R.id.parent_fragment_second3, childFragment4)
//                .replace(R.id.parent_fragment_second, childFragment2).commit();
        List<Integer> ids = new ArrayList<>();
        ids.add(R.id.parent_fragment_container);
        ids.add(R.id.parent_fragment_third);
        ids.add(R.id.parent_fragment_second3);
        ids.add(R.id.parent_fragment_second);
        prepareView(ids);
    }
}
