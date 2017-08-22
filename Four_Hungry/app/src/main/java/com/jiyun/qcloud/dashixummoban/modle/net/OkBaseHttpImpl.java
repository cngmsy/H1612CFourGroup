package com.jiyun.qcloud.dashixummoban.modle.net;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.base.IBaseHttp;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



/**
 * Created by chj on 2017/8/21.
 */

public class OkBaseHttpImpl implements IBaseHttp {
    private OkHttpClient okHttpClient;
    //构造函数私有化
    private OkBaseHttpImpl(){
        okHttpClient = new OkHttpClient.Builder().build();
    }

    private static OkBaseHttpImpl okHttpUtils;

    //提供一个公共的、静态的、返回值类型是当前本类的对象
    public static OkBaseHttpImpl getInstance(){
        if(okHttpUtils == null){
            synchronized (OkBaseHttpImpl.class){
                if(okHttpUtils == null)
                    okHttpUtils = new OkBaseHttpImpl();
            }
        }
        return okHttpUtils;
    }


    /**
     * 发送get请求
     * @param url 请求地址
     * @param params 请求参数
     * @param callback 回调
     * @param <T> 请求回来的数据对应的JavaBean
     */
    @Override
    public <T> void get(String url, Map<String, String> params, final NetWorkCallBack<T> callback) {

        StringBuffer sb = new StringBuffer(url);
        if(params != null && params.size() > 0){
            sb.append("?");
            Set<String> keys = params.keySet();
            for (String key : keys) {
                String value = params.get(key);
                sb.append(key).append("=").append(value).append("&");
            }
            url = sb.deleteCharAt(sb.length()-1).toString();
        }
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.mBaseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.onError(404,e.getMessage().toString());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                //执行在子线程中
                App.mBaseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.onSuccess(getGeneric(jsonData,callback));
                    }
                });

            }
        });

    }

    @Override
    public <T> void get(String url, Map<String, String> params, Map<String, String> headers, final NetWorkCallBack<T> callback) {

        StringBuffer sb = new StringBuffer(url);
        if(params != null && params.size() > 0){
            sb.append("?");
            Set<String> keys = params.keySet();
            for (String key : keys) {
                String value = params.get(key);
                sb.append(key).append("=").append(value).append("&");
            }
            url = sb.deleteCharAt(sb.length()-1).toString();
        }
        Request.Builder builder = new Request.Builder();
        if(headers != null && headers.size() > 0){
            Set<String> keys = headers.keySet();
            for (String key : keys){
                String value = headers.get(key);
                builder.addHeader(key,value);
            }
        }
        Request request = builder.url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.mBaseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.onError(404,e.getMessage().toString());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                //执行在子线程中
                App.mBaseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.onSuccess(getGeneric(jsonData,callback));
                    }
                });

            }
        });
    }

    /**
     * post方法上传表单
     * @param url
     * @param params
     * @param callback
     * @param <T>
     */
    @Override
    public <T> void post(String url, Map<String, String> params, final NetWorkCallBack<T> callback) {

        FormBody.Builder builder = new FormBody.Builder();
        if(params !=null && params.size() > 0){
            Set<String> keys = params.keySet();
            for (String key : keys) {
                String value = params.get(key);
                builder.add(key,value);
            }
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.mBaseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.onError(404,e.getMessage().toString());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                //执行在子线程中
                App.mBaseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.onSuccess(getGeneric(jsonData,callback));
                    }
                });

            }
        });
    }




    @Override
    public void upload(String url, Map<String,String> map, File file, final NetWorkCallBack callBacks) {
        // MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody=new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("file",file.getName(),fileBody).build();
        Request request=new Request.Builder().url(url).post(requestBody).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

                //(BaseActivity)App.context
                App.mBaseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBacks.onError(404,e.getMessage());
                    }
                });

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String result=response.body().string();
                App.mBaseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBacks.onSuccess(result);
                    }
                });
            }
        });
    }

    @Override
    public void download() {

    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        Glide.with(App.mBaseActivity).load(url).into(imageView);
    }



    /**
     * 自动解析json至回调中的JavaBean
     * @param jsonData
     * @param callBack
     * @param <T>
     * @return
     */
    private <T> T getGeneric(String jsonData,NetWorkCallBack<T> callBack){
        Gson gson = new Gson();
        //通过反射获取泛型的实例
        Type[] types = callBack.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) types[0]).getActualTypeArguments();
        Type type = actualTypeArguments[0];
        T t = gson.fromJson(jsonData,type);
        return t;
    }
}
