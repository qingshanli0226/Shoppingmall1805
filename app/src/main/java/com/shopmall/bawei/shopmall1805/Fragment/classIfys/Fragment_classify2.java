package com.shopmall.bawei.shopmall1805.Fragment.classIfys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shopmall.bawei.shopmall1805.Adper.tliteadper;
import com.shopmall.bawei.shopmall1805.Adper.classify.zi2Adper;
import com.shopmall.bawei.shopmall1805.Adper.classify.ziAdper;
import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;

import framework.BaseFragment;
import framework.User2;
import framework.mvpc.jsonPresenter;
import mode.ClothesBean;

public
class Fragment_classify2 extends BaseFragment {
    private List<String> list = new ArrayList<>();
    private RecyclerView ShopcarOne;
    private RecyclerView ShopcarTow;
    private RecyclerView ShopcarThree;
    private tliteadper tliteadper;
    private ziAdper adper;
    private zi2Adper zi2Adper;
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
                        Presenter.getshopcal(count, new User2() {
                            @Override
                            public void Susses(ClothesBean e) {
                                clothesBeans.addAll(e.getResult().get(0).getChild());
                                hotProductListBeans.addAll(e.getResult().get(0).getHot_product_list());
                                Log.i("====","打印出来的的当前是"+hotProductListBeans.get(0).getName());
                                adper.notifyDataSetChanged();
                                zi2Adper.notifyDataSetChanged();
                            }

                            @Override
                            public void Error(String error) {

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

    }

    @Override
    protected void InitData(View inflate) {
        ShopcarOne = (RecyclerView) inflate.findViewById(R.id.Shopcar_one);
        ShopcarTow = (RecyclerView) inflate.findViewById(R.id.Shopcar_tow);
        ShopcarThree = (RecyclerView) inflate.findViewById(R.id.Shopcar_three);

        ShopcarOne.setLayoutManager(new LinearLayoutManager(getContext()));
        ShopcarTow.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        ShopcarThree.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

        Presenter.getshopcal(count, new User2() {
            @Override
            public void Susses(ClothesBean e) {
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
