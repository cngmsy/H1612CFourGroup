package com.jiyun.qcloud.dashixummoban.ui.first.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.HomeBean;

import java.util.List;


/**
 * Created by my301s on 2017/8/13.
 */

public class GridHomeAdapter extends RecyclerView.Adapter <GridHomeAdapter.MyViewHolder>{
    private Context context;
    private List<HomeBean.HeadBean.CategorieListBean> categorieListBeen;

    public GridHomeAdapter(Context context, List<HomeBean.HeadBean.CategorieListBean> categorieListBeen) {
        this.context = context;
        this.categorieListBeen = categorieListBeen;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name_home.setText(categorieListBeen.get(position).getName());
        Glide.with(context).load(categorieListBeen.get(position).getPic()).into(holder.img_home);
    }

    @Override
    public int getItemCount() {
        return categorieListBeen.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView img_home;
        private final TextView name_home;

        public MyViewHolder(View itemView) {
            super(itemView);
            img_home = itemView.findViewById(R.id.img_home);
            name_home = itemView.findViewById(R.id.name_home);
        }
    }
}
