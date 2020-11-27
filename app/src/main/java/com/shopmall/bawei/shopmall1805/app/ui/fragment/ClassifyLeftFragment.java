package com.shopmall.bawei.shopmall1805.app.ui.fragment;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.adapter.fenlei.ClassifyRightAdapter;
import com.shopmall.bawei.shopmall1805.app.adapter.fenlei.ClassifyLeftAdapter;
import com.shopmall.bawei.shopmall1805.app.contract.ClassifyLeftContract;
import com.shopmall.bawei.shopmall1805.app.presenter.ClassifyLeftPresenterImpl;
import com.shopmall.bawei.shopmall1805.common.ClothesBean;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.framework.BaseFragment;
import com.shopmall.bawei.shopmall1805.framework.BaseMVPFragment;
import com.shopmall.bawei.shopmall1805.framework.ErrorBean;

import java.util.ArrayList;
import java.util.List;


public class ClassifyLeftFragment extends BaseMVPFragment<ClassifyLeftPresenterImpl,ClassifyLeftContract.FenleiView> implements ClassifyLeftContract.FenleiView {
    private RecyclerView fenRvOne;
    private RecyclerView fenRvTwo;
    private List<String> list=new ArrayList<>();
    private ClassifyLeftAdapter fenLeiAdapter;
    private List<Object> list_up=new ArrayList<>();
    private ClassifyRightAdapter fenleiAdapters=new ClassifyRightAdapter(getContext());
    private String url=ConfigUrl.SKIRT_URL;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify_left;
    }
    @Override
    protected void initData() {
        list.add("小裙子");
        list.add("上衣");
        list.add("下装");
        list.add("外套");
        list.add("配件");
        list.add("包包");
        list.add("装扮");
        list.add("居家宅品");
        list.add("办公文具");
        list.add("数码周边");
        list.add("游戏专区");
        fenLeiAdapter=new ClassifyLeftAdapter(list,getContext());
        fenLeiAdapter.setGetListener(new ClassifyLeftAdapter.GetListener() {
            @Override
            public void onclick(int position) {
                fenLeiAdapter.setmPosition(position);
                fenLeiAdapter.notifyDataSetChanged();
                changerData(position);
            }
        });
        fenRvOne.setAdapter(fenLeiAdapter);
    }
    @Override
    protected void initView() {
        fenRvOne = findViewById(R.id.fen_rv_one);
        fenRvOne.setLayoutManager(new LinearLayoutManager(getContext()));
        fenRvOne.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        fenRvTwo = findViewById(R.id.fen_rv_two);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        fenRvTwo.setLayoutManager(manager);
        fenRvTwo.setAdapter(fenleiAdapters);
    }
    private void changerData(int position) {
        Log.i("TAG", "changerData: "+position);
        switch (position){
            case 0:
                url = ConfigUrl.SKIRT_URL;
                break;
            case 1:
                url = ConfigUrl.JACKET_URL;
                break;
            case 2:
                url = ConfigUrl.PANTS_URL;
                break;
            case 3:
                url = ConfigUrl.OVERCOAT_URL;
                break;
            case 4:
                url = ConfigUrl.ACCESSORY_URL;
                break;
            case 5:
                url = ConfigUrl.BAG_URL;
                break;
            case 6:
                url = ConfigUrl.DRESS_UP_URL;
                break;
            case 7:
                url = ConfigUrl.HOME_PRODUCTS_URL;
                break;
            case 8:
                url = ConfigUrl.STATIONERY_URL;
                break;
            case 9:
                url = ConfigUrl.DIGIT_URL;
                break;
            case 10:
                url = ConfigUrl.GAME_URL;
                break;
        }
        ihttpPresenter.getFenLeiView(url);
    }
    @Override
    public void onFenleiData(List<ClothesBean.ResultBean> resultBeanList) {
        list_up.clear();
        List<ClothesBean.ResultBean.HotProductListBean> hot_product_list = resultBeanList.get(0).getHot_product_list();
        List<ClothesBean.ResultBean.ChildBean> child = resultBeanList.get(0).getChild();
        list_up.add(hot_product_list);
        list_up.add(child);
        fenleiAdapters.upDataText(list_up);
    }
    @Override
    public void showLoaing() {
        showLoading();
    }
    @Override
    public void hideLoading() {
        hideLoadingPage(true);
    }
    @Override
    public void showEmpty() {

    }
    @Override
    protected void initHttpData() {
        ihttpPresenter.getFenLeiView(url);
    }
    @Override
    protected void initPresenter() {
        ihttpPresenter =new  ClassifyLeftPresenterImpl();
    }
}