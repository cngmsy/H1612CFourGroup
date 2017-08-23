package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.car.GoodsBean;

import java.util.ArrayList;


/**
 * Created by liuwangping on 2017/8/13.
 */

public class LeftListAdapter extends BaseAdapter {
    private ArrayList<GoodsBean> list;
    private Context context;
    private int selectItem = 0;

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    public LeftListAdapter(ArrayList<GoodsBean> list, Context context) {
        this.list = list;
        this.context = context;

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            holder = new Holder();
           view = LayoutInflater.from(context).inflate(
                    R.layout.item_list_goods, null);
            holder.name = (TextView)view
                    .findViewById(R.id.item_list_name);
            view.setTag(holder);
        } else {
            holder = (Holder)view.getTag();
        }
        holder.name.setText(list.get(i).getName());

        if(i== selectItem){
            holder.name.setBackgroundColor(Color.WHITE);
            holder.name.setTextColor(context.getResources().getColor(R.color.gray));
        }else {
            holder.name.setBackgroundColor(context.getResources().getColor(R.color.gray3));
            holder.name.setTextColor(context.getResources().getColor(R.color.gray));
        }
        return view;
    }
    class Holder{
        TextView name;
    }
}
