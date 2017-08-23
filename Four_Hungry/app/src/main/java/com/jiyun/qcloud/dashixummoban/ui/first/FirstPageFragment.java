package com.jiyun.qcloud.dashixummoban.ui.first;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.Home;
import com.jiyun.qcloud.dashixummoban.entity.HomeBean;
import com.jiyun.qcloud.dashixummoban.ui.first.adapter.GridHomeAdapter;
import com.jiyun.qcloud.dashixummoban.ui.first.adapter.HomeAdapter;
import com.jiyun.qcloud.dashixummoban.ui.first.goods.activity.GoodsActivity;
import com.jiyun.qcloud.dashixummoban.ui.first.map.GaodeActivity;
import com.jiyun.qcloud.dashixummoban.ui.first.search.SearchActivity;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by chj on 2017/8/20.
 */

public class FirstPageFragment extends BaseFragment implements HomeContract.View, AMapLocationListener {
    private int mDistance = 0;
    private int maxDistance = 255;
    @BindView(R.id.first_recycler)
    PullToRefreshRecyclerView firstRecycler;
    @BindView(R.id.home_tv_address)
    TextView homeTvAddress;
    @BindView(R.id.map)
    LinearLayout map;
    @BindView(R.id.textsss)
    TextView textsss;
    @BindView(R.id.edisearch)
    LinearLayout edisearch;
    @BindView(R.id.ll_title_search)
    LinearLayout llTitleSearch;
    @BindView(R.id.ll_title_container)
    LinearLayout llTitleContainer;
    Unbinder unbinder;
    private StringBuffer buffer;


    private PullToRefreshRecyclerView home_recycler;
    private RollPagerView rollpager;
    private GridHomeAdapter gridHomeAdapter;
    private HomeAdapter adapter;



    private HomeContract.Presenter presenter;
    private List<HomeBean.BodyBean.SellerBean> list2 = new ArrayList<>();
    private List<String> list3 = new ArrayList<>();
    private List<HomeBean.BodyBean> list4 = new ArrayList<>();
    private List<HomeBean.HeadBean.CategorieListBean> list1 = new ArrayList<>();
    private AMapLocationClient aMapLocationClient;
    private AMapLocationClientOption mLocationOption;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    protected void initView(View view) {
        aMapLocationClient = new AMapLocationClient(getActivity().getApplicationContext());
        aMapLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setOnceLocation(false);
        mLocationOption.setMockEnable(false);
        mLocationOption.setInterval(2000);
        aMapLocationClient.setLocationOption(mLocationOption);


        firstRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        firstRecycler.setPullRefreshEnabled(true);
        firstRecycler.displayLastRefreshTime(true);
        firstRecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                firstRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        firstRecycler.setRefreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {

            }
        });
        adapter = new HomeAdapter(getActivity(),list2,list3,list4);
        firstRecycler.setAdapter(adapter);

    }
    @Override
    public void setBundle(Bundle bundle) {

    }
    @Override
    public void showHomeListData(Home home) {
        initBanner();
        Gson gson = new Gson();
        HomeBean homeBean = gson.fromJson(home.getData(), HomeBean.class);
        list1.addAll(homeBean.getHead().getCategorieList());
        for (int i = 0; i < homeBean.getBody().size(); i++) {
            list2.add(homeBean.getBody().get(i).getSeller());
            list3.addAll(homeBean.getBody().get(i).getRecommendInfos());
        }
        list4.addAll(homeBean.getBody());
        App.mBaseActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                gridHomeAdapter.notifyDataSetChanged();
            }
        });
        initListener();
        adapter.setOnItemClickLinear(new HomeAdapter.OnItemClickLinear() {
            @Override
            public void onItemvlick(int position) {
                Intent intent = new Intent(getContext(), GoodsActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
    private void initBanner() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_banner, null);
        rollpager = inflate.findViewById(R.id.banner);
        home_recycler = inflate.findViewById(R.id.home_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        home_recycler.setLayoutManager(gridLayoutManager);
        home_recycler.setPullRefreshEnabled(false);
        home_recycler.setLoadingMoreEnabled(false);
        gridHomeAdapter = new GridHomeAdapter(getContext(), list1);
        home_recycler.setAdapter(gridHomeAdapter);
        //设置播放时间间隔
        rollpager.setPlayDelay(1000);
        //设置透明度
        rollpager.setAnimationDurtion(500);
        //设置适配器
        rollpager.setAdapter(new TestNormalAdapter());
        rollpager.setHintView(new ColorPointHintView(getContext(), Color.YELLOW, Color.WHITE));
        firstRecycler.addHeaderView(inflate);

    }
    public void initListener() {
        firstRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mDistance += dy;
                float percent = mDistance * 1f / maxDistance;//百分比
                int alpha = (int) (percent * 255);
                setSystemBarAlpha(alpha);
            }
        });
    }

    private void setSystemBarAlpha(int alpha) {
        if (alpha >= 255) {
            alpha = 255;
        } else if (alpha <= 125) {
            alpha = 125;
        }
        //标题栏渐变。a:alpha透明度 r:红 g：绿 b蓝
        llTitleContainer.setBackgroundColor(Color.rgb(57, 174, 255));//没有透明效果
        llTitleContainer.getBackground().setAlpha(alpha);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码
                buffer = new StringBuffer();
                buffer.append(aMapLocation.getCountry() + ""
                        + aMapLocation.getProvince() + ""
                        + aMapLocation.getCity() + ""
                        + aMapLocation.getProvince() + ""
                        + aMapLocation.getDistrict() + ""
                        + aMapLocation.getStreet() + ""
                        + aMapLocation.getStreetNum());
                homeTvAddress.setText(buffer.toString() + "精度" + aMapLocation.getLongitude() + "纬度" + aMapLocation.getLatitude());
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }

    private class TestNormalAdapter extends StaticPagerAdapter {
        private String[] imgs = {
                "http://123.206.14.104:8080/TakeoutService/imgs/promotion/1.jpg",
                "http://123.206.14.104:8080/TakeoutService/imgs/promotion/2.jpg",
                "http://123.206.14.104:8080/TakeoutService/imgs/promotion/3.jpg"
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            Glide.with(container.getContext()).load(imgs[position]).into(view);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }
    @Override
    public void listener() {
        homeTvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.mBaseActivity.startActivity(new Intent(getActivity(), GaodeActivity.class));
            }
        });

        edisearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ints = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(ints);
            }
        });
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


}
