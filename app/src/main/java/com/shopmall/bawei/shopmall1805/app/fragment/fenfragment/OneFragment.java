package com.shopmall.bawei.shopmall1805.app.fragment.fenfragment;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.adapter.fenlei.FenleiAdapter;
import com.shopmall.bawei.shopmall1805.app.adapter.home.FenLeiLeftAdapter;
import com.shopmall.bawei.shopmall1805.app.contract.FenleiContract;
import com.shopmall.bawei.shopmall1805.app.presenter.FenleiPresenter;
import com.shopmall.bawei.shopmall1805.common.ClothesBean;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.framework.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class OneFragment extends BaseFragment<FenleiContract.FenleiPresenter> implements FenleiContract.FenleiView {
    private RecyclerView fenRvOne;
    private RecyclerView fenRvTwo;
    private List<String> list=new ArrayList<>();
    private FenLeiLeftAdapter fenLeiAdapter;
    private List<Object> list_up=new ArrayList<>();
    private  FenleiAdapter fenleiAdapters=new FenleiAdapter(getContext());
    private String url=ConfigUrl.SKIRT_URL;
    @Override
    protected void initEvent() {

    }
    @Override
    protected void createPresenter() {
        iPresenter=new FenleiPresenter(this);
        iPresenter.getFenSkirt();
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
        fenLeiAdapter=new FenLeiLeftAdapter(list,getContext());
        fenLeiAdapter.setGetListener(new FenLeiLeftAdapter.GetListener() {
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
    protected void initView(View iView) {
        fenRvOne = iView.findViewById(R.id.fen_rv_one);
        fenRvOne.setLayoutManager(new LinearLayoutManager(getContext()));
        fenRvOne.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        fenRvTwo = iView.findViewById(R.id.fen_rv_two);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        fenRvTwo.setLayoutManager(manager);
        fenRvTwo.setAdapter(fenleiAdapters);
    }
    @Override
    protected int bandLyoaut() {
        return R.layout.fragment_one;
    }
    @Override
    public void setSkirtData(List<ClothesBean.ResultBean> resultBeanList) {
        list_up.clear();
        List<ClothesBean.ResultBean.HotProductListBean> hot_product_list = resultBeanList.get(0).getHot_product_list();
        List<ClothesBean.ResultBean.ChildBean> child = resultBeanList.get(0).getChild();
        list_up.add(hot_product_list);
        list_up.add(child);
        fenleiAdapters.upDataText(list_up);
    }
    @Override
    public String setUrl() {
        return url;
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
        iPresenter.getFenSkirt();
    }
}