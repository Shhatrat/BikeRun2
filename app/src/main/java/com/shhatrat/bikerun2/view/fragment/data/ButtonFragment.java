package com.shhatrat.bikerun2.view.fragment.data;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.view.activity.SportActivity;
import com.shhatrat.bikerun2.view.fragment.DataType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    @OnClick(R.id.bfrag_fbutton)
    public void click()
    {
        switch (dataType) {
            case BUTTON_START:
                //// TODO: 4/27/17
                break;
            case BUTTON_STOP:
                //// TODO: 4/27/17
                break;
            case BUTTON_MOVE_LEFT:
                ((SportActivity)this.getActivity()).moveToLeftActivity();
                break;
            case BUTTON_MOVE_RIGHT:
                ((SportActivity)this.getActivity()).moveToRightActivity();
                break;
        }
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
        switch (dataType) {
            case BUTTON_START:
                bfragFbutton.setText("START");
                break;
            case BUTTON_STOP:
                bfragFbutton.setText("STOP");
                break;
            case BUTTON_MOVE_LEFT:
                bfragFbutton.setText("LEFT PAGE");
                break;
            case BUTTON_MOVE_RIGHT:
                bfragFbutton.setText("RIGHT PAGE");
                break;
        }
        bfragFbutton.setEnabled(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
