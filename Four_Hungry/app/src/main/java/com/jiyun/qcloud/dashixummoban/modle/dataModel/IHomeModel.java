package com.jiyun.qcloud.dashixummoban.modle.dataModel;

import com.jiyun.qcloud.dashixummoban.entity.Home;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by Administrator on 2017/8/22.
 */

public interface IHomeModel extends BaseModel {

    void getHome(double latitude, double longitude, NetWorkCallBack<Home> callback);
    void getShop(int sellerId, NetWorkCallBack<Home> callBack);
}
