package com.shhatrat.bikerun2.view.fragment.container;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.shhatrat.bikerun2.db.NormalContainer;

/**
 * Created by szymon on 01.05.17.
 */

public abstract class BaseContainer extends Fragment {

    EnumContainerType enumContainerType;
    NormalContainer normalContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = this.getArguments();
        normalContainer = NormalContainer.deserialize(b.getString(ContainerFactory.BUNDLE_KEY));
        enumContainerType = normalContainer.getContainerType();
    }

}





