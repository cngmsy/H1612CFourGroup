package com.jiyun.qcloud.dashixummoban.modle.ordermodel;

import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.entity.orderbean.OrderBean;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by Administrator on 2017/8/23.
 */

public class IOrderModelImp implements IOrederModle {
    @Override
    public void loadOrderList(NetWorkCallBack<OrderBean> callBack) {
        iHttp.get(Urls.ORDER,null,callBack);
    }
}
