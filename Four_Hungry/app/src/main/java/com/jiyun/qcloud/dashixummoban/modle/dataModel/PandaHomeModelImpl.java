package com.jiyun.qcloud.dashixummoban.modle.dataModel;


import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.entity.Head;
import com.jiyun.qcloud.dashixummoban.entity.PandaHome;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import java.io.File;

/**
 * Created by xingge on 2017/7/26.
 */

public class PandaHomeModelImpl implements IPandaHomeModel {


    @Override
    public void loadHomeList(NetWorkCallBack<PandaHome> callback) {

        //iHttp.get(Urls.PANDAHOME,null,callback);
    }

    //上传头像
    @Override
    public void updataHead(File file,NetWorkCallBack<Head> callback) {

        iHttp.upload(Urls.USERIMG,null,file,callback);
    }
}
