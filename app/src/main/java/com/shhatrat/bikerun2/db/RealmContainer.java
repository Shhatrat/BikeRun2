package com.shhatrat.bikerun2.db;

import com.annimon.stream.Stream;
import com.shhatrat.bikerun2.service.EnumSportType;
import com.shhatrat.bikerun2.utils.StringUtil;
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

        RealmList<RealmData> realmList = new RealmList<>();
        Stream.of(nc.getList()).forEach(l -> realmList.add(new RealmData(l)));
        this.list = realmList;
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

    public RealmList<RealmData> getList() {
        return list;
    }

    public void setList(RealmList<RealmData> list) {
        this.list = list;
    }

    private RealmList<RealmData> list = new RealmList<RealmData>();

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
