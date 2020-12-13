package com.shopmall.bawei.shopmall1805.home.fragment.classIfys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.content.Intent;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shopmall.bawei.shopmall1805.Adper.classify.ClassIfyblowAdaper;
import com.shopmall.bawei.shopmall1805.Adper.classify.ClassifyUpAdaper;
import com.shopmall.bawei.shopmall1805.Adper.classify.TliteHeadlineAdper;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.GoShopActivity;
import com.shopmall.bawei.shopmall1805.home.fragment.jsonCallBack.JsonDataBack;

import java.util.ArrayList;
import java.util.List;

import framework.BaseFragment;


import framework.mvpc.JsonPresenter;
import mode.ClothesBean;
import mode.ShopcarBean;
import view.loadinPage.ErrorBean;
import view.ToolBar;

public
class FragmentClassify2 extends BaseFragment<JsonPresenter> implements ToolBar.IToolBarClickListner {
    private List<String> list = new ArrayList<String>();
    private RecyclerView shopcarOne;
    private RecyclerView shopcarTow;
    private RecyclerView shopcarThree;
    private TliteHeadlineAdper tliteAdper;
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
        presenter = new JsonPresenter(this);

    }
    @Override
    protected void OnClickListener() {

        tliteAdper = new TliteHeadlineAdper(R.layout.item_tlite,list);
        shopcarOne.setAdapter(tliteAdper);
        tliteAdper.notifyDataSetChanged();
        tliteAdper.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        count = position;
                        clothesBeans.clear();
                        hotProductListBeans.clear();
                        presenter.getshopcal(count, new JsonDataBack(){
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
        shopcarTow.setAdapter(classifyUpAdaper);
        classifyUpAdaper.notifyDataSetChanged();
        classifyUpAdaper.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                goShopBay(1,position);
            }
        });

        classIfyblowAdaper = new ClassIfyblowAdaper(R.layout.item_channel,hotProductListBeans);
        shopcarThree.setAdapter(classIfyblowAdaper);
        classIfyblowAdaper.notifyDataSetChanged();

        classIfyblowAdaper.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                goShopBay(2,position);
            }
        });

    }

    @Override
    protected void InitData() {
        shopcarOne = (RecyclerView) findViewById(R.id.Shopcar_one);
        shopcarTow = (RecyclerView) findViewById(R.id.Shopcar_tow);
        shopcarThree = (RecyclerView) findViewById(R.id.Shopcar_three);
        tooBar = (ToolBar) findViewById(R.id.tooBar);

        shopcarOne.setLayoutManager(new LinearLayoutManager(getContext()));
        shopcarTow.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        shopcarThree.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

        presenter.getshopcal(count, new JsonDataBack() {
            @Override
            public void clothesBean(ClothesBean e) {
                clothesBeans.addAll(e.getResult().get(0).getChild());
                hotProductListBeans.addAll(e.getResult().get(0).getHot_product_list());
            }

            @Override
            public void Error(String error) {

            }
        });

        presenter.getshopcal(count,new JsonDataBack(){
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

    private void goShopBay(int i, int position) {//购买
            ShopcarBean shopcarBean = null;
            ClothesBean.ResultBean.HotProductListBean hotProductListBean = hotProductListBeans.get(position);
                shopcarBean = new ShopcarBean(hotProductListBean.getBrand_id()
                        ,hotProductListBean.getName()
                        ,"1",
                        hotProductListBean.getFigure()
                        ,hotProductListBean.getCover_price()
                        ,true);
            if (shopcarBean !=null){
                Intent intent = new Intent(getContext(), GoShopActivity.class);
                intent.putExtra("user",shopcarBean);
                startActivity(intent);
            }
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_classifyc;
    }

    @Override
    public void showLoaDing() {
        showLoaDing();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {
        showEmpty();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}
