package com.jiyun.qcloud.dashixummoban.modle.dataModel;

import com.jiyun.qcloud.dashixummoban.entity.car.BaseBean;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by liuwangping on 2017/8/21.
 */

public interface IShopCarModel extends BaseModel {
    void shop(String sellerId,NetWorkCallBack<BaseBean> callBack);

}
