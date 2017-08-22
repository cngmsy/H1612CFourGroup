package com.jiyun.qcloud.dashixummoban.modle.dataModel;

import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.entity.car.BaseBean;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuwangping on 2017/8/21.
 */

public class ShopCarModelImpl implements IShopCarModel{

    @Override
    public void shop(String sellerId, NetWorkCallBack<BaseBean> callBack) {
        Map<String, String> params = new HashMap<>();
        params.put("sellerId",sellerId);
        iHttp.get(Urls.SHOP,params, callBack);
    }
}
