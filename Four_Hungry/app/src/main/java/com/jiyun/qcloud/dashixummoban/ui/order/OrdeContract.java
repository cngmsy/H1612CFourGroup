package com.jiyun.qcloud.dashixummoban.ui.order;

import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.orderbean.OrderBean;

/**
 * Created by Administrator on 2017/8/23.
 */

public class OrdeContract {
    //接口管理类
    public interface View extends IBaseView<Presenter> {
        void showOrderList(OrderBean orderBean);

    }

    interface Presenter extends IBasePresenter {}
}
