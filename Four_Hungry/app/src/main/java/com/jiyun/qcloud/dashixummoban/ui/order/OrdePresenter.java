package com.jiyun.qcloud.dashixummoban.ui.order;

import com.jiyun.qcloud.dashixummoban.entity.orderbean.OrderBean;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;
import com.jiyun.qcloud.dashixummoban.modle.ordermodel.IOrderModelImp;
import com.jiyun.qcloud.dashixummoban.modle.ordermodel.IOrederModle;

import java.io.File;

/**
 * Created by Administrator on 2017/8/23.
 */

public class OrdePresenter implements OrdeContract.Presenter {
    private OrdeContract.View ordeview;
    private IOrederModle  ordemodle;
    public OrdePresenter(OrdeContract.View ordeview){
        this.ordeview = ordeview;
        ordeview.setPresenter(this);
        this.ordemodle = new  IOrderModelImp();

    }
    @Override
    public void start() {
        ordeview.showProgress();
        ordemodle.loadOrderList(new NetWorkCallBack<OrderBean>() {
            @Override
            public void onSuccess(OrderBean orderBean) {
                ordeview.showOrderList(orderBean);
                ordeview.dimissProgress();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                ordeview.showMessage(errorMsg);
                ordeview.dimissProgress();
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
