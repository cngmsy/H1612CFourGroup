package com.jiyun.qcloud.dashixummoban.modle.dataModel;

import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.entity.Home;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/22.
 */

public class HomeImpl implements IHomeModel {
    @Override
    public void getHome(double latitude, double longitude, NetWorkCallBack<Home> callback) {
        Map<String,String> params=new HashMap<String, String>();
        params.put("latitude", String.valueOf(latitude));
        params.put("longitude", String.valueOf(longitude));
        iHttp.get(Urls.HOME,params,callback);
    }

    @Override
    public void getShop(int sellerId, NetWorkCallBack<Home> callBack) {
        Map<String,String> params=new HashMap<String, String>();
        params.put("sellerId", String.valueOf(sellerId));
        iHttp.get(Urls.SHOP,params,callBack);
    }
}
