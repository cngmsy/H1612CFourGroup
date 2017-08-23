package com.jiyun.qcloud.dashixummoban.ui.more;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.MovieBean;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Administrator on 2017/8/22.
 */

public class MyAdapter extends BaseAdapter{
    private Context context;
    private List<MovieBean.DataBean.TrailersBean>  datalist;
    private SetView puView;
    public MyAdapter(Context context, List<MovieBean.DataBean.TrailersBean> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int i) {
        return datalist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder   holder = null;
        if (view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_movie,null);
            holder.videoPlayer = view.findViewById(R.id.jcvideoplayer);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.itemview = view;
        holder.itemview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                puView.putView(view);
                return false;
            }
        });
        MovieBean.DataBean.TrailersBean trailersBean = datalist.get(i);
        holder.videoPlayer.setThumbImageViewScalType(ImageView.ScaleType.FIT_XY);
        holder.videoPlayer.setUp(trailersBean.getHightUrl(),trailersBean.getVideoTitle());
        Glide.with(context).load(trailersBean.getCoverImg()).into(holder.videoPlayer.ivThumb);
        return view;
    }
    public interface SetView{
        void  putView(View view);
    }
    public void getView(SetView setView){
        this.puView = setView;
    }
    class ViewHolder{
        View itemview;
        JCVideoPlayer  videoPlayer;

    }
}
