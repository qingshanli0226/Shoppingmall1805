package com.shopmall.bawei.order.mview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.order.R;
import com.shopmall.bawei.order.adaper.MessageAdaper;

import java.util.ArrayList;

import framework.BaseActivity;
import view.SkipFinalUlis;
import view.loadinPage.ErrorBean;

@Route(path = SkipFinalUlis.REFRESH_ACTIVITY)
public class MainActivity2 extends BaseActivity implements RefreshLayout.IRefreshListener {
    private RefreshLayout refreshLayout;
    private ListView listView;
    private MessageAdaper messageAdaper ;
    private ArrayList<String> arrayList = new ArrayList<>();
    private int count = 0;

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void initData() {

        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        listView = (ListView) findViewById(R.id.listView);
        tooBar = findViewById(R.id.tooBar);
        refreshLayout.AddRefreshListener(this);

        messageAdaper = new MessageAdaper(this,arrayList);
        listView.setAdapter(messageAdaper);
        refreshData(0);
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_refresh;
    }

    @Override
    public void onRefresh() {
        refreshData(1);
        Log.i("pppp","上拉刷新11111");
    }

    private void refreshData(int i) {
        if (i==1){
            count =0;
            arrayList.clear();
            for (int c=0;c<20;c++){
                arrayList.add("这个数据是"+count++);
            }
            messageAdaper.notifyDataSetChanged();
        }else {
            for (int c=0;c<20;c++){
                arrayList.add("这个数据是"+count++);
            }
            messageAdaper.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadMore() {
        Log.i("pppp","下拉加载11111");
        refreshData(0);
    }

    @Override
    public void onRefreshComPlete() {

    }


    @Override
    public void showLoaDing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}