package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.car.RightListBean;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by liuwangping on 2017/8/14.
 */

public class RightListAdapter extends BaseAdapter {
    private IDialogControl dialogControl;
    private Context context;
    private ArrayList<RightListBean> list;
    private int rightItem = 0;

    public RightListAdapter(Context context, ArrayList<RightListBean> list) {
        this.context = context;
        this.list = list;
    }
    public void setDialogControl(IDialogControl dialogControl) {
        this.dialogControl = dialogControl;
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
        final Holder holder;
        if(view==null) {
            holder=new Holder();
            view= LayoutInflater.from(context).inflate(R.layout.item_rv_goods,null);
            holder.img=view.findViewById(R.id.iv_icon);
            holder.title=view.findViewById(R.id.textItem);
            holder.name=view.findViewById(R.id.tv_name);
            holder.form=view.findViewById(R.id.tv_zucheng);
            holder.num=view.findViewById(R.id.tv_yueshaoshounum);
            holder.newprice=view.findViewById(R.id.tv_newprice);
            holder.oldprice=view.findViewById(R.id.tv_oldprice);
            holder.remove=view.findViewById(R.id.ib_minus);
            holder.add=view.findViewById(R.id.ib_add);
            holder.textNum=view.findViewById(R.id.tv_money);
            view.setTag(holder);
        }else {
            holder = (Holder) view.getTag();
        }
        final RightListBean twoListBean = list.get(i);
        holder.title.setText(twoListBean.getAname());
        Glide.with(context).load(twoListBean.getIcon()).into(holder.img);
        holder.name.setText(twoListBean.getName());
        holder.form.setText(twoListBean.getForm());
        holder.num.setText("月售"+twoListBean.getMonthSaleNum()+"份");
        DecimalFormat df = new DecimalFormat("######0.00");
        String format = df.format(twoListBean.getNewPrice());
        holder.newprice.setText("￥"+format);
        holder.oldprice.setText("￥"+twoListBean.getOldPrice());
        holder.textNum.setText(twoListBean.getNum()+"");
        String num = String.valueOf(twoListBean.getNum());
        if(num.equals("0")) {
            holder.textNum.setVisibility(View.GONE);
            holder.remove.setVisibility(View.GONE);
        }else {
            holder.textNum.setVisibility(View.VISIBLE);
            holder.remove.setVisibility(View.VISIBLE);
        }

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogControl.getPosition(view,i);
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogControl.getRemovePosition(view,i);
            }
        });

        if(i ==0){//如果是第一个  需要显示标题
            holder.title.setVisibility(View.VISIBLE);
        }else if (!TextUtils.equals(list.get(i).getAname(),list.get(i -1).getAname())) {
            //如果这个标题和上一个不一样   也需要将标题显示出来
            holder.title.setVisibility(View.VISIBLE);
        }else {
            holder.title.setVisibility(View.GONE);
        }
        return view;
    }
    class Holder{
        private ImageView img;
        private TextView title,name,form,num,newprice,oldprice,textNum;
        private ImageButton remove,add;

    }

    // 内部接口
    public interface IDialogControl {
         void getPosition(View view, int position);
        void getRemovePosition(View view, int position);
    }

}
