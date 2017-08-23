package com.jiyun.qcloud.dashixummoban.entity;

/**
 * Created by my301s on 2017/7/24.
 */

public class DataSynEvent {
    String name;
    String Image_Head;

    public DataSynEvent(String name, String image_Head) {
        this.name = name;
        Image_Head = image_Head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_Head() {
        return Image_Head;
    }

    public void setImage_Head(String image_Head) {
        Image_Head = image_Head;
    }

    @Override
    public String toString() {
        return "DataSynEvent{" +
                "name='" + name + '\'' +
                ", Image_Head='" + Image_Head + '\'' +
                '}';
    }
}
