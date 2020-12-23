package com.shopmall.bawei.shopmall1805.activity.delivery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bawei.deom.BaseActivity;
import com.bawei.deom.selectedordelete.FindForContract;
import com.bawei.deom.selectedordelete.FindForSendImpl;
import com.shopmall.bawei.shopmall1805.DetailsActivity;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.apter.DeliveryApter;

import java.util.ArrayList;
import java.util.List;

import bean.FindForPayBean;
import bean.FindForSendBean;

public class DeliveryActivity extends BaseActivity<FindForSendImpl, FindForContract.FindForView>implements FindForContract.FindForView {
    private RecyclerView recyle;
    ArrayList<FindForSendBean.ResultBean> arrayList=new ArrayList<>();
    DeliveryApter deliveryApter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_delivery;
    }

    @Override
    protected void inPresone() {
         prine=new FindForSendImpl();
    }

    @Override
    protected void inData() {
        prine.findforshow();
        deliveryApter=new DeliveryApter(R.layout.iteam_deliver,arrayList);
        recyle.setAdapter(deliveryApter);
        recyle.setLayoutManager(new LinearLayoutManager(this));
         recyle.addItemDecoration(new DividerItemDecoration(DeliveryActivity.this,DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void intView() {
        recyle = (RecyclerView) findViewById(R.id.recyle);
    }

    @Override
    public void onFindForView(List<FindForSendBean.ResultBean> list) {
      arrayList.addAll(list);
      deliveryApter.notifyDataSetChanged();
    }

    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }
}
