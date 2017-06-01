package com.shhatrat.bikerun2.view.fragment.container;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.shhatrat.bikerun2.db.NormalContainer;

/**
 * Created by szymon on 6/1/17.
 */

final public class ContainerFactory {

    public static String BUNDLE_KEY = "container";

    public static Fragment getInstance(NormalContainer normalContainer) {
        BaseContainer lf;
        switch (normalContainer.getContainerType()) {
            case LIST:
                lf = new ListFragment();
                lf.setArguments(preapreIntent(normalContainer));
                return lf;
            case NORMAL:
                lf = new NormalFragment();
                lf.setArguments(preapreIntent(normalContainer));
                return lf;
        }
        return null;
    }

    private static Bundle preapreIntent(NormalContainer normalContainer) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_KEY, normalContainer.serialize());
        return bundle;
    }


}
