package com.jiyun.qcloud.dashixummoban.entity;

/**
 * Created by my301s on 2017/8/22.
 */

public class HeadEventBean {
    String iconurl;
    String screen_name;

    public HeadEventBean(String iconurl, String screen_name) {
        this.iconurl = iconurl;
        this.screen_name = screen_name;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    @Override
    public String toString() {
        return "HeadEventBean{" +
                "iconurl='" + iconurl + '\'' +
                ", screen_name='" + screen_name + '\'' +
                '}';
    }
}
