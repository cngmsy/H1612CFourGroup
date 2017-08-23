package com.jiyun.qcloud.dashixummoban.ui.first.goods.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.entity.car.RightListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by liuwangping on 2017/8/15.
 */


public class SettleCenterActivity extends BaseActivity{
    @BindView(R.id.sette_back)
    ImageButton setteBack;
    @BindView(R.id.settle_left)
    ImageView settleLeft;
    @BindView(R.id.rl_right)
    ImageView rlRight;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_label)
    TextView tvLabel;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_selected_address_container)
    LinearLayout llSelectedAddressContainer;
    @BindView(R.id.tv_select_address)
    TextView tvSelectAddress;
    @BindView(R.id.rl_location)
    RelativeLayout rlLocation;
    @BindView(R.id.pay_method)
    RelativeLayout payMethod;
    @BindView(R.id.daijin)
    RelativeLayout daijin;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.tv_seller_name)
    TextView tvSellerName;
    @BindView(R.id.ll_select_goods)
    LinearLayout llSelectGoods;
    @BindView(R.id.center_listview)
    RecyclerView centerListview;
    @BindView(R.id.tv_send_price)
    TextView tvSendPrice;
    @BindView(R.id.tv_count_price)
    TextView tvCountPrice;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    private List<RightListBean> list;
    private String string;

    @Override
    protected void initData() {

        Intent intent = getIntent();
        list = (List<RightListBean>) intent.getSerializableExtra("list");
        String num = intent.getStringExtra("num");
        tvCountPrice.setText("待支付￥" + num);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settle_center;
    }

    @OnClick({R.id.sette_back, R.id.rl_location, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sette_back:
                finish();
                break;
            case R.id.rl_location:
                break;
            case R.id.tv_submit:
                Toast.makeText(SettleCenterActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }


}