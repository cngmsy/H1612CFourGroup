package com.jiyun.qcloud.dashixummoban.modle.ordermodel;

import com.jiyun.qcloud.dashixummoban.entity.orderbean.OrderBean;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.BaseModel;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by Administrator on 2017/8/23.
 */

public interface IOrederModle extends BaseModel {
     void loadOrderList(NetWorkCallBack<OrderBean> callBack);
}
