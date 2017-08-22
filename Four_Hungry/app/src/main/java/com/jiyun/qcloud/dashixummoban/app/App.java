package com.jiyun.qcloud.dashixummoban.app;

import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;

/**
 * Created by chj on 2017/8/20.
 */

public class App extends  BaseApplication implements Thread.UncaughtExceptionHandler{

    public static BaseActivity mBaseActivity;
    public static BaseFragment lastfragment;


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.setDefaultUncaughtExceptionHandler(this);

    }
}
