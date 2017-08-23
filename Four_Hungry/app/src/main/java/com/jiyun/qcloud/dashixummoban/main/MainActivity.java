package com.jiyun.qcloud.dashixummoban.main;

import android.os.Build;
import android.support.v4.app.FragmentManager;
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
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.manager.ActivityCollector;
import com.jiyun.qcloud.dashixummoban.manager.FragmentMager;
import com.jiyun.qcloud.dashixummoban.ui.first.FirstPageFragment;
import com.jiyun.qcloud.dashixummoban.ui.first.HomePresenter;
import com.jiyun.qcloud.dashixummoban.ui.more.MorePageFragment;
import com.jiyun.qcloud.dashixummoban.ui.mycenter.MyPageFragment;
import com.jiyun.qcloud.dashixummoban.ui.order.OrderPageFragment;
import com.orhanobut.logger.Logger;

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
    private FragmentManager fragmentManager;
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
        Logger.d("===========");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        return R.layout.activity_main;
    }

    ///////////////////

    /**
     * 自定义回退栈管理；
     * 获取栈顶的fragment的名字，判断名字是否和主页的名字是否一样，
     * 如果一样就退出应用，如果不是就回退上一个fragment；
     * <p>
     * onBackPressed()与onKeyDown
     */
    @Override
    public void onBackPressed() {
        String simpleName = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        if ("FirstPageFragment".equals(simpleName) ||
                "OrderPageFragment".equals(simpleName) ||
                "MyFragment".equals(simpleName) ||
                "MoreFragment".equals(simpleName)
                ) {
            finish();
        } else {
            if (fragmentManager.getBackStackEntryCount() > 1) {
                fragmentManager.popBackStackImmediate();//
                String name = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
                App.lastfragment = (BaseFragment) fragmentManager.findFragmentByTag(name);
            }
        }
    }

    /**
     * 双击退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String name = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        if ("FirstPageFragment".equals(name) ||
                "OrderPageFragment".equals(name) ||
                "MyFragment".equals(name) ||
                "MoreFragment".equals(name)
                ) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {//back键被按下了
                if ((System.currentTimeMillis() - mExitTime) > 2000) {//第二次点击判断是否在两秒内完成，是的话Finish掉（退出）
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    ActivityCollector.getInstance().exit(App.mBaseActivity);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }



    @OnClick({R.id.main_ZongHe, R.id.main_DongTan, R.id.main_FaXian, R.id.main_WoDe, R.id.Main_RadioGroup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_ZongHe:
                FragmentMager.getInstance().start(R.id.container, FirstPageFragment.class,false).build();
                break;
            case R.id.main_DongTan:
                FragmentMager.getInstance().start(R.id.container, OrderPageFragment.class,false).build();
                break;
            case R.id.main_FaXian:
                FragmentMager.getInstance().start(R.id.container, MyPageFragment.class,false).build();
                break;
            case R.id.main_WoDe:
                FragmentMager.getInstance().start(R.id.container, MorePageFragment.class,false).build();
                break;
            case R.id.Main_RadioGroup:
                break;
        }
    }
}




