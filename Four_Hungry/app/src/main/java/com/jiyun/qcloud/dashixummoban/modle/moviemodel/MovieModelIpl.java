package com.jiyun.qcloud.dashixummoban.modle.moviemodel;

import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.entity.MovieBean;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by Administrator on 2017/8/22.
 */

public class MovieModelIpl implements IMovieHomeModel {
    @Override
    public void loadmovielist(NetWorkCallBack<MovieBean> callback) {
        iHttp.get(Urls.MOVIES,null,callback);
    }
}
