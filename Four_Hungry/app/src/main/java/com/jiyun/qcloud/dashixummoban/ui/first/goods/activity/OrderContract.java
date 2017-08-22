package com.jiyun.qcloud.dashixummoban.ui.first.goods.activity;

import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.car.BaseBean;

/**
 * Created by liuwangping on 2017/8/22.
 */

public class OrderContract {

     interface View extends IBaseView<OrderContract.Presenter> {
        void showOrder(BaseBean baseBean);
    }

     interface Presenter extends IBasePresenter {}

}
