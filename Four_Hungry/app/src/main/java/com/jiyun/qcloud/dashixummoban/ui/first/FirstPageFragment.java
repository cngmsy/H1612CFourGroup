package com.jiyun.qcloud.dashixummoban.ui.first;

import android.os.Bundle;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.PandaHome;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chj on 2017/8/20.
 */

public class FirstPageFragment extends BaseFragment implements XRecyclerView.LoadingListener,HomeContract.View {

    private HomeContract.Presenter presenter;
    private List<Object> datas;



    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        presenter.start();

    }

    @Override
    protected void initView(View view) {
        this.datas = new ArrayList<Object>();

    }

    @Override
    public void setBundle(Bundle bundle) {

    }




    @Override
    public void showHomeListData(PandaHome pandaHome) {
        Logger.d("在接口回调中返回结果"+pandaHome.getData().getArea().getTitle());
    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loadWebView() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRefresh() {
        presenter.start();
    }

    @Override
    public void onLoadMore() {

    }
}
