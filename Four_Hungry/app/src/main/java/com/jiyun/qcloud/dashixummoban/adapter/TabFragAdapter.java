package com.jiyun.qcloud.dashixummoban.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jiyun.qcloud.dashixummoban.base.BaseFragment;

import java.util.List;

/**
 * Created by liuwangping on 2017/8/13.
 */

public class TabFragAdapter extends FragmentStatePagerAdapter {
    List<String> listname;
    List<BaseFragment> listfragment;

    public TabFragAdapter(FragmentManager fm, List<String> listname, List<BaseFragment> listfragment) {
        super(fm);
        this.listname = listname;
        this.listfragment = listfragment;
    }

    @Override
    public Fragment getItem(int position) {
        return listfragment.get(position);
    }

    @Override
    public int getCount() {
        return listfragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listname.get(position);
    }
}
