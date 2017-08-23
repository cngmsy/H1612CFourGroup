package com.jiyun.qcloud.dashixummoban.ui.mycenter;

import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.Head;

/**
 * Created by my301s on 2017/8/22.
 */

public class MyContract {
    interface View extends IBaseView<Presenter> {
        void showImageHead(Head head);
    }
    interface Presenter extends IBasePresenter {}
}
