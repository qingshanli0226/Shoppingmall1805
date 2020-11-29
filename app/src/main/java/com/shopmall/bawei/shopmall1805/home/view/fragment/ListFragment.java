package com.shopmall.bawei.shopmall1805.home.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.net.mode.RecommendedBeen;
import com.shopmall.bawei.net.typebean.BugBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.home.contract.TypeContract;
import com.shopmall.bawei.shopmall1805.home.presenter.HomeImpl;
import com.shopmall.bawei.shopmall1805.home.presenter.TypeImpl;
import com.shopmall.bawei.shopmall1805.home.view.adapter.BugChangApter;
import com.shopmall.bawei.shopmall1805.home.view.adapter.BugReApter;
import com.shopmall.bawei.shopmall1805.home.view.adapter.ZhongleiApter;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends BaseFragment<TypeImpl, TypeContract.ITypeView> implements TypeContract.ITypeView, View.OnClickListener {
    private RecyclerView zhonglei;
    private RecyclerView remai;
    private RecyclerView changyong;

    //    //集合
    ArrayList<String> arrayList=new ArrayList<>();

    ArrayList<BugBean.ResultBean.HotProductListBean> BugRe=new ArrayList<>();

    //    //适配器
    ZhongleiApter zhongleiApter;

    BugReApter bugReApter;
    BugChangApter bugChangApter;
    @Override
    protected int layoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {
        super.initView();
        zhonglei = (RecyclerView) findViewById(R.id.zhonglei);
        remai = (RecyclerView) findViewById(R.id.remai);
        changyong = (RecyclerView)findViewById(R.id.changyong);
        changyong.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));


        remai.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        super.initData();
        add();

        httpPresenter.UserShow(Constants.SKIRT_URL);
        httpPresenter.UserReShow(Constants.SKIRT_URL);
        zhongleiApter=new ZhongleiApter(R.layout.string,arrayList);
        zhonglei.setAdapter(zhongleiApter);
        zhonglei.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        zhongleiApter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (arrayList.get(position)){
                    case "小裙子":
                        httpPresenter.UserShow(Constants.SKIRT_URL);
                        httpPresenter.UserReShow(Constants.SKIRT_URL);
                        break;
                    case "上衣":
                        httpPresenter.UserShow(Constants.JACKET_URL);
                        httpPresenter.UserReShow(Constants.JACKET_URL);

                        Toast.makeText(getContext(), "上衣", Toast.LENGTH_SHORT).show();
                        break;
                    case "下装":
                        httpPresenter.UserShow(Constants.PANTS_URL);
                        httpPresenter.UserReShow(Constants.PANTS_URL);

                        break;
                    case "外套":
                        httpPresenter.UserShow(Constants.OVERCOAT_URL);
                        httpPresenter.UserReShow(Constants.OVERCOAT_URL);

                        break;
                    case "配件":
                        httpPresenter.UserShow(Constants.ACCESSORY_URL);
                        httpPresenter.UserReShow(Constants.ACCESSORY_URL);

                        break;
                    case "包包":
                        httpPresenter.UserShow(Constants.BAG_URL);
                        httpPresenter.UserReShow(Constants.BAG_URL);

                        break;

                    case "装扮":
                        httpPresenter.UserShow(Constants.DRESS_UP_URL);
                        httpPresenter.UserReShow(Constants.DRESS_UP_URL);

                        break;
                    case "居家宅品":
                        httpPresenter.UserShow(Constants.HOME_PRODUCTS_URL);
                        httpPresenter.UserReShow(Constants.HOME_PRODUCTS_URL);

                        break;
                    case "办公文具":
                        httpPresenter.UserShow(Constants.STATIONERY_URL);
                        httpPresenter.UserReShow(Constants.STATIONERY_URL);

                        break;
                    case "数码周边":
                        httpPresenter.UserShow(Constants.DIGIT_URL);
                        httpPresenter.UserReShow(Constants.DIGIT_URL);

                        break;
                    case "游戏专区":
                        httpPresenter.UserShow(Constants.GAME_URL);
                        httpPresenter.UserReShow(Constants.GAME_URL);

                        break;
                }

            }
        });
    }

    private void add() {

        arrayList.add("小裙子");
        arrayList.add("上衣");
        arrayList.add("下装");
        arrayList.add("外套");
        arrayList.add("配件");
        arrayList.add("包包");
        arrayList.add("装扮");
        arrayList.add("居家宅品");
        arrayList.add("办公文具");
        arrayList.add("数码周边");
        arrayList.add("游戏专区");


    }

    @Override
    protected void initPresenter() {
        httpPresenter = new TypeImpl();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }



    @Override
    public void UserView(List<BugBean.ResultBean.ChildBean> list) {
        //Log.e("============",""+list.get(0).getName());
        if (bugChangApter==null){
            bugChangApter=new BugChangApter(R.layout.skirtlayout,list);
            changyong.setAdapter(bugChangApter);
        }else {
            Log.e("changyong",""+list.get(0).getName());
            bugChangApter.getData().clear();
            bugChangApter.getData().addAll(list);

            bugChangApter.notifyDataSetChanged();
        }
    }

    @Override
    public void UserRe(List<BugBean.ResultBean.HotProductListBean> list) {
        Log.e("============",""+list.get(0).getName());
        if (bugReApter==null){
            bugReApter=new BugReApter(R.layout.jackchang,list);
            remai.setAdapter(bugReApter);
        }else {
            Log.e("remai",""+list.get(0).getName());
            bugReApter.getData().clear();
            bugReApter.getData().addAll(list);

            bugReApter.notifyDataSetChanged();
        }
    }
}