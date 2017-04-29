package com.shhatrat.bikerun2.view.fragment.data;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.shhatrat.bikerun2.service.SportService;
import com.shhatrat.bikerun2.view.fragment.DataType;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by szymon on 16.04.17.
 */

abstract public class BaseDataFragment extends Fragment {

    SportService mService;
    boolean mBound = false;
    Subject<Boolean> mObservable;
    DataType dataType;

    public void setmBound(boolean val) {
        if(mBound!=val) {
            this.mBound = val;
            mObservable.onNext(val);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mObservable = PublishSubject.create();
        this.mObservable.filter(e -> e).subscribe(ee -> subscribeData(), e -> Log.e("mObservable", e.getMessage()));
    }

    abstract void subscribeData();

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getActivity(), SportService.class);
        getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        Bundle b = this.getArguments();
        dataType = (DataType) b.get("dataType");
    }

    Disposable sub;

    @Override
    public void onPause() {
        super.onPause();
        if(!(sub ==null))
        sub.dispose();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBound) {
            getActivity().unbindService(mConnection);
            mBound = false;
            mObservable.onComplete();
        }
    }


    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            SportService.LocalBinder binder = (SportService.LocalBinder) service;
            mService = binder.getService();
            setmBound(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            setmBound(false);
        }
    };
}