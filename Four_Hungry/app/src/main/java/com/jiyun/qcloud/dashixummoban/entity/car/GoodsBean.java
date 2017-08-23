package com.jiyun.qcloud.dashixummoban.entity.car;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by my301s on 2017/8/12.
 */

public class GoodsBean {

    /**
     * id : 10101
     * info : (不与其它活动同享)13.9元特价套餐!!|13.9特价套餐!!(每单仅限2份)
     * list : [{"bargainPrice":true,"form":"肉末烧汁茄子+千叶豆腐+小食+时蔬+含粗粮米饭)","icon":"http://123.206.14.104:8080/TakeoutService/imgs/goods/caiping_taocan.webp","id":101001,"monthSaleNum":53,"name":"肉末烧汁茄子+千叶豆腐套餐(含粗粮米饭)","new":false,"newPrice":13.899999618530273,"oldPrice":30},{"bargainPrice":true,"form":"肉末烧汁茄子+榄菜肉末四季豆+小食+时蔬+含粗粮米饭)","icon":"http://123.206.14.104:8080/TakeoutService/imgs/goods/caiping_taocan.webp","id":101002,"monthSaleNum":37,"name":"肉末烧汁茄子+四季豆套餐(含粗粮米饭)","new":false,"newPrice":13.899999618530273,"oldPrice":30},{"bargainPrice":true,"form":"手撕杏鲍菇+千叶豆腐+小食+时蔬+含粗粮米饭)","icon":"http://123.206.14.104:8080/TakeoutService/imgs/goods/caiping_taocan.webp","id":101003,"monthSaleNum":27,"name":"手撕杏鲍菇+千叶豆腐套餐(含粗粮米饭)","new":false,"newPrice":13.899999618530273,"oldPrice":30},{"bargainPrice":true,"form":"肉末烧汁茄子+杏鲍菇+小食+时蔬+含粗粮米饭)","icon":"http://123.206.14.104:8080/TakeoutService/imgs/goods/caiping_taocan.webp","id":101004,"monthSaleNum":24,"name":"肉末烧汁茄子+杏鲍菇套餐(含粗粮米饭)","new":false,"newPrice":13.899999618530273,"oldPrice":30},{"bargainPrice":true,"form":"榄菜肉末四季豆+千叶豆腐+小食+时蔬+含粗粮米饭)","icon":"http://123.206.14.104:8080/TakeoutService/imgs/goods/caiping_taocan.webp","id":101005,"monthSaleNum":53,"name":"榄菜肉末四季豆+千叶豆腐套餐(含粗粮米饭)","new":false,"newPrice":13.899999618530273,"oldPrice":30},{"bargainPrice":true,"form":"榄菜肉末四季豆+手撕杏鲍菇+小食+时蔬+含粗粮米饭)","icon":"http://123.206.14.104:8080/TakeoutService/imgs/goods/caiping_taocan.webp","id":101006,"monthSaleNum":53,"name":"榄菜肉末四季豆+手撕杏鲍菇套餐(含粗粮米饭)","new":false,"newPrice":13.899999618530273,"oldPrice":30}]
     * name : 13.9特价套餐
     */

    private int id;
    private String info;
    private String name;
    private List<ListBean> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * bargainPrice : true
         * form : 肉末烧汁茄子+千叶豆腐+小食+时蔬+含粗粮米饭)
         * icon : http://123.206.14.104:8080/TakeoutService/imgs/goods/caiping_taocan.webp
         * id : 101001
         * monthSaleNum : 53
         * name : 肉末烧汁茄子+千叶豆腐套餐(含粗粮米饭)
         * new : false
         * newPrice : 13.899999618530273
         * oldPrice : 30
         */
        private boolean bargainPrice;
        private String form;
        private String icon;
        private int id;
        private int monthSaleNum;
        private String name;
        @SerializedName("new")
        private boolean newX;
        private double newPrice;
        private int oldPrice;

        public boolean isBargainPrice() {
            return bargainPrice;
        }

        public void setBargainPrice(boolean bargainPrice) {
            this.bargainPrice = bargainPrice;
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

        public boolean isNewX() {
            return newX;
        }

        public void setNewX(boolean newX) {
            this.newX = newX;
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
    }
}
