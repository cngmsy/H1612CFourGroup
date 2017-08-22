package com.jiyun.qcloud.dashixummoban.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.qcloud.dashixummoban.app.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chj on 2017/8/20.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
           // view = getCreateView(inflater, container);
            view=View.inflate(App.mBaseActivity,getLayoutRes(),null);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }


        unbinder =ButterKnife.bind(this,view);
        initView(view);

        return view;
    }
    /**
     * 获取Fragment布局文件的View
     *
     * @param inflater
     * @param container
     * @return
     */
    private View getCreateView(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(getLayoutRes(), container, false);
    }

    protected abstract int getLayoutRes();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    protected abstract void initData();

    protected abstract void initView(View view);

    /**
     * Fragment之间传递数据或者Fragment与Activity之间传递值都可以使用该方法
     * 对应的获取Bundule的方法是
     * Bundle bundle=getArguments();
     * @param bundle
     */
    public abstract void setBundle(Bundle bundle);



}
