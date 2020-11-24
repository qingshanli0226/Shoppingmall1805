package com.shopmall.bawei.shopmall1805.activity.ui.fragment_main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.shopmall1805.activity.base.BaseFragment;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.mvptest.presenter.HomePresenter;
import com.shopmall.bawei.net.bean.HomeData;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.activity.adapter.HomeNewAdapter;
import com.shopmall.bawei.shopmall1805.activity.adapter.HomeShoppingAdapter;
import com.shopmall.bawei.shopmall1805.activity.adapter.HomeXinAdapter;
import com.shopmall.bawei.shopmall1805.activity.adapter.HometitleAdapter;
import com.shopmall.bawei.shopmall1805.activity.banner.Bannerlun;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomePresenter> implements Constart.HomeConstartView {
    private List<String> image=new ArrayList<>();
    private List<String> image2=new ArrayList<>();
    private Banner bannerF1;
    private HometitleAdapter hometitleAdapter;
    private RecyclerView titleF1;
    private Banner banner2F1;
    private RecyclerView homeRecycleGou;
    private RecyclerView homeRecycleNew;
    private ArrayList<HomeData.ResultBean.ChannelInfoBean> channel=new ArrayList<>();
    private HomeNewAdapter homeNewAdapter;
    private HomeXinAdapter homeXinAdapter;
    private RecyclerView homeRecycleShopping;
    private HomeShoppingAdapter homeShoppingAdapter;
    @Override
    protected void createViewid(View inflate) {

        bannerF1 = inflate.findViewById(R.id.banner_f1);

        homeRecycleGou = inflate.findViewById(R.id.home_recycle_gou);

        titleF1 = inflate.findViewById(R.id.title_f1);
        banner2F1 = inflate.findViewById(R.id.banner2_f1);

        homeRecycleNew = inflate.findViewById(R.id.home_recycle_new);

        homeRecycleShopping = inflate.findViewById(R.id.home_recycle_shopping);

    }

    @Override
    protected void createEnvent() {
          bannerF1.setOnBannerListener(new OnBannerListener() {
              @Override
              public void OnBannerClick(int position) {
                  Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
              }
          });
    }

    @Override
    protected void createData() {


            mPresenter.homec(Constants.HOME_URL2);
            titleF1.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
            homeRecycleGou.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
            homeRecycleNew.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
            homeRecycleShopping.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

    }

    @Override
    protected int fragmentid() {
        return R.layout.fragment_home;
    }

    @Override
    protected void createPresenter() {
           mPresenter=new HomePresenter(this);
    }

    @Override
    public void Success(Object... objects) {

        HomeData homeData = (HomeData) objects[0];
        Toast.makeText(getContext(), ""+homeData, Toast.LENGTH_SHORT).show();
        for (int i = 0; i <homeData.getResult().getAct_info().size() ; i++) {

            HomeData.ResultBean.ActInfoBean actInfoBean = homeData.getResult().getAct_info().get(i);
            image.add(Constants.BASE_URl_IMAGE+actInfoBean.getIcon_url());
        }

        for (int i = 0; i <homeData.getResult().getBanner_info().size() ; i++) {
            HomeData.ResultBean.BannerInfoBean bannerInfoBean = homeData.getResult().getBanner_info().get(i);
            image2.add(Constants.BASE_URl_IMAGE+bannerInfoBean.getImage());
        }

        Bannerlun.getBannerlun().bann(bannerF1,image2);
        Bannerlun.getBannerlun().bann(banner2F1,image);

        hometitleAdapter=new HometitleAdapter(R.layout.home1buju,homeData.getResult().getChannel_info());
        titleF1.setAdapter(hometitleAdapter);

        homeNewAdapter=new HomeNewAdapter(R.layout.home_gou,homeData.getResult().getSeckill_info().getList());
        homeRecycleGou.setAdapter(homeNewAdapter);

        homeXinAdapter=new HomeXinAdapter(R.layout.home_new,homeData.getResult().getRecommend_info());
        homeRecycleNew.setAdapter(homeXinAdapter);

        homeShoppingAdapter=new HomeShoppingAdapter(R.layout.hone_shopping_buju,homeData.getResult().getHot_info());
        homeRecycleShopping.setAdapter(homeShoppingAdapter);

    }

    @Override
    public void Error(String s) {
        Toast.makeText(getContext(), "111"+s, Toast.LENGTH_SHORT).show();
    }
}
