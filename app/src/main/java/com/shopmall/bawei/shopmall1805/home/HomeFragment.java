package com.shopmall.bawei.shopmall1805.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.net.bean.HomeFragmentBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.base.BaseFragment;
import com.shopmall.bawei.shopmall1805.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.home.presenter.HomePresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<HomePresenter, HomeContract.HomeView> implements com.shopmall.bawei.shopmall1805.home.contract.HomeContract.IHomeView {

    private Banner banner;
    private List<Integer> images = new ArrayList<>();
    private RecyclerView homeRv;
    private List<HomeFragmentBean.ResultBean> list = new ArrayList<>();
    private HomeListAdapter homeListAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        banner = view.findViewById(R.id.banner);
        homeRv = view.findViewById(R.id.rv);



        images.add(R.drawable.ic_launcher_background);
        images.add(R.drawable.ic_launcher_background);
        images.add(R.drawable.ic_launcher_background);
        images.add(R.drawable.ic_launcher_background);
        banner.setImages(images);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.start();


        homeListAdapter = new HomeListAdapter(list);
        homeRv.setAdapter(homeListAdapter);
        homeRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        httpPresenter = new HomePresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        httpPresenter.onGetHomeData();
    }

    @Override
    public void onOk(HomeFragmentBean homeFragmentBean) {
        list.add(homeFragmentBean.getResult());
        homeListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String message) {

    }
}
