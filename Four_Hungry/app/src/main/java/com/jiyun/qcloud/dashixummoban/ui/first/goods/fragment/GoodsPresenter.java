package com.jiyun.qcloud.dashixummoban.ui.first.goods.fragment;

import com.jiyun.qcloud.dashixummoban.entity.car.BaseBean;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.IShopCarModel;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.ShopCarModelImpl;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by liuwangping on 2017/8/21.
 */

public class GoodsPresenter implements GoodsContract.Presenter{
    //在p层持有View层的对象，但是这里的对象不是实例化的对象，而是使用了接口来实现调用
    private GoodsContract.View goodsView;
    //在P层持有了Model层的对象
    private IShopCarModel shopCarModel;
    /*
   在构造方法里面做了什么事情;   初始化了HomeView  这时候的homeView相当于HomePageFragent
   同时在这个构造方法中有初始化了homeModel   HomeModel里面就是网络请求后从服务器返回的bean结果
 */
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
