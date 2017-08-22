package com.jiyun.qcloud.dashixummoban.ui.first.goods.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.TabFragAdapter;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.ui.first.goods.fragment.AssessFragment;
import com.jiyun.qcloud.dashixummoban.ui.first.goods.fragment.GoodsFragment;
import com.jiyun.qcloud.dashixummoban.ui.first.goods.fragment.GoodsPresenter;
import com.jiyun.qcloud.dashixummoban.ui.first.goods.fragment.MerchantsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liuwangping on 2017/8/21.
 */

public class GoodsActivity extends BaseActivity {
    @BindView(R.id.Add_Iv_Return)
    ImageView AddIvReturn;
    @BindView(R.id.Title_Text)
    TextView TitleText;
    @BindView(R.id.Top_Group)
    RelativeLayout TopGroup;
    @BindView(R.id.marker_tablayout)
    TabLayout markerTablayout;
    @BindView(R.id.marker_ViewPager)
    ViewPager markerViewPager;
    private List<String> listName;
    private List<BaseFragment> listfragmet;
    private TabFragAdapter adapter;

    @Override
    protected void initData() {
        listName = new ArrayList<>();
        listName.clear();
        listfragmet = new ArrayList<>();
        listfragmet.clear();
        listName.add("商品");
        listName.add("评价");
        listName.add("商家");

        GoodsFragment goodsFragment=new GoodsFragment();
        new GoodsPresenter(goodsFragment);

        listfragmet.add(goodsFragment);
        listfragmet.add(new AssessFragment());
        listfragmet.add(new MerchantsFragment());

        markerTablayout.setTabMode(TabLayout.MODE_FIXED);
        adapter= new TabFragAdapter(getSupportFragmentManager(), listName, listfragmet);
        markerViewPager.setAdapter(adapter);
        markerTablayout.setupWithViewPager(markerViewPager);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_marker;
    }


    @OnClick(R.id.Add_Iv_Return)
    public void onViewClicked() {
        finish();
    }
}
