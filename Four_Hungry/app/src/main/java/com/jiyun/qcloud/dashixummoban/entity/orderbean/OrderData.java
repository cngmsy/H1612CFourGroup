package com.jiyun.qcloud.dashixummoban.entity.orderbean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/8/23.
 */

public class OrderData {
    /**
     * detail : {"address":"北京校区","pay":"在线支付","phone":"135000000000","time":"2020-10-10 10:10:10","username":"阿曹"}
     * distribution : {"des":"饿啦吗配送","type":"1"}
     * goodsInfos : [{"bargainPrice":false,"form":"","icon":"","id":0,"monthSaleNum":0,"name":"红烧肉","new":false,"newPrice":25,"oldPrice":0},{"bargainPrice":false,"form":"","icon":"","id":0,"monthSaleNum":0,"name":"米饭","new":false,"newPrice":2,"oldPrice":0},{"bargainPrice":false,"form":"","icon":"","id":0,"monthSaleNum":0,"name":"雪碧","new":false,"newPrice":4,"oldPrice":0}]
     * id : 1010 8027 3652 5689 39
     * rider : {"id":100,"location":{"latitude":"106.23","longitude":"43.123"},"name":"张三","phone":"13200000000"}
     * seller : {"activityList":[],"deliveryFee":"","distance":"","ensure":"","id":1,"invoice":"","name":"饿啦吗程序员外卖项目","pic":"http://123.206.14.104:8080/TakeoutService/imgs/category/1.png","recentVisit":"","sale":"","score":"5","sendPrice":0,"time":""}
     * type : 10
     */

    private DetailBean detail;
    private DistributionBean distribution;
    private String id;
    private RiderBean rider;
    private SellerBean seller;
    private String type;
    private List<GoodsInfosBean> goodsInfos;

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public DistributionBean getDistribution() {
        return distribution;
    }

    public void setDistribution(DistributionBean distribution) {
        this.distribution = distribution;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RiderBean getRider() {
        return rider;
    }

    public void setRider(RiderBean rider) {
        this.rider = rider;
    }

    public SellerBean getSeller() {
        return seller;
    }

    public void setSeller(SellerBean seller) {
        this.seller = seller;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<GoodsInfosBean> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<GoodsInfosBean> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }

    public static class DetailBean {
        /**
         * address : 北京校区
         * pay : 在线支付
         * phone : 135000000000
         * time : 2020-10-10 10:10:10
         * username : 阿曹
         */

        private String address;
        private String pay;
        private String phone;
        private String time;
        private String username;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public static class DistributionBean {
        /**
         * des : 饿啦吗配送
         * type : 1
         */

        private String des;
        private String type;

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class RiderBean {
        /**
         * id : 100
         * location : {"latitude":"106.23","longitude":"43.123"}
         * name : 张三
         * phone : 13200000000
         */

        private int id;
        private LocationBean location;
        private String name;
        private String phone;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public static class LocationBean {
            /**
             * latitude : 106.23
             * longitude : 43.123
             */

            private String latitude;
            private String longitude;

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }
        }
    }

    public static class SellerBean {
        /**
         * activityList : []
         * deliveryFee :
         * distance :
         * ensure :
         * id : 1
         * invoice :
         * name : 饿啦吗程序员外卖项目
         * pic : http://123.206.14.104:8080/TakeoutService/imgs/category/1.png
         * recentVisit :
         * sale :
         * score : 5
         * sendPrice : 0
         * time :
         */

        private String deliveryFee;
        private String distance;
        private String ensure;
        private int id;
        private String invoice;
        private String name;
        private String pic;
        private String recentVisit;
        private String sale;
        private String score;
        private int sendPrice;
        private String time;
        private List<?> activityList;

        public String getDeliveryFee() {
            return deliveryFee;
        }

        public void setDeliveryFee(String deliveryFee) {
            this.deliveryFee = deliveryFee;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getEnsure() {
            return ensure;
        }

        public void setEnsure(String ensure) {
            this.ensure = ensure;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getRecentVisit() {
            return recentVisit;
        }

        public void setRecentVisit(String recentVisit) {
            this.recentVisit = recentVisit;
        }

        public String getSale() {
            return sale;
        }

        public void setSale(String sale) {
            this.sale = sale;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public int getSendPrice() {
            return sendPrice;
        }

        public void setSendPrice(int sendPrice) {
            this.sendPrice = sendPrice;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<?> getActivityList() {
            return activityList;
        }

        public void setActivityList(List<?> activityList) {
            this.activityList = activityList;
        }
    }

    public static class GoodsInfosBean {
        /**
         * bargainPrice : false
         * form :
         * icon :
         * id : 0
         * monthSaleNum : 0
         * name : 红烧肉
         * new : false
         * newPrice : 25
         * oldPrice : 0
         */

        private boolean bargainPrice;
        private String form;
        private String icon;
        private int id;
        private int monthSaleNum;
        private String name;
        @SerializedName("new")
        private boolean newX;
        private int newPrice;
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

        public int getNewPrice() {
            return newPrice;
        }

        public void setNewPrice(int newPrice) {
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
