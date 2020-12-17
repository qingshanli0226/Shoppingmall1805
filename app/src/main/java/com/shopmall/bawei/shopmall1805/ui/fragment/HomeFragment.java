package com.shopmall.bawei.shopmall1805.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.base.BaseFragment;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.manager.GreendaoManager;
import com.shopmall.bawei.framework.mvptest.presenter.HomePresenter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.homeadapter.PrimereAdpter;
import com.shopmall.bawei.shopmall1805.ui.activity.HoneMessageActivity;
import com.shopmall.bean.HomeData;

public class HomeFragment extends BaseFragment<HomePresenter> implements Constant.HomeConstartView,GreendaoManager.ImessageChangeListener {

    private TextView homeTishi;
    private TextView honeMessage;
    private RecyclerView recycleHome;
    private PrimereAdpter primereAdpter;

    @Override
    protected void createViewid(View inflate) {



        homeTishi = inflate.findViewById(R.id.home_tishi);

        recycleHome = inflate.findViewById(R.id.recycleHome);
        honeMessage = inflate.findViewById(R.id.hone_message);


    }

    @Override
    protected void createEnvent() {
         homeTishi.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                startActivity(new Intent(getContext(), HoneMessageActivity.class));
             }
         });
    }

    @Override
    protected void createData() {


            mPresenter.homec(Constants.HOME_URL2,logingPage);

        recycleHome.setLayoutManager(new LinearLayoutManager(getContext()));

        int getcount = GreendaoManager.getInstance().getcount();
        if (getcount==0){
            honeMessage.setVisibility(View.GONE);
        }else {
            honeMessage.setVisibility(View.VISIBLE);
            honeMessage.setText(getcount+"");
        }


    }

    @Override
    protected int fragmentid() {
        return R.layout.home_fragment;
    }

    @Override
    protected void createPresenter() {
           mPresenter=new HomePresenter(this);
           GreendaoManager.getInstance().registmessageChangeListener(this);

    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void getMessageCount(boolean flg){
//        Log.e("1111",""+flg);
//
//    }



    @Override
    public void Success(Object... objects) {

           HomeData homeData = (HomeData) objects[0];
           primereAdpter=new PrimereAdpter();
         primereAdpter.addOneData(homeData.getResult().getBanner_info());
         primereAdpter.addOneData(homeData.getResult().getChannel_info());
         primereAdpter.addOneData(homeData.getResult().getAct_info());
         primereAdpter.addOneData(homeData.getResult().getSeckill_info().getList());
         primereAdpter.addOneData(homeData.getResult().getRecommend_info());
         primereAdpter.addOneData(homeData.getResult().getHot_info());
        recycleHome.setAdapter(primereAdpter);
//        Toast.makeText(getContext(), ""+homeData, Toast.LENGTH_SHORT).show();
//        for (int i = 0; i <homeData.getResult().getAct_info().size() ; i++) {
//
//            HomeData.ResultBean.ActInfoBean actInfoBean = homeData.getResult().getAct_info().get(i);
//            image.add(Constants.BASE_URl_IMAGE+actInfoBean.getIcon_url());
//        }
//
//        for (int i = 0; i <homeData.getResult().getBanner_info().size() ; i++) {
//            HomeData.ResultBean.BannerInfoBean bannerInfoBean = homeData.getResult().getBanner_info().get(i);
//            image2.add(Constants.BASE_URl_IMAGE+bannerInfoBean.getImage());
//        }
//
//        Bannerlun.getBannerlun().bann(bannerF1,image2);
//
//        banner2F1.isAutoPlay(false);
//        banner2F1.setBannerStyle(BannerConfig.NOT_INDICATOR);
//        Bannerlun.getBannerlun().bann(banner2F1,image);

//        hometitleAdapter=new HometitleAdapter(R.layout.home1buju,homeData.getResult().getChannel_info());
//        titleF1.setAdapter(hometitleAdapter);
//
//        homeNewAdapter=new HomeNewAdapter(R.layout.home_gou,homeData.getResult().getSeckill_info().getList());
//        homeRecycleGou.setAdapter(homeNewAdapter);
//
//        homeXinAdapter=new HomeXinAdapter(R.layout.home_new,homeData.getResult().getRecommend_info());
//        homeRecycleNew.setAdapter(homeXinAdapter);
//
//        homeShoppingAdapter=new HomeShoppingAdapter(R.layout.hone_shopping_buju,homeData.getResult().getHot_info());
//        homeRecycleShopping.setAdapter(homeShoppingAdapter);



//        homeXinAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                HomeData.ResultBean.RecommendInfoBean recommendInfoBean = homeData.getResult().getRecommend_info().get(position);
//                DetailsData detailsData=new DetailsData(recommendInfoBean.getProduct_id(),recommendInfoBean.getName(),recommendInfoBean.getCover_price(),Constants.BASE_URl_IMAGE+recommendInfoBean.getFigure());
//
//                Intent intent = new Intent(getContext(), DetailsMainActivity.class);
//                intent.putExtra("details",detailsData);
//                startActivity(intent);
//            }
//        });
//
//        homeShoppingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//                HomeData.ResultBean.HotInfoBean hotInfoBean = homeData.getResult().getHot_info().get(position);
//                DetailsData detailsData=new DetailsData(hotInfoBean.getProduct_id(),hotInfoBean.getName(),hotInfoBean.getCover_price(),Constants.BASE_URl_IMAGE+hotInfoBean.getFigure());
//                Intent intent = new Intent(getContext(), DetailsMainActivity.class);
//                intent.putExtra("details",detailsData);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // EventBus.getDefault().unregister(this);
        GreendaoManager.getInstance().unmessageChangeListener(this);
    }

    @Override
    public void Error(String s) {
        Toast.makeText(getContext(), "111"+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onupdatemessage() {
        int getcount = GreendaoManager.getInstance().getcount();
        if (getcount==0){
            honeMessage.setVisibility(View.GONE);
        }else {
            honeMessage.setVisibility(View.VISIBLE);
            honeMessage.setText(getcount+"");
        }
    }
}
