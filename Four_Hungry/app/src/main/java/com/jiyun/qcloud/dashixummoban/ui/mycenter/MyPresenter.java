package com.jiyun.qcloud.dashixummoban.ui.mycenter;

import com.jiyun.qcloud.dashixummoban.entity.Head;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.IPandaHomeModel;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.PandaHomeModelImpl;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import java.io.File;

/**
 * Created by my301s on 2017/8/22.
 */

public class MyPresenter implements MyContract.Presenter {
    private MyContract.View view;
    private IPandaHomeModel homeModel;

    public MyPresenter(MyContract.View view) {
        this.view = view;
        view.setPresenter(this);
        this.homeModel = new PandaHomeModelImpl();
    }


    @Override
    public void start() {

    }

    @Override
    public void upImage(File file) {
        homeModel.updataHead(file, new NetWorkCallBack<Head>() {
            @Override
            public void onSuccess(Head head) {
                view.showImageHead(head);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            }

            @Override
            public void onFail(String netOff) {

            }
        });
    }
}
