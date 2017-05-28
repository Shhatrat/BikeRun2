package com.shhatrat.bikerun2.db;

import com.shhatrat.bikerun2.utils.StringUtil;
import com.shhatrat.bikerun2.service.EnumSportType;
import com.shhatrat.bikerun2.view.fragment.container.EnumContainerType;

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
    String id;

    public RealmContainer(NormalContainer nc)
    {
        this.position = nc.getPosition();
        this.id= (nc.getId());
        this.saveContainerType(nc.getContainerType());
        this.saveSportType(nc.getSportType());
    }

    public RealmContainer() {
        this.id = System.currentTimeMillis()+ StringUtil.getRandomString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private RealmList<DataRealm> list = new RealmList<DataRealm>();

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    private Integer position;
    private String containerType;
    private String sportType;

    public void saveSportType(EnumSportType val) {
        this.sportType = val.toString();
    }

    public EnumSportType getSportType() {
        return EnumSportType.valueOf(sportType);
    }

    public void saveContainerType(EnumContainerType val) {
        this.containerType = val.toString();
    }

    public EnumContainerType getContainerType() {
        return EnumContainerType.valueOf(containerType);
    }
}
