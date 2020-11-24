package com.shopmall.bawei.shopmall1805.type.view;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shopmall.bawei.common.MyToolBar;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.net.TypeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.type.contract.TypeContract;
import com.shopmall.bawei.shopmall1805.type.contract.TypeImpl;

import java.util.ArrayList;
import java.util.List;

public class ListFragment<P extends TypeImpl,V extends TypeContract.ITypeView> extends BaseFragment<P,V> implements TypeContract.ITypeView {
    private MyToolBar toolbar;
    private ListView lvLeft;
    private RecyclerView rvRight;

    private List<String> list = new ArrayList<>();

    private TypeAdapter adapter;
    private ListAdapter lvAdapter;



    @Override
    protected void initView() {
        lvLeft = (ListView) findViewById(R.id.lv_left);
        rvRight = (RecyclerView) findViewById(R.id.rv_right);
        adapter = new TypeAdapter();
        rvRight.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        rvRight.setAdapter(adapter);
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
        lvAdapter = new ListAdapter(getContext(),list);
        lvLeft.setAdapter(lvAdapter);
    }

    @Override
    protected void initData() {
        httpPresenter.getSkirt();
        httpPresenter.getJacket();
        httpPresenter.getPants();
        httpPresenter.getOvercoat();
        httpPresenter.getAccessory();
        httpPresenter.getBag();
        httpPresenter.getDress();
        httpPresenter.getProduct();
        httpPresenter.getStationery();
        httpPresenter.getDigit();
        httpPresenter.getGame();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initListener() {
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvAdapter.changeSelected(position);
                lvAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void initPresenter() {
        httpPresenter = (P) new TypeImpl();
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
    public void hideLoading() {

    }

    @Override
    public void onSkirt(TypeBean typeBean) {
        List<TypeBean.ResultBean> result = typeBean.getResult();
        for (TypeBean.ResultBean item:result) {
            Log.i("TAG", "onSkirt: "+item.getName());
        }
    }

    @Override
    public void onJacket(TypeBean typeBean) {
        List<TypeBean.ResultBean> result = typeBean.getResult();
        for (TypeBean.ResultBean item:result) {
            Log.i("TAG", "onSkirt: "+item.getName());
        }
    }

    @Override
    public void onPants(TypeBean typeBean) {
        List<TypeBean.ResultBean> result = typeBean.getResult();
        for (TypeBean.ResultBean item:result) {
            Log.i("TAG", "onSkirt: "+item.getName());
        }
    }

    @Override
    public void onOvercoat(TypeBean typeBean) {
        List<TypeBean.ResultBean> result = typeBean.getResult();
        for (TypeBean.ResultBean item:result) {
            Log.i("TAG", "onSkirt: "+item.getName());
        }
    }

    @Override
    public void onAccessory(TypeBean typeBean) {
        List<TypeBean.ResultBean> result = typeBean.getResult();
        for (TypeBean.ResultBean item:result) {
            Log.i("TAG", "onSkirt: "+item.getName());
        }
    }

    @Override
    public void onBag(TypeBean typeBean) {
        List<TypeBean.ResultBean> result = typeBean.getResult();
        for (TypeBean.ResultBean item:result) {
            Log.i("TAG", "onSkirt: "+item.getName());
        }
    }

    @Override
    public void onDress(TypeBean typeBean) {
        List<TypeBean.ResultBean> result = typeBean.getResult();
        for (TypeBean.ResultBean item:result) {
            Log.i("TAG", "onSkirt: "+item.getName());
        }
    }

    @Override
    public void onProduct(TypeBean typeBean) {
        List<TypeBean.ResultBean> result = typeBean.getResult();
        for (TypeBean.ResultBean item:result) {
            Log.i("TAG", "onSkirt: "+item.getName());
        }
    }

    @Override
    public void onStationery(TypeBean typeBean) {
        List<TypeBean.ResultBean> result = typeBean.getResult();
        for (TypeBean.ResultBean item:result) {
            Log.i("TAG", "onSkirt: "+item.getName());
        }
    }

    @Override
    public void onDigit(TypeBean typeBean) {
        List<TypeBean.ResultBean> result = typeBean.getResult();
        for (TypeBean.ResultBean item:result) {
            Log.i("TAG", "onSkirt: "+item.getName());
        }
    }

    @Override
    public void onGame(TypeBean typeBean) {
        List<TypeBean.ResultBean> result = typeBean.getResult();
        for (TypeBean.ResultBean item:result) {
            Log.i("TAG", "onSkirt: "+item.getName());
        }
    }
}
