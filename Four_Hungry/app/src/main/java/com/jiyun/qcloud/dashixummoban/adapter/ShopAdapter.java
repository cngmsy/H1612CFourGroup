package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.car.RightListBean;

import java.util.List;


/**
 * Created by liuwangping on 2017/8/15.
 */

public class ShopAdapter extends BaseAdapter {
    private Context context;
    private List<RightListBean> list;

    public ShopAdapter(Context context, List<RightListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
      Holder holder;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.item_cart_goods, null);
            holder.img = view.findViewById(R.id.item_iv);
            holder.name = view.findViewById(R.id.item_tv_name);
            holder.newprice = view.findViewById(R.id.item_tv_price);
            holder.textNum = view.findViewById(R.id.item_tv_num);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        RightListBean shop= list.get(i);
        Glide.with(context).load(shop.getIcon()).into(holder.img);
        holder.name.setText(shop.getName());
        holder.newprice.setText("￥ " + shop.getNewPrice());
        holder.textNum.setText(shop.getNum() + "");
        return view;
    }

    class Holder {
        private ImageView img;
        private TextView  name,newprice,textNum;
    }

}