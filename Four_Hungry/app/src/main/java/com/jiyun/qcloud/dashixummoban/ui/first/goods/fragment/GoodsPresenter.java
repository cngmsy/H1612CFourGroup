package com.jiyun.qcloud.dashixummoban.ui.first.goods.fragment;

import com.jiyun.qcloud.dashixummoban.entity.car.BaseBean;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.IShopCarModel;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.ShopCarModelImpl;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by liuwangping on 2017/8/21.
 */

public class GoodsPresenter implements GoodsContract.Presenter{
    private GoodsContract.View goodsView;
    private IShopCarModel shopCarModel;

    public GoodsPresenter(GoodsContract.View goodsView) {
        this.goodsView = goodsView;
        goodsView.setPresenter(this);
        this.shopCarModel=new ShopCarModelImpl();

    }

    @Override
    public void start() {
        goodsView.showProgress();
        goodsView.listener();
        //在该方法的回调参数中完成数据bean对象的返回
        shopCarModel.shop("101", new NetWorkCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                goodsView.showShop(baseBean);
                goodsView.dimissProgress();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                goodsView.showMessage(errorMsg);
                goodsView.dimissProgress();
            }

            @Override
            public void onFail(String netOff) {

            }
        });
    }
}
