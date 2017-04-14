package com.shhatrat.bikerun2.model;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by szymon on 14.04.17.
 */
@Parcel
public class AthleteDataToStats {

    List<AthleteData> listdata;

    public AthleteDataToStats() {
    }

    public List<AthleteData> getListdata() {
        return listdata;
    }

    public void setListdata(List<AthleteData> listdata) {
        this.listdata = listdata;
    }

    @Parcel
    static public class AthleteData{

        public AthleteData() {
        }

        String attributeName;
        String value;

        public String getAttributeName() {
            return attributeName;
        }

        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public AthleteData(String attributeName, String value) {
            this.attributeName = attributeName;
            this.value = value;
        }
    }
}
