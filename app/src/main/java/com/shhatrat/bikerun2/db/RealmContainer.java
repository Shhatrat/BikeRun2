package com.shhatrat.bikerun2.db;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by szymon on 01.05.17.
 */

public class RealmContainer extends RealmObject {


    @PrimaryKey
    @Required
    Integer id;
    private RealmList<DataRealm> list = new RealmList<DataRealm>();
}
