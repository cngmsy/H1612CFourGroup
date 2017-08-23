package com.jiyun.qcloud.dashixummoban.ui.more;

import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.MovieBean;

/**
 * Created by Administrator on 2017/8/22.
 */

public class MovieContract {
    public interface View extends IBaseView<Presenter> {
        void showMoviedata(MovieBean movieBean);

    }

    interface Presenter extends IBasePresenter {}
}
