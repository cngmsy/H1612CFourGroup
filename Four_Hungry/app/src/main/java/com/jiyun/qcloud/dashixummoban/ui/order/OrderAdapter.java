package com.jiyun.qcloud.dashixummoban.ui.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.orderbean.OrderData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/23.
 */

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<OrderData.SellerBean> ordeList;

    public OrderAdapter(Context context, List<OrderData.SellerBean> ordeList) {
        this.context = context;
        this.ordeList = ordeList;
    }

    @Override
    public int getCount() {
        return ordeList.size();
    }

    @Override
    public Object getItem(int i) {
        return ordeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder   holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_order_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        OrderData.SellerBean sellerBean = ordeList.get(i);
        Glide.with(context).load(sellerBean.getPic()).into(holder.icon);
        holder.ordername.setText(sellerBean.getName());
        holder.tvOrderItemFoods.setText("红烧肉等"+sellerBean.getScore()+"件商品");
        return view;
    }

     class ViewHolder {
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.ordername)
        TextView ordername;
        @BindView(R.id.tv_order_item_type)
        TextView tvOrderItemType;
        @BindView(R.id.tv_order_item_time)
        TextView tvOrderItemTime;
        @BindView(R.id.tv_order_item_foods)
        TextView tvOrderItemFoods;
        @BindView(R.id.tv_order_item_money)
        TextView tvOrderItemMoney;
        @BindView(R.id.tv_order_item_multi_function)
        TextView tvOrderItemMultiFunction;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
