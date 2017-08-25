package com.jiyun.qcloud.dashixummoban.ui.first;


import com.jiyun.qcloud.dashixummoban.entity.Home;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.HomeImpl;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import java.io.File;

/**
 * Created by xingge on 2017/7/26.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View homeView;
    private HomeImpl homeModel;
    /*
       在构造方法里面做了什么事情;   初始化了HomeView  这时候的homeView相当于HomePageFragent
       同时在这个构造方法中有初始化了homeModel   HomeModel里面就是网络请求后从服务器返回的bean结果
     */
    public HomePresenter(HomeContract.View homeView){
        this.homeView = homeView;
        homeView.setPresenter(this);
        this.homeModel = new HomeImpl();
    }

    @Override
    public void start() {
        homeView.showProgress();
        homeView.listener();
        homeModel.getHome(116.30142, 40.05087, new NetWorkCallBack<Home>() {
            @Override
            public void onSuccess(Home home) {
                homeView.showHomeListData(home);
                homeView.dimissProgress();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                homeView.showMessage(errorMsg);
                homeView.dimissProgress();
            }

            @Override
            public void onFail(String netOff) {

            }
        });


    }

    @Override
    public void upImage(File file) {

    }
}
