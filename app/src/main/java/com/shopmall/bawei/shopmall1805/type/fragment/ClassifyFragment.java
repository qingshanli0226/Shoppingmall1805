package com.shopmall.bawei.shopmall1805.type.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.bw.framework.BaseFragment;
import com.bw.net.bean.SkirtBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.type.adapter.MyListAdapter;
import com.shopmall.bawei.shopmall1805.type.adapter.TypeFragmentAdapter;
import com.shopmall.bawei.shopmall1805.type.contract.TypeContract;
import com.shopmall.bawei.shopmall1805.type.presenter.TypePresenter;

import java.util.ArrayList;
import java.util.List;

public class ClassifyFragment extends BaseFragment<TypePresenter, TypeContract.TypeView> implements TypeContract.TypeView  {
    private RecyclerView recyclerView;
    private TypeFragmentAdapter typeFragmentAdapter;
    private List<Object> skirtList = new ArrayList<>();
    private ListView listView;
    private MyListAdapter myListAdapter;
    private List<String> titles = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initView(View view) {
        listView = view.findViewById(R.id.item_lv);
        recyclerView = view.findViewById(R.id.find_rv);
        typeFragmentAdapter = new TypeFragmentAdapter();
        recyclerView.setAdapter(typeFragmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        titles.add("裙子");
        titles.add("上衣");
        titles.add("下衣");
        titles.add("外套");
        titles.add("配件");
        titles.add("包包");
        titles.add("装扮");
        titles.add("居家宅品");
        titles.add("办公文具");
        titles.add("数码周边");
        titles.add("游戏专区");



        myListAdapter = new MyListAdapter(titles,getContext());
        listView.setAdapter(myListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                skirtList.clear();
                switch (position){
                    case 0:
                        httpPresenter.getSkirt();
                        break;
                    case 1:
                        httpPresenter.getJacket();
                        break;
                    case 2:
                        httpPresenter.getPints();
                        break;
                    case 3:
                        httpPresenter.getOvercoad();
                        break;
                    case 4:
                        httpPresenter.getAccessory();
                        break;
                    case 5:
                        httpPresenter.getBag();
                        break;
                    case 6:
                        httpPresenter.getDress();
                        break;
                    case 7:
                        httpPresenter.getHomeproduct();
                        break;
                    case 8:
                        httpPresenter.getStation();
                        break;
                    case 9:
                        httpPresenter.getDigit();
                        break;
                    case 10:
                        httpPresenter.getGame();
                        break;
                }
                typeFragmentAdapter.notifyDataSetChanged();
            }
        });

    }
    @Override
    protected void initPresenter() {
        super.initPresenter();
        httpPresenter = new TypePresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        httpPresenter.getSkirt();
    }

    @Override
    public void onGetSkirtOk(SkirtBean skirtBean) {

        List<SkirtBean.ResultBean> result = skirtBean.getResult();
            List<SkirtBean.ResultBean.ChildBean> child = result.get(0).getChild();
            List<SkirtBean.ResultBean.HotProductListBean> hot_product_list = result.get(0).getHot_product_list();
            skirtList.add(hot_product_list);
            skirtList.add(child);
        typeFragmentAdapter.updataData(skirtList);
    }



    @Override
    public void onError(String message) {
        myToast(R.string.getDataError+message);
    }

    @Override
    public void showsLoaing() {
        showLoading();
    }

    @Override
    public void hidesLoading(boolean isSuccess) {
        hideLoadingPage(isSuccess);
    }


    @Override
    public void showEmpty() {

    }

    @Override
    public void onRightClick() {

    }
}
