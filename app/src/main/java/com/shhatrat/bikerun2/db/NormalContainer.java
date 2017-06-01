package com.shhatrat.bikerun2.db;

import com.annimon.stream.Stream;
import com.google.gson.Gson;
import com.shhatrat.bikerun2.service.EnumSportType;
import com.shhatrat.bikerun2.utils.StringUtil;
import com.shhatrat.bikerun2.view.fragment.container.EnumContainerType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szymon on 27.05.17.
 */

public class NormalContainer {
    String id;

    public NormalContainer() {
        this.id = System.currentTimeMillis()+ StringUtil.getRandomString();
    }

    public NormalContainer(RealmContainer nc)
    {
        this.position = (nc.getPosition());
        this.id = (nc.getId());
        this.saveContainerType(nc.getContainerType());
        this.saveSportType(nc.getSportType());
        List<NormalData> normalDatas = new ArrayList<>();
        Stream.of(nc.getList()).forEach(o -> normalDatas.add(new NormalData(o)));
        this.list = normalDatas;
    }

    public String serialize() {
        return new Gson().toJson(this);
    }

    public static NormalContainer deserialize(String json) {
        return new Gson().fromJson(json, NormalContainer.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private List<NormalData> list;

    public List<NormalData> getList() {
        return list;
    }

    public void setList(List<NormalData> list) {
        this.list = list;
    }

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
