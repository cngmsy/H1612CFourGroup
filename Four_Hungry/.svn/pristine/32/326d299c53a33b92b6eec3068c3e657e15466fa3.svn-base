package com.jiyun.qcloud.dashixummoban.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jiyun.qcloud.dashixummoban.app.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chj on 2017/8/20.
 */

public abstract class BaseActivity  extends AppCompatActivity{

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置竖屏
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        setContentView(getLayoutId());
        App.mBaseActivity=this;
        ButterKnife.bind(this);
        //注意以下方法仅在Activity创建的时候调用一次
        //activity添加到Activity的管理里面
        // ActivityCollector.getInstance().addActivity(this);
        initView();
        initData();


    }


   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    protected void onResume() {
        super.onResume();
        //// TODO: 2017/8/20  在这里添加需要重复执行的内容
        App.mBaseActivity=this;
    }
}
