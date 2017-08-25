package com.jiyun.qcloud.dashixummoban.ui.first.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.HomeBean;

import java.util.List;


/**
 * Created by my301s on 2017/8/12.
 */


public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeBean.BodyBean.SellerBean> list2;
    private List<String> list3;
    private List<HomeBean.BodyBean> list4;
    private OnItemClickLinear onItemClickLinear;

    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE, ITEM_TYPE_TEXT
    }

    public void setOnItemClickLinear(OnItemClickLinear onItemClickLinear) {
        this.onItemClickLinear = onItemClickLinear;
    }

    public interface OnItemClickLinear {
        public void onItemvlick(int position);
    }

    public HomeAdapter(FragmentActivity activity, List<HomeBean.BodyBean.SellerBean> list2, List<String> list3, List<HomeBean.BodyBean> list4) {
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        this.context = activity;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_seller, null);
            return new MyViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_home_string, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new MyView(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).tv.setText(list2.get(position).getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickLinear != null) {
                        onItemClickLinear.onItemvlick(position);
                    }
                }
            });

        } else if (holder instanceof MyView) {
            ((MyView) holder).textView.setText(list3.get(0));
            ((MyView) holder).textView2.setText(list3.get(1));
            ((MyView) holder).textView3.setText(list3.get(2));
            ((MyView) holder).textView4.setText(list3.get(3));
            ((MyView) holder).textView5.setText(list3.get(4));
            ((MyView) holder).textView6.setText(list3.get(5));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickLinear != null) {
                        onItemClickLinear.onItemvlick(position);
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return list4 == null ? 0 : list4.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_title);
        }
    }

    class MyView extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;

        public MyView(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text1);
            textView2 = (TextView) itemView.findViewById(R.id.text2);
            textView3 = (TextView) itemView.findViewById(R.id.text3);
            textView4 = (TextView) itemView.findViewById(R.id.text4);
            textView5 = (TextView) itemView.findViewById(R.id.text5);
            textView6 = (TextView) itemView.findViewById(R.id.text6);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list4.get(position).getType() == 0) {
            return ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
        }

    }

}
