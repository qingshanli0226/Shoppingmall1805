package com.shopmall.bawei.shopmall1805.fragment.classIfys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.content.Intent;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shopmall.bawei.shopmall1805.Adper.JsonCallbackInterface;
import com.shopmall.bawei.shopmall1805.Adper.classify.zi2Adper;
import com.shopmall.bawei.shopmall1805.Adper.classify.ziAdper;
import com.shopmall.bawei.shopmall1805.Adper.tliteadper;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.fragment.goShopActivity;


import java.util.ArrayList;
import java.util.List;

import framework.BaseFragment;
import framework.greendao.UserBeanc;
import framework.mvpc.jsonPresenter;
import mode.ClothesBean;


public
class Fragment_classify2 extends BaseFragment {
    private List<String> list = new ArrayList<>();
    private RecyclerView ShopcarOne;
    private RecyclerView ShopcarTow;
    private RecyclerView ShopcarThree;
    private com.shopmall.bawei.shopmall1805.Adper.tliteadper tliteadper;
    private ziAdper adper;
    private com.shopmall.bawei.shopmall1805.Adper.classify.zi2Adper zi2Adper;
    private List<ClothesBean.ResultBean.ChildBean> clothesBeans = new ArrayList<>();
    private List<ClothesBean.ResultBean.HotProductListBean> hotProductListBeans = new ArrayList<>();
    private int count = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("小裙子");
        list.add("上衣");
        list.add("夏装");
        list.add("外套");
        list.add("配件");
        list.add("包包");
        list.add("装扮");
        list.add("居家宅品");
        list.add("办公文具");
        list.add("数码周边");
        list.add("游戏专区");
    }

    @Override
    protected void createPresenter() {
        Presenter = new jsonPresenter(this);

    }
    @Override
    protected void OnClickListener() {

        tliteadper = new tliteadper(R.layout.fragment_personage,list);
        ShopcarOne.setAdapter(tliteadper);
        tliteadper.notifyDataSetChanged();
        tliteadper.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    count = position;
                    if (position==5){
                        count=0;
                    }
                    if (count<4){
                        clothesBeans.clear();
                        hotProductListBeans.clear();
                        Presenter.getshopcal(count,new JsonCallbackInterface(){
                            @Override
                            public void successClassifs(ClothesBean clothesBean) {
                                clothesBeans.addAll(clothesBean.getResult().get(0).getChild());
                                hotProductListBeans.addAll(clothesBean.getResult().get(0).getHot_product_list());
                                adper.notifyDataSetChanged();
                                zi2Adper.notifyDataSetChanged();
                            }

                            @Override
                            public void Error(String error) {
                                super.Error(error);
                            }
                        });
                    }

            }
        });

        adper = new ziAdper(R.layout.item_er_tlite,clothesBeans);
        ShopcarTow.setAdapter(adper);
        adper.notifyDataSetChanged();

        zi2Adper = new zi2Adper(R.layout.item_channel,hotProductListBeans);
        ShopcarThree.setAdapter(zi2Adper);
        zi2Adper.notifyDataSetChanged();

        adper.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                goShopBay(1,position);
            }
        });
        zi2Adper.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                goShopBay(4,position);
            }
        });
    }

    @Override
    protected void InitData() {
        ShopcarOne = (RecyclerView) findViewById(R.id.Shopcar_one);
        ShopcarTow = (RecyclerView) findViewById(R.id.Shopcar_tow);
        ShopcarThree = (RecyclerView)findViewById(R.id.Shopcar_three);

        ShopcarOne.setLayoutManager(new LinearLayoutManager(getContext()));
        ShopcarTow.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        ShopcarThree.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

        Presenter.getshopcal(count,new JsonCallbackInterface(){
            @Override
            public void successClassifs(ClothesBean clothesBean) {
                clothesBeans.addAll(clothesBean.getResult().get(0).getChild());
                hotProductListBeans.addAll(clothesBean.getResult().get(0).getHot_product_list());
            }

            @Override
            public void Error(String error) {
                super.Error(error);
            }
        });
    }
     private void goShopBay(int i, int position) {//购买
            UserBeanc usernv = null;
            if (i==1){
                ClothesBean.ResultBean.ChildBean ChildBean = clothesBeans.get(position);
                usernv = new UserBeanc(ChildBean.getName(),ChildBean.getParent_id(),ChildBean.getPic());
            }else if (i == 4){
                ClothesBean.ResultBean.HotProductListBean hotProductListBean = hotProductListBeans.get(position);
                usernv = new UserBeanc(hotProductListBean.getName(),hotProductListBean.getCover_price(),hotProductListBean.getFigure());
            }

            if (usernv !=null){
                Intent intent = new Intent(getContext(), goShopActivity.class);
                intent.putExtra("user",usernv);
                startActivity(intent);
            }
        }
    @Override
    protected int getlayoutId() {
        return R.layout.classifs2_fragment;
    }
}
