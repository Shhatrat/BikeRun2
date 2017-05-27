package com.shhatrat.bikerun2.presenter.activity;

import com.shhatrat.bikerun2.RealmUtils;
import com.shhatrat.bikerun2.db.RealmContainer;
import com.shhatrat.bikerun2.exception.ParameterException;
import com.shhatrat.bikerun2.exception.RealmException;
import com.shhatrat.bikerun2.presenter.activity.models.IPrepareContainersPresenter;
import com.shhatrat.bikerun2.service.EnumSportType;
import com.shhatrat.bikerun2.view.activity.models.IPrepareContainersActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by szymon on 26.05.17.
 */

public class PrepareContainersPresenter implements IPrepareContainersPresenter {

    private Realm realm;
    private EnumSportType enumSportType;
    private IPrepareContainersActivity prepareContainersActivity;
    public PrepareContainersPresenter(Realm realm, EnumSportType enumSportType, IPrepareContainersActivity prepareContainersActivity) {
        this.realm = realm;
        this.enumSportType = enumSportType;
        this.prepareContainersActivity = prepareContainersActivity;
    }

    @Override
    public void loadConfigFromDB() {
        if(RealmUtils.checkConfAvailable(realm,enumSportType))
            try {
                prepareContainersActivity.preapreRecycleViewData(RealmUtils.getContainerList(realm, enumSportType));
            } catch (RealmException e) {
                //todo
            }
        else
            prepareContainersActivity.preapreRecycleViewData(new ArrayList<>());
    }

    @Override
    public void saveConfigFromScreen(List<RealmContainer> list) {
        try {
            RealmUtils.saveContainerList(realm, list, enumSportType);
        } catch (ParameterException e) {
            //// TODO: 26.05.17
        }
    }
}
