package com.jiyun.qcloud.dashixummoban.modle.dataModel;


import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.entity.Head;
import com.jiyun.qcloud.dashixummoban.entity.PandaHome;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by xingge on 2017/7/26.
 */

public class PandaHomeModelImpl implements IPandaHomeModel {



    @Override
    public void loadHomeList(NetWorkCallBack<PandaHome> callback) {
        //iHttp.get(Urls.PANDAHOME,null,callback);
    }

    @Override
    public void updataHead(NetWorkCallBack<Head> callback) {
        iHttp.get(Urls.PANDAHOME,null,callback);
    }
}
