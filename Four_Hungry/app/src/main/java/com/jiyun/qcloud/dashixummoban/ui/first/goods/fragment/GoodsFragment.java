package com.jiyun.qcloud.dashixummoban.ui.first.goods.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.LeftListAdapter;
import com.jiyun.qcloud.dashixummoban.adapter.RightListAdapter;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.car.BaseBean;
import com.jiyun.qcloud.dashixummoban.entity.car.GoodsBean;
import com.jiyun.qcloud.dashixummoban.entity.car.RightListBean;
import com.jiyun.qcloud.dashixummoban.ui.first.goods.activity.ShopCartActivity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liuwangping on 2017/8/13.
 */

public class GoodsFragment extends BaseFragment implements GoodsContract.View {
    @BindView(R.id.Left_listview)
    ListView leftListview;
    @BindView(R.id.goods_title)
    TextView goodsTitle;
    @BindView(R.id.right_listview)
    ListView rightListview;
    @BindView(R.id.fab)
    ImageView fab;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.shopping_cart_layout)
    FrameLayout shoppingCartLayout;
    @BindView(R.id.goods_relative)
    RelativeLayout goodsRelative;
    private GoodsContract.Presenter presenter;

    private int currentItem;
    private LeftListAdapter leftAdapter;
    private ArrayList<GoodsBean> list = new ArrayList<>();
    private RightListAdapter rightListAdapter;
    private ArrayList<RightListBean> rightlist = new ArrayList();
    //这个集合是右边所有要显示标题的条目的position
    private ArrayList<Integer> showTitle = new ArrayList<>();
    private ValueAnimator valueAnimator;
    private int i = 0;
    private int num;
    private List<RightListBean> carlist=new ArrayList<>();
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_goods;
    }

    @Override
    protected void initData() {
        if (presenter != null) {
            presenter.start();
        }

        leftAdapter = new LeftListAdapter(list, getContext());
        leftListview.setAdapter(leftAdapter);
        rightListAdapter = new RightListAdapter(getContext(), rightlist);
        rightListview.setAdapter(rightListAdapter);
        rightListAdapter.setDialogControl(new RightListAdapter.IDialogControl() {
            @Override
            public void getPosition(final View view, final int position) {
                for (int i = 0; i < rightlist.size(); i++) {
                    if (i == position) {
                        //点击的对应的item的bean数据源的num数据加1
                        //找到点击的item对应的bean对象
                        RightListBean bean = rightlist.get(i);
                        //让bean对象对应的数据加1
                        int k = bean.getNum() + 1;
                        //把加1后的数据赋值给我们点击的那个Bean对象
                        bean.setNum(k);
                    }
                }

                //一、创造出执行动画的主题---imageview
                //代码new一个imageview，图片资源是上面的imageview的图片
                // (这个图片就是执行动画的图片，从开始位置出发，经过一个抛物线（贝塞尔曲线），移动到购物车里)

                final ImageView imageView = new ImageView(getContext());
                goodsRelative.addView(imageView);
                imageView.setImageResource(R.mipmap.food_button_add);
                imageView.setVisibility(View.VISIBLE);

                //控制点（辅助点）
                int[] recyclerPosition = new int[2];
                //贝塞尔起始数据点
                int[] startPosition = new int[2];
                //贝塞尔结束数据点
                int[] endPosition = new int[2];

                // 二、计算动画开始/结束点的坐标的准备工作
                //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
                rightListview.getLocationInWindow(recyclerPosition);
                //得到商品图片的坐标（用于计算动画开始的坐标）
                view.getLocationInWindow(startPosition);
                //得到购物车图片的坐标(用于计算动画结束后的坐标)
                fab.getLocationInWindow(endPosition);


                final PointF startF = new PointF();
                final PointF endF = new PointF();
                PointF controllF = new PointF();

                startF.x = startPosition[0];
                startF.y = startPosition[1] - recyclerPosition[1];
                endF.x = endPosition[0];
                endF.y = endPosition[1] - recyclerPosition[1];
                controllF.x = endF.x;
                controllF.y = startF.y;
                imageView.setX(startF.x);
                imageView.setY(startF.y);

                valueAnimator = ValueAnimator.ofObject(new BezierTypeEvaluator(controllF), startF, endF);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        PointF pointF = (PointF) animation.getAnimatedValue();
                        imageView.setX(pointF.x);
                        imageView.setY(pointF.y);
                        Log.i("wangjtiao", "viewF:" + view.getX() + "," + view.getY());
                    }
                });

                ObjectAnimator objectAnimatorX = new ObjectAnimator().ofFloat(fab, "scaleX", 0.6f, 1.0f);
                ObjectAnimator objectAnimatorY = new ObjectAnimator().ofFloat(fab, "scaleY", 0.6f, 1.0f);
                objectAnimatorX.setInterpolator(new AccelerateInterpolator());
                objectAnimatorY.setInterpolator(new AccelerateInterpolator());
                AnimatorSet set = new AnimatorSet();
                set.play(objectAnimatorX).with(objectAnimatorY).after(valueAnimator);
                set.setDuration(800);
                set.start();

                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        // 购物车的数量加1
                        i++;
                        count.setText(String.valueOf(i));
                        String ss = String.valueOf(i);
                        if ("0".equals(ss)) {
                            count.setVisibility(View.GONE);
                        } else {
                            count.setVisibility(View.VISIBLE);

                        }

                        // 把移动的图片imageview从父布局里移除
                        goodsRelative.removeView(imageView);


                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });

                rightListAdapter.notifyDataSetChanged();

            }

            @Override
            public void getRemovePosition(final View view, final int position) {
                for (int i = 0; i < rightlist.size(); i++) {
                    if (i == position) {
                        //点击的对应的item的bean数据源的num数据加1
                        //找到点击的item对应的bean对象
                        RightListBean bean = rightlist.get(i);
                        //让bean对象对应的数据加1
                        int k = bean.getNum() - 1;
                        //把加1后的数据赋值给我们点击的那个Bean对象
                        bean.setNum(k);
                    }
                }
                i--;
                String ss = String.valueOf(i);
                count.setText(ss);
                if ("0".equals(ss)) {
                    count.setVisibility(View.GONE);
                } else {
                    count.setVisibility(View.VISIBLE);
                }

                rightListAdapter.notifyDataSetChanged();
            }
        });



    }

    @Override
    protected void initView(View view) {


    }

    @Override
    public void setBundle(Bundle bundle) {

    }


    @OnClick(R.id.shopping_cart_layout)
    public void onViewClicked() {
        carlist.clear();
        for (int i=0;i<rightlist.size();i++){
            num = rightlist.get(i).getNum();
            if(num>0) {
                RightListBean rightListBean=new RightListBean();
                rightListBean.setNum(rightlist.get(i).getNum());
                rightListBean.setName(rightlist.get(i).getName());
                BigDecimal bg = new BigDecimal(rightlist.get(i).getNewPrice());
                double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                rightListBean.setNewPrice(f1);
                carlist.add(rightListBean);
            }
        }
        Intent intent=new Intent(getActivity(), ShopCartActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) carlist);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showShop(BaseBean baseBean) {

        Gson gson=new Gson();
        list.addAll((Collection<? extends GoodsBean>) gson.fromJson(baseBean.getData(), new TypeToken<ArrayList<GoodsBean>>() {
        }.getType()));
        for (int a = 0; a < list.size(); a++) {
            for (int i = 0; i < list.get(a).getList().size(); i++) {
                rightlist.add(new RightListBean(list.get(a).getName(),
                        list.get(a).getList().get(i).getForm(),
                        list.get(a).getList().get(i).getIcon(),
                        list.get(a).getList().get(i).getId(),
                        list.get(a).getList().get(i).getMonthSaleNum(),
                        list.get(a).getList().get(i).getName(),
                        list.get(a).getList().get(i).getNewPrice(),
                        list.get(a).getList().get(i).getOldPrice()
                        , i, 0));
            }
        }

        for (int i = 0; i < rightlist.size(); i++) {
            if (i == 0) {//第一个必须显示
                showTitle.add(i);
                System.out.println(i + "dd");
            } else if (!TextUtils.equals(rightlist.get(i).getAname(), rightlist.get(i - 1).getAname())) {//如果跟上一个条目的type不一样就必须显示
                showTitle.add(i);
                System.out.println(i + "dd");
            }
        }
        leftAdapter.notifyDataSetChanged();
        rightListAdapter.notifyDataSetChanged();



    }

    @Override
    public void listener() {
        leftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                leftAdapter.setSelectItem(i);
                leftAdapter.notifyDataSetInvalidated();
                rightListview.setSelection(showTitle.get(i));
                goodsTitle.setText(list.get(i).getName());
                goodsTitle.setVisibility(View.GONE);

            }
        });

        rightListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int scrollState;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.scrollState = scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    return;
                }
                Log.d("jiejie", "onScroll" + "  firstVisibleItem" + firstVisibleItem
                        + "  visibleItemCount" + visibleItemCount + "  totalItemCount" + totalItemCount);
                int current = showTitle.indexOf(firstVisibleItem);//当前选中的一级条目的position
                System.out.println(current + "dd" + firstVisibleItem);
                if (currentItem != current && current >= 0) {
                    currentItem = current;
                    goodsTitle.setVisibility(View.VISIBLE);
                    goodsTitle.setText(list.get(current).getName());
                    leftAdapter.setSelectItem(currentItem);
                    leftAdapter.notifyDataSetInvalidated();
                }
            }
        });
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
    public void setPresenter(GoodsContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
