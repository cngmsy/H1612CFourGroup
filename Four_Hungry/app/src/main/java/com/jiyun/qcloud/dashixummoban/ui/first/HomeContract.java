package com.jiyun.qcloud.dashixummoban.ui.first;

import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.Home;

/**
 * Created by chj on 2017/8/21.
 * 这是难点
 */

public class HomeContract {

    interface View extends IBaseView<Presenter> {
        void showHomeListData(Home home);
        void listener();

    }

    interface Presenter extends IBasePresenter {}
}
