package com.shhatrat.bikerun2.view.fragment.data;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.view.activity.SportActivity;
import com.tapadoo.alerter.Alerter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import info.hoang8f.widget.FButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonFragment extends BaseDataFragment {

    @BindView(R.id.bfrag_fbutton)
    FButton bfragFbutton;
    Unbinder unbinder;

    public ButtonFragment() {
    }

    public static ButtonFragment newInstance(EnumDataType buttonType) {
        ButtonFragment buttonFragment = new ButtonFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("enumDataType", buttonType);
        buttonFragment.setArguments(bundle);
        return buttonFragment;
    }

    @OnClick(R.id.bfrag_fbutton)
    public void click()
    {
        switch (enumDataType) {
            case BUTTON_START:
                mService.training.startTraining();
                break;
            case BUTTON_STOP:
                mService.training.stopTraining();
                break;
            case BUTTON_STARTSTOP:
                startStop();
                break;
            case BUTTON_PAUSE:
                pauseUnpause();
                break;
            case BUTTON_MOVE_LEFT:
                ((SportActivity)this.getActivity()).moveToLeftActivity();
                break;
            case BUTTON_MOVE_RIGHT:
                ((SportActivity)this.getActivity()).moveToRightActivity();
                break;
        }
    }

    private void pauseUnpause()
    {
        mService.training.pauseTraining();
        preparePauseUnpause();
    }

    private void startStop()
    {
        if(mService.training.isTrainingRunning()) {
            mService.training.stopTraining();
        }else {
            mService.training.startTraining();
        }
        prepareStartStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    void prepareStartStop()
    {
        if(mService.training.isTrainingRunning())
        {
            bfragFbutton.setButtonColor(ContextCompat.getColor(this.getActivity(), R.color.colorAccent));
            bfragFbutton.setText("STOP");
        }
        else
        {
            bfragFbutton.setButtonColor(ContextCompat.getColor(this.getActivity(), R.color.fbutton_color_green_sea));
            bfragFbutton.setText("START");
        }
    }

    void preparePauseUnpause()
    {
        if(!mService.training.isTrainingRunning())
        {
            bfragFbutton.setButtonColor(ContextCompat.getColor(this.getActivity(), R.color.colorAccent));
            bfragFbutton.setText("PAUSE");
            Alerter.create(this.getActivity())
                    .setBackgroundColor(R.color.colorPrimary)
                    .setTitle("Training is not started")
                    .show();
        }
        else
        {
            if(mService.training.isTrainingPaused())
            {
                bfragFbutton.setButtonColor(ContextCompat.getColor(this.getActivity(), R.color.fbutton_color_green_sea));
                bfragFbutton.setText("UNPAUSE");
            }
            else
            {
                bfragFbutton.setButtonColor(ContextCompat.getColor(this.getActivity(), R.color.colorAccent));
                bfragFbutton.setText("PAUSE");
            }
        }
    }

    @Override
    void subscribeData() {
        switch (enumDataType) {
            case BUTTON_START:
                bfragFbutton.setText("START");
                break;
            case BUTTON_STARTSTOP:
                prepareStartStop();
                break;
            case BUTTON_PAUSE:
                preparePauseUnpause();
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
