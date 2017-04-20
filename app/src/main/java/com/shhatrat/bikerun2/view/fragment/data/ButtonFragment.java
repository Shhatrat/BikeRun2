package com.shhatrat.bikerun2.view.fragment.data;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.view.fragment.DataType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import info.hoang8f.widget.FButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonFragment extends BaseFragment {

    @BindView(R.id.bfrag_fbutton)
    FButton bfragFbutton;
    Unbinder unbinder;

    public ButtonFragment() {
    }

    public static ButtonFragment newInstance(DataType buttonType) {
        ButtonFragment buttonFragment = new ButtonFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataType", buttonType);
        buttonFragment.setArguments(bundle);
        return buttonFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    void subscribeData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
