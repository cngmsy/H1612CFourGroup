package com.jiyun.qcloud.dashixummoban.modle.moviemodel;

import com.jiyun.qcloud.dashixummoban.entity.MovieBean;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.BaseModel;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by Administrator on 2017/8/22.
 */

public interface IMovieHomeModel extends BaseModel {
    void loadmovielist(NetWorkCallBack<MovieBean> callback);
}
