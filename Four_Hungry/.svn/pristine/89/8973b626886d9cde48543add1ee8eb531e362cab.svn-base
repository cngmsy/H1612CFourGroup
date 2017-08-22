package com.jiyun.qcloud.dashixummoban.modle.net;

import android.widget.ImageView;

import com.jiyun.qcloud.dashixummoban.base.IBaseHttp;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import java.io.File;
import java.util.Map;

/**
 * Created by chj on 2017/8/21.
 */

public class RetrofitImpl implements IBaseHttp {

    //构造函数私有化
    private RetrofitImpl(){
       // TODO: 2017/8/21   获取retrofit对象
    }

    private static RetrofitImpl retrofitImpl;

    //提供一个公共的、静态的、返回值类型是当前本类的对象
    public static RetrofitImpl getInstance(){
        if(retrofitImpl == null){
            synchronized (RetrofitImpl.class){
                if(retrofitImpl == null)
                    retrofitImpl = new RetrofitImpl();
            }
        }
        return retrofitImpl;
    }

    @Override
    public <T> void get(String url, Map<String, String> params, NetWorkCallBack<T> callback) {
        
    }

    @Override
    public <T> void get(String url, Map<String, String> params, Map<String, String> headers, NetWorkCallBack<T> callback) {

    }

    @Override
    public <T> void post(String url, Map<String, String> params, NetWorkCallBack<T> callback) {

    }

    @Override
    public void upload(String url, Map<String, String> map, File file, NetWorkCallBack callback) {

    }

    @Override
    public void download() {

    }

    @Override
    public void loadImage(String url, ImageView imageView) {

    }
}
