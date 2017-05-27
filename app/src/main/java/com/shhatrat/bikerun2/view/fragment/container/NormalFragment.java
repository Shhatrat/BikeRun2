package com.shhatrat.bikerun2.view.fragment.container;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.view.fragment.data.EnumDataType;
import com.shhatrat.bikerun2.view.fragment.data.DataFragmentFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class NormalFragment extends BaseContainer implements IContainer {


    public NormalFragment() {
    }

    @Override
    public void setDataField()
    {
        Log.d("dddd", "JESTEM SOME FRAGMENTEM");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        enumContainerType = EnumContainerType.NORMAL;
        return inflater.inflate(R.layout.fragment_some, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Fragment childFragment = DataFragmentFactory.getInstance(EnumDataType.BUTTON_STARTSTOP);
        Fragment childFragment2 = DataFragmentFactory.getInstance(EnumDataType.BUTTON_START);
        Fragment childFragment3 = DataFragmentFactory.getInstance(EnumDataType.DATA_TIME);
        Fragment childFragment4 = DataFragmentFactory.getInstance(EnumDataType.BUTTON_PAUSE);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction
                .replace(R.id.parent_fragment_container, childFragment)
                .replace(R.id.parent_fragment_third, childFragment3)
                .replace(R.id.parent_fragment_second3, childFragment4)
                .replace(R.id.parent_fragment_second, childFragment2).commit();
    }
}
