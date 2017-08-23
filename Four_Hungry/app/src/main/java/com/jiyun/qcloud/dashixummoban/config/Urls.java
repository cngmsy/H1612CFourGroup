package com.jiyun.qcloud.dashixummoban.config;

/**
 * Created by xingge on 2017/7/11.
 * 相关参数
 */

public class Urls {
    //时光网视频地址
    public static final String MOVIES = "https://ticket-api-m.mtime.cn/discovery/trailerList.api";
    //订单页面GET
    public static final String ORDER = "http://123.206.14.104:8080/TakeoutService//order?userId=3626";
    //服务器地址
    public static final String BASE_URL = "http://123.206.14.104:8080/TakeoutService/";
    //首页GET
    public static final String HOME = BASE_URL + "home";
    //商铺页GET
//    http://123.206.14.104:8080/TakeoutService/goods?sellerId=101
    public static final String SHOP = BASE_URL + "goods";
    //用户登陆GET
    public static final String LOGIN = BASE_URL + "login";
    //订单管理
    public static final String ORDERFORM_MANAGER = BASE_URL + "order";
    //订单列表GET
    public static final String ORDERFORM_LIST = BASE_URL + "order";
    //支付GET
    public static final String PAY = BASE_URL + "pay";
    //上传头像
    public static final String USERIMG ="http://123.206.14.104:8080/FileUploadDemo/FileUploadServlet";

    //预告片
    public static final String PREVUE="https://ticket-api-m.mtime.cn/discovery/trailerList.api";

}
