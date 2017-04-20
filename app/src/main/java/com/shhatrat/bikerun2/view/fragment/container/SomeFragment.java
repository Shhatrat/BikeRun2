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
import com.shhatrat.bikerun2.view.fragment.DataType;
import com.shhatrat.bikerun2.view.fragment.data.ButtonFragment;
import com.shhatrat.bikerun2.view.fragment.data.DataFragment;
import com.shhatrat.bikerun2.view.fragment.data.MapFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SomeFragment extends Fragment implements IContainer {


    public SomeFragment() {
    }

    @Override
    public void setDataField()
    {
        Log.d("dddd", "JESTEM SOME FRAGMENTEM");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_some, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Fragment childFragment = MapFragment.newInstance(DataType.BUTTON_START);
        Fragment childFragment2 = DataFragment.newInstance(DataType.DATA_POSITION);
        Fragment childFragment3 = DataFragment.newInstance(DataType.DATA_SPEED);
//        Fragment childFragment3 = ButtonFragment.newInstance(DataType.BUTTON_START);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction
                .replace(R.id.parent_fragment_container, childFragment)
                .replace(R.id.parent_fragment_third, childFragment3)
                .replace(R.id.parent_fragment_second, childFragment2).commit();
    }
}
