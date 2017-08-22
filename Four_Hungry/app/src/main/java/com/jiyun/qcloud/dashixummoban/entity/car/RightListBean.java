package com.jiyun.qcloud.dashixummoban.entity.car;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class RightListBean implements Serializable{
    String aname;
    private String form;
    private String icon;
    private int id;
    private int monthSaleNum;
    private String name;
    private double newPrice;
    private int oldPrice;
    private int position;
    private int num;

    public RightListBean(String aname, String form, String icon, int id, int monthSaleNum, String name, double newPrice, int oldPrice, int position, int num) {
        this.aname = aname;
        this.form = form;
        this.icon = icon;
        this.id = id;
        this.monthSaleNum = monthSaleNum;
        this.name = name;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
        this.position = position;
        this.num = num;
    }

    public RightListBean() {
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonthSaleNum() {
        return monthSaleNum;
    }

    public void setMonthSaleNum(int monthSaleNum) {
        this.monthSaleNum = monthSaleNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
