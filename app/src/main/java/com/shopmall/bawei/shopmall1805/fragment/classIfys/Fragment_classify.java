package com.shopmall.bawei.shopmall1805.fragment.classIfys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shopmall.bawei.shopmall1805.Adper.classify.ClassIfyblowAdaper;
import com.shopmall.bawei.shopmall1805.Adper.classify.ClassifyUpAdaper;
import com.shopmall.bawei.shopmall1805.Adper.classify.Tliteadper;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.fragment.jsonCallBack.JsonDataBack;

import java.util.ArrayList;
import java.util.List;

import framework.BaseFragment;
import framework.mvpc.jsonPresenter;
import mode.ClothesBean;

public
class Fragment_classify extends BaseFragment {
    private List<String> list = new ArrayList<>();
    private RecyclerView ShopcarOne;
    private RecyclerView ShopcarTow;
    private RecyclerView ShopcarThree;
    private Tliteadper tliteadper;
    private ClassifyUpAdaper classifyUpAdaper;
    private ClassIfyblowAdaper classIfyblowAdaper;
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

        tliteadper = new Tliteadper(R.layout.fragment_personage,list);
        ShopcarOne.setAdapter(tliteadper);
        tliteadper.notifyDataSetChanged();
        tliteadper.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        count = position;
                        clothesBeans.clear();
                        hotProductListBeans.clear();
                        Presenter.getshopcal(count, new JsonDataBack(){
                            @Override
                            public void clothesBean(ClothesBean e) {
                                clothesBeans.addAll(e.getResult().get(0).getChild());
                                hotProductListBeans.addAll(e.getResult().get(0).getHot_product_list());
                                Log.i("====","打印出来的的当前是"+hotProductListBeans.get(0).getName());
                                classifyUpAdaper.notifyDataSetChanged();
                                classIfyblowAdaper.notifyDataSetChanged();
                            }
                        });


            }
        });

        classifyUpAdaper = new ClassifyUpAdaper(R.layout.item_er_tlite,clothesBeans);
        ShopcarTow.setAdapter(classifyUpAdaper);
        classifyUpAdaper.notifyDataSetChanged();


        classIfyblowAdaper = new ClassIfyblowAdaper(R.layout.item_channel,hotProductListBeans);
        ShopcarThree.setAdapter(classIfyblowAdaper);
        classIfyblowAdaper.notifyDataSetChanged();

    }

    @Override
    protected void InitData() {
        ShopcarOne = (RecyclerView) findViewById(R.id.Shopcar_one);
        ShopcarTow = (RecyclerView) findViewById(R.id.Shopcar_tow);
        ShopcarThree = (RecyclerView) findViewById(R.id.Shopcar_three);

        ShopcarOne.setLayoutManager(new LinearLayoutManager(getContext()));
        ShopcarTow.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        ShopcarThree.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

        Presenter.getshopcal(count, new JsonDataBack() {
            @Override
            public void clothesBean(ClothesBean e) {
                clothesBeans.addAll(e.getResult().get(0).getChild());
                hotProductListBeans.addAll(e.getResult().get(0).getHot_product_list());
            }

            @Override
            public void Error(String error) {

            }
        });
    }

    @Override
    protected int getlayoutId() {
        return R.layout.classifs2_fragment;
    }
}
