package com.jiyun.qcloud.dashixummoban.ui.first.goods.activity;

import com.jiyun.qcloud.dashixummoban.entity.car.BaseBean;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.IShopCarModel;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.ShopCarModelImpl;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by liuwangping on 2017/8/22.
 */

public class OrderPresenter implements OrderContract.Presenter{

    //在p层持有View层的对象，但是这里的对象不是实例化的对象，而是使用了接口来实现调用
    private OrderContract.View orderView;
    //在P层持有了Model层的对象
    private IShopCarModel orderModel;

    public OrderPresenter(OrderContract.View orderView) {
        this.orderView = orderView;
        orderView.setPresenter(this);
        orderModel=new ShopCarModelImpl();
    }

    @Override
    public void start() {
        orderView.showProgress();

        orderModel.orderPost(new NetWorkCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                orderView.showOrder(baseBean);
                orderView.dimissProgress();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                orderView.showMessage(errorMsg);
                orderView.dimissProgress();

            }

            @Override
            public void onFail(String netOff) {

            }
        });
    }
}
