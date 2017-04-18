package com.shhatrat.bikerun2.view.fragment.data;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.view.fragment.DataType;

/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonFragment extends BaseFragment{

    public ButtonFragment() {
    }

    public static ButtonFragment newInstance(DataType buttonType)
    {
        ButtonFragment buttonFragment = new ButtonFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataType", buttonType);
        buttonFragment.setArguments(bundle);
        return buttonFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_button, container, false);
    }

    @Override
    void subscribeData() {

    }
}
