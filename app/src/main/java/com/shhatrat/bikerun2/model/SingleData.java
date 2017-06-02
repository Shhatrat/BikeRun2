package com.shhatrat.bikerun2.model;

/**
 * Created by szymon on 6/2/17.
 */

public class SingleData {
    String datakey, title, content;
    Integer drawable;

    public SingleData(String title, String content, Integer drawable, String datakey) {
        this.datakey = datakey;
        this.title = title;
        this.content = content;
        this.drawable = drawable;
    }

    public String getDatakey() {
        return datakey;
    }

    public void setDatakey(String datakey) {
        this.datakey = datakey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDrawable() {
        return drawable;
    }

    public void setDrawable(Integer drawable) {
        this.drawable = drawable;
    }
}
