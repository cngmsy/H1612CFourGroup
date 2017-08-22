package com.jiyun.qcloud.dashixummoban.ui.first.goods.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.ShopAdapter;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.entity.car.RightListBean;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liuwangping on 2017/8/15.
 */

public class ShopCartActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.cart_shopping_listview)
    ListView cartShoppingListview;
    @BindView(R.id.totalize)
    TextView totalize;
    @BindView(R.id.jiesuan_button)
    Button jiesuanButton;

    private List<RightListBean> list;
    private List<RightListBean> mlist = new ArrayList<>();
    private ShopAdapter adapter;
    float sum=0;
    private String format;
    @Override
    protected void initData() {
        Intent intent = getIntent();
        list = (List<RightListBean>) intent.getSerializableExtra("list");
        mlist.addAll(list);
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i).getNum();
            double newPrice = list.get(i).getNewPrice();
            sum += num * newPrice;
            DecimalFormat df = new DecimalFormat("######0.00");
            format = df.format(sum);
            totalize.setText(format);
        }
        adapter = new ShopAdapter(ShopCartActivity.this, mlist);
        cartShoppingListview.setAdapter(adapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopcart;
    }


    @OnClick({R.id.iv_back, R.id.jiesuan_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.jiesuan_button:
//                new OrderPresenter(new SettleCenterActivity());
                Intent intent=new Intent(ShopCartActivity.this,SettleCenterActivity.class);
                intent.putExtra("list", (Serializable) mlist);
                intent.putExtra("num",format);
                startActivity(intent);
                break;
        }
    }
}
