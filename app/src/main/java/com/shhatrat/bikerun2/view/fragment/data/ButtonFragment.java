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
import butterknife.OnLongClick;
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
            case BUTTON_BLANK:
                changeFragment(tag);
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
            bfragFbutton.setText(R.string.stop);
        }
        else
        {
            bfragFbutton.setButtonColor(ContextCompat.getColor(this.getActivity(), R.color.fbutton_color_green_sea));
            bfragFbutton.setText(R.string.start);
        }
    }

    void preparePauseUnpause()
    {
        if(!mService.training.isTrainingRunning())
        {
            bfragFbutton.setButtonColor(ContextCompat.getColor(this.getActivity(), R.color.colorAccent));
            bfragFbutton.setText(R.string.pause);
            Alerter.create(this.getActivity())
                    .setBackgroundColor(R.color.colorPrimary)
                    .setTitle(R.string.training_is_not_started)
                    .show();
        }
        else
        {
            if(mService.training.isTrainingPaused())
            {
                bfragFbutton.setButtonColor(ContextCompat.getColor(this.getActivity(), R.color.fbutton_color_green_sea));
                bfragFbutton.setText(R.string.unpause);
            }
            else
            {
                bfragFbutton.setButtonColor(ContextCompat.getColor(this.getActivity(), R.color.colorAccent));
                bfragFbutton.setText(R.string.pause);
            }
        }
    }

    @OnLongClick(R.id.bfrag_fbutton)
    boolean changeFragmentExecutor()
    {
        changeFragment(tag);
        return false;
    }

    @Override
    void subscribeData() {
        switch (enumDataType) {
            case BUTTON_START:
                bfragFbutton.setText(R.string.start);
                break;
            case BUTTON_STARTSTOP:
                prepareStartStop();
                break;
            case BUTTON_PAUSE:
                preparePauseUnpause();
                break;
            case BUTTON_STOP:
                bfragFbutton.setText(R.string.stop);
                break;
            case BUTTON_MOVE_LEFT:
                bfragFbutton.setText(R.string.left_page);
                break;
            case BUTTON_MOVE_RIGHT:
                bfragFbutton.setText(R.string.right_page);
                break;
            case BUTTON_BLANK:
                bfragFbutton.setText(R.string.click_to_set);
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
