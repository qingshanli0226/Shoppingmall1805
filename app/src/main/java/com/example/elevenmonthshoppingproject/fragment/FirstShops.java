package com.example.elevenmonthshoppingproject.fragment;


import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elevenmonthshoppingproject.R;
import com.example.elevenmonthshoppingproject.adapter.BaseRVAdapter;
import com.example.elevenmonthshoppingproject.adapter.Recyadapter;
import com.example.elevenmonthshoppingproject.shop.ShopIView;
import com.example.elevenmonthshoppingproject.shop.ShopPresenterImp;
import com.example.net.BaseFragment;
import com.example.net.bean.LoginBean;
import com.example.net.bean.Recommonde;
import com.example.net.bean.RegisterBean;

import java.util.ArrayList;
import java.util.List;

public class FirstShops extends BaseFragment implements ShopIView.IShopView , BaseRVAdapter.IBaseRecyclerLinsterner {


    private RecyclerView recyCler;

    private Recyadapter recyadapter;
    private ShopPresenterImp shopPresenterImp;


    @Override
    protected int getlayoutid() {
        return R.layout.firstshopfragment;
    }
    @Override
    protected void iniView(View view) {
        shopPresenterImp=new ShopPresenterImp();
        shopPresenterImp.attatch(this);
        shopPresenterImp.getshop();
        recyCler = view.findViewById(R.id.recy_cler);
        recyCler.setLayoutManager(new LinearLayoutManager(getContext()));
        recyadapter=new Recyadapter();
        recyCler.setAdapter(recyadapter);


    }

    @Override
    protected void iniData() {

    }


    @Override
    public void onShopview(Recommonde recommonde) {
        Toast.makeText(getContext(), "请求数据"+recommonde, Toast.LENGTH_SHORT).show();
        List<Object> datelist = new ArrayList<>();
        datelist.add(recommonde.getBanner_info());
        datelist.add(recommonde.getChannel_info());
        datelist.add(recommonde.getAct_info());
        datelist.add(recommonde.getHot_info());
        datelist.add(recommonde.getRecommend_info());
        datelist.add(recommonde.getSeckill_info().getList());
        recyadapter.updatelist(datelist);
        recyadapter.setBaseRVAdapterlinterner(this);

    }

    @Override
    public void onregister(RegisterBean registerBean) {

    }

    @Override
    public void onlogin(LoginBean loginBean) {

    }


    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), "请求数据失败"+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemclick(int position) {
        Toast.makeText(getContext(), "点击了"+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shopPresenterImp.detachview();
    }
}
