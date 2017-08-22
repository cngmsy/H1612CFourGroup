package com.jiyun.qcloud.dashixummoban.manager;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.orhanobut.logger.Logger;

/**
 * Created by chj on 2017/8/20.
 */

public class FragmentMager {
    private static FragmentMager fragmentMager;
    private android.support.v4.app.FragmentManager fragmentManager;
    private BaseFragment fragment;
    private String simpleName;
    private FragmentTransaction fragmentTransaction;



    public FragmentMager() {
        getFragmentManagers();

    }

    public static FragmentMager getInstance() {
        if (fragmentMager == null) {
            synchronized (FragmentMager.class) {
                fragmentMager = new FragmentMager();
            }
        }
        return fragmentMager;
    }

    public FragmentMager getFragmentManagers() {
        fragmentManager = App.mBaseActivity.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        return this;
    }

    /**
     * @param containID     容器的id
     * @param fragmentClass fragment的实例化对象
     * @return 当前类对象, 方便使用构建者模式
     * @nest    是否是Fragment嵌套(Fragment和Fragment嵌套)
     */

    public FragmentMager start(int containID, Class<? extends BaseFragment> fragmentClass,Boolean nest) {

        getFragmentManagers();


        simpleName = fragmentClass.getSimpleName();
        fragment = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);

        if (fragment == null) {

            try {
                //java动态代理
                fragment = fragmentClass.newInstance();
                //add
                fragmentTransaction.add(containID, fragment, simpleName);
                fragmentTransaction.addToBackStack(simpleName);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        if (App.lastfragment != null&!nest) {
            fragmentTransaction.hide(App.lastfragment);
        }

        Logger.d("11111111");
        fragmentTransaction.show(fragment);

        return this;

    }

    /**
     *
     * @param containID
     * @param fragmentClass
     * @return
     */
    public FragmentMager replace(int containID, Class<? extends BaseFragment> fragmentClass) {

      //  getFragmentManagers();


        simpleName= fragmentClass.getSimpleName();
        fragment = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);

        if (fragment == null) {

            try {
                //java动态代理
                fragment = fragmentClass.newInstance();
                //add
                fragmentTransaction.replace(containID, fragment, simpleName);
                fragmentTransaction.addToBackStack(simpleName);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }



        Logger.d("11111111");
        fragmentTransaction.show(fragment);

        return this;
    }

    /**
     * 可以在非Fragment中给对应的Fragment传值
     *
     * @param bundle
     * @return
     */
    public FragmentMager setBundle(Bundle bundle) {
        if (bundle != null) {
            fragment.setBundle(bundle);
        }
        return this;
    }

    /**
     * 提交transaction
     * @return
     */
    public BaseFragment build() {




        App.lastfragment = fragment;
        fragmentTransaction.commit();
        int count=fragmentManager.getBackStackEntryCount();
        Logger.d("当前回退栈里面的Fragment对象为"+count);
        return fragment;
    }


}
