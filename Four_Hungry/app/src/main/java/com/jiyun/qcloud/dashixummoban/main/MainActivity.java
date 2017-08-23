package com.jiyun.qcloud.dashixummoban.main;


import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.manager.FragmentMager;
import com.jiyun.qcloud.dashixummoban.ui.first.FirstPageFragment;
import com.jiyun.qcloud.dashixummoban.ui.first.HomePresenter;
import com.jiyun.qcloud.dashixummoban.ui.more.MorePageFragment;
import com.jiyun.qcloud.dashixummoban.ui.more.MoviePresenter;
import com.jiyun.qcloud.dashixummoban.ui.mycenter.MyPageFragment;
import com.jiyun.qcloud.dashixummoban.ui.order.OrdePresenter;
import com.jiyun.qcloud.dashixummoban.ui.order.OrderPageFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chj on 2017/8/20.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.main_ZongHe)
    RadioButton mainZongHe;
    @BindView(R.id.main_DongTan)
    RadioButton mainDongTan;
    @BindView(R.id.main_FaXian)
    RadioButton mainFaXian;
    @BindView(R.id.main_WoDe)
    RadioButton mainWoDe;
    @BindView(R.id.Main_RadioGroup)
    RadioGroup MainRadioGroup;
    private android.support.v4.app.FragmentManager fragmentManager;
    private long mExitTime;

    @Override
    protected void initData() {
        fragmentManager = App.mBaseActivity.getSupportFragmentManager();
        FirstPageFragment homeFragment = (FirstPageFragment) FragmentMager.getInstance().start(R.id.container, FirstPageFragment.class, false).build();
        //presenter在这里初始化
        new HomePresenter(homeFragment);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        return R.layout.activity_main;
    }

    @OnClick({R.id.main_ZongHe, R.id.main_DongTan, R.id.main_FaXian, R.id.main_WoDe, R.id.Main_RadioGroup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_ZongHe:
                FragmentMager.getInstance().start(R.id.container, FirstPageFragment.class,false).build();
                break;
            case R.id.main_DongTan:
                OrderPageFragment orderPageFragment = (OrderPageFragment) FragmentMager.getInstance().start(R.id.container, OrderPageFragment.class, false).build();
                new OrdePresenter(orderPageFragment);
                break;
            case R.id.main_FaXian:
                FragmentMager.getInstance().start(R.id.container, MyPageFragment.class,false).build();
                break;
            case R.id.main_WoDe:
                MorePageFragment morePageFragment = (MorePageFragment) FragmentMager.getInstance().start(R.id.container, MorePageFragment.class, false).build();
                new MoviePresenter(morePageFragment);
                break;
            case R.id.Main_RadioGroup:
                break;
        }
    }



    private long firstTime = 0;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

}




