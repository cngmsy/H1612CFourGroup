package com.jiyun.qcloud.dashixummoban.ui.more;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.MovieBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * A simple {@link Fragment} subclass.
 */
public class MorePageFragment extends BaseFragment implements MovieContract.View {


    Unbinder unbinder;
    @BindView(R.id.movielist)
    ListView movielistview;
    Unbinder unbinder1;
    private MovieContract.Presenter presenter;
    private List<MovieBean.DataBean.TrailersBean> movielist = new ArrayList<>();
    private List<String> imagelist = new ArrayList<>();
    private MyAdapter adapter;
    private View inflate;
    private Banner banner;
    private JCVideoPlayer videoPlayer;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_more_page;
    }

    @Override
    protected void initData() {
        presenter.start();

    }

    @Override
    protected void initView(View view) {
        adapter = new MyAdapter(getActivity(), movielist);
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.headview, null);
        movielistview.addHeaderView(inflate);
        movielistview.setAdapter(adapter);


    }

    @Override
    public void setBundle(Bundle bundle) {

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
    public void setPresenter(MovieContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMoviedata(MovieBean movieBean) {
        List<MovieBean.DataBean.TrailersBean> trailers = movieBean.getData().getTrailers();
        for (int i = 0; i < trailers.size(); i++) {
            MovieBean.DataBean.TrailersBean trailersBean = trailers.get(i);
            String coverImg = trailersBean.getCoverImg();
            if (imagelist.size()<5){
                imagelist.add(coverImg);
            }
        }
        movielist.addAll(trailers);
        banner = inflate.findViewById(R.id.mybanner);
        banner.setImages(imagelist);
        banner.setImageLoader(new MyImageLoader());
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();
        adapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        JCVideoPlayer.releaseAllVideos();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(getActivity()).load(path).into(imageView);
        }
    }
}

