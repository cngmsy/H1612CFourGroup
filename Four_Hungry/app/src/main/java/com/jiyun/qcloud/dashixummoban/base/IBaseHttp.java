package com.jiyun.qcloud.dashixummoban.base;


import android.widget.ImageView;

import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import java.io.File;
import java.util.Map;

/**
 * 网络的顶层接口
 */

public interface IBaseHttp {


    <T> void get(String url, Map<String, String> params, NetWorkCallBack<T> callback);
    <T> void get(String url, Map<String, String> params, Map<String, String> headers, NetWorkCallBack<T> callback);
    <T> void post(String url, Map<String, String> params,NetWorkCallBack<T> callback);
    void upload(String url, Map<String,String> map, File file, NetWorkCallBack  callback);
    void download();
    void loadImage(String url, ImageView imageView);

}
