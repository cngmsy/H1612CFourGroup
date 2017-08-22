package com.jiyun.qcloud.dashixummoban.ui.first.goods.fragment;

import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.car.BaseBean;

/**
 * Created by liuwangping on 2017/8/21.
 */

public class GoodsContract {
     interface View extends IBaseView<Presenter>{
        void showShop(BaseBean baseBean);
        void listener();
    }

    interface Presenter extends IBasePresenter {}
}
