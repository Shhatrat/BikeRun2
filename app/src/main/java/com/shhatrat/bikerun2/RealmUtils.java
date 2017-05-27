package com.shhatrat.bikerun2;

import android.util.Log;

import com.shhatrat.bikerun2.db.RealmContainer;
import com.shhatrat.bikerun2.exception.ListException;
import com.shhatrat.bikerun2.exception.ParameterException;
import com.shhatrat.bikerun2.service.EnumSportType;

import java.util.List;
import io.realm.Realm;
import io.realm.RealmResults;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

/**
 * Created by szymon on 26.05.17.
 */

public class RealmUtils {

    public static boolean checkConfAvailable(Realm realm, EnumSportType enumSportType)
    {
        RealmContainer result = realm.where(RealmContainer.class)
                .like("sportType", enumSportType.name())
                .findFirst();
        return result!=null;
    }

    public static List<RealmContainer> getContainerList(Realm realm, EnumSportType enumSportType) throws ParameterException, ListException {
        if(realm==null)
            throw new ParameterException("Realm is null!");
        if( enumSportType == null)
            throw new ParameterException("enumSportType is null!");
        if(!checkConfAvailable(realm,enumSportType))
            throw new ListException("No list avaliable!");

        RealmResults<RealmContainer> result = realm.where(RealmContainer.class)
                .like("sportType", enumSportType.name())
                .findAll();
        return result;
    }

    public static void saveContainerList(Realm realm, List<RealmContainer> list, EnumSportType enumSportType) throws ParameterException {
        if(realm==null)
            throw new ParameterException("Realm is null!");
        if(list == null)
            throw new ParameterException("No list avaliable!");
        realm.beginTransaction();
        try{realm.where(RealmContainer.class).like("sportType", enumSportType.name()).findAll().deleteAllFromRealm();}catch (Throwable e ){
            Log.w("sssss", e.getMessage());
        }
        realm.copyToRealm(list);
        realm.commitTransaction();
    }
}
