package com.jiyun.qcloud.dashixummoban.ui.more;

import com.jiyun.qcloud.dashixummoban.entity.MovieBean;
import com.jiyun.qcloud.dashixummoban.modle.moviemodel.IMovieHomeModel;
import com.jiyun.qcloud.dashixummoban.modle.moviemodel.MovieModelIpl;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

import java.io.File;

/**
 * Created by Administrator on 2017/8/22.
 */

public class  MoviePresenter implements MovieContract.Presenter {
   //https://github.com/cngmsy/H1612CFourGroup
    private MovieContract.View movieview;
    private IMovieHomeModel moviemodel;
    /*
       在构造方法里面做了什么事情;   初始化了HomeView  这时候的homeView相当于HomePageFragent
       同时在这个构造方法中有初始化了homeModel   HomeModel里面就是网络请求后从服务器返回的bean结果
     */
    public MoviePresenter(MovieContract.View movieview){
        this.movieview = movieview;
        movieview.setPresenter(this);
        this.moviemodel = new MovieModelIpl();
    }
    @Override
    public void start() {
        movieview.showProgress();
        moviemodel.loadmovielist(new NetWorkCallBack<MovieBean>() {
            @Override
            public void onSuccess(MovieBean movieBean) {
                movieview.showMoviedata(movieBean);
                movieview.dimissProgress();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
               movieview.showMessage(errorMsg);
                movieview.dimissProgress();
            }

            @Override
            public void onFail(String netOff) {

            }
        });
    }

    @Override
    public void upImage(File file) {

    }
}
