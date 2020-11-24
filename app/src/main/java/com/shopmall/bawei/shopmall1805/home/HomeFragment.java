package com.shopmall.bawei.shopmall1805.home;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.base.BaseFragment;
import com.example.net.bean.MainBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<HomePresenterImpl, HomeContract.HomeView> implements HomeContract.HomeView {
    private ImageView searchBtnHome;
    private EditText searchEtHome;
    private LinearLayout newMessage;
    private RecyclerView rvHomeMain;
    private HomeAdapter homeAdapter;
    private List<Object> list=new ArrayList<>();
    @Override
    protected void initDate() {
        presenter.loadMain();
    }


    @Override
    protected void initLisenter() {

    }
    @Override
    protected void initView(View inflate) {
        presenter=new HomePresenterImpl();
        searchBtnHome = (ImageView) inflate.findViewById(R.id.search_btn_home);
        searchEtHome = (EditText) inflate.findViewById(R.id.search_et_home);
        newMessage = (LinearLayout) inflate.findViewById(R.id.new_message);
        rvHomeMain = (RecyclerView) inflate.findViewById(R.id.rv_home_main);
        rvHomeMain.requestFocus();
        rvHomeMain.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(getContext());
        rvHomeMain.setAdapter(homeAdapter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void onOk(MainBean bean) {
        MainBean.ResultBean result = bean.getResult();
        list.add(result.getBanner_info());
        list.add(result.getChannel_info());
        list.add(result.getAct_info());
        list.add(result.getSeckill_info());
        list.add(result.getRecommend_info());
        list.add(result.getHot_info());
        homeAdapter.addData(list);
    }

    @Override
    public void onEorror(String msg) {
        Toast.makeText(getContext(), ""+msg, Toast.LENGTH_SHORT).show();
    }
}