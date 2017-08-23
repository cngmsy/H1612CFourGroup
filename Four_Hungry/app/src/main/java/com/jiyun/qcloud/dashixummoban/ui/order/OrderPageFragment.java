package com.jiyun.qcloud.dashixummoban.ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.orderbean.OrderBean;
import com.jiyun.qcloud.dashixummoban.entity.orderbean.OrderData;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chj on 2017/8/20.
 */

public class OrderPageFragment extends BaseFragment implements OrdeContract.View {
    @BindView(R.id.orderlistview)
    ListView orderlistview;
    Unbinder unbinder;
    private OrdeContract.Presenter presenter;
    private List<OrderData.SellerBean>   ordelist = new ArrayList<>();
    private OrderAdapter adapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initData() {
        presenter.start();
    }

    @Override
    protected void initView(View view) {
        adapter = new OrderAdapter(getActivity(),ordelist);
        orderlistview.setAdapter(adapter);
    }

    @Override
    public void setBundle(Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void setPresenter(OrdeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showOrderList(OrderBean orderBean) {
        Gson   gson = new Gson();
        Type listType = new TypeToken<List<OrderData>>() {
        }.getType();
        List<OrderData> orderDatas = gson.fromJson(orderBean.getData(), listType);
        for (int i = 0; i < orderDatas.size(); i++) {
            OrderData.SellerBean seller = orderDatas.get(i).getSeller();
            ordelist.add(seller);
        }
        adapter.notifyDataSetChanged();
    }
}
