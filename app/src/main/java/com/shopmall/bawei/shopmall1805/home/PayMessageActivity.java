package com.shopmall.bawei.shopmall1805.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shopmall.bawei.shopmall1805.Adper.PayMessageAdaper;
import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;

import framework.BaseActivity;
import framework.MessageMangerUlis;
import framework.messagegreendao.ShopMessageGreenBean;
import view.SkipFinalUlis;
import view.loadinPage.ErrorBean;

@Route(path = SkipFinalUlis.PAY_MESSAGE_ACTIVITY)
public class PayMessageActivity extends BaseActivity {
    private RecyclerView payMessageRv;
    private PayMessageAdaper payMessageAdaper;
    private List<ShopMessageGreenBean> shopMessageGreenBeans = new ArrayList<>();
    @Override
    protected void createPresenter() {

    }

    @Override
    protected void OnClickListener() {

      /*  payMessageAdaper.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                new AlertDialog.Builder(PayMessageActivity.this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle(shopMessageGreenBeans.get(position).getId()+"")
                        .setMessage("内容:"+shopMessageGreenBeans.get(position).getBody()+"\n"
                                    +"时间:"+shopMessageGreenBeans.get(position).getTime()+"\n"
                                    +"其他:这是一种成年的大木头，有很多种好的习惯，希望各位加油，奥利给")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(PayMessageActivity.this, "取消", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(PayMessageActivity.this, "确定", Toast.LENGTH_SHORT).show();
                                MessageMangerUlis.getInstance().deleteMessage(shopMessageGreenBeans.get(position),null);
                                shopMessageGreenBeans.remove(position);
                                payMessageAdaper.notifyDataSetChanged();
                            }
                        })
                        .create()
                        .show();

            }
        });*/
    }

    @Override
    protected void initData() {
        tooBar = findViewById(R.id.tooBar);
      /*  payMessageRv = (RecyclerView) findViewById(R.id.payMessageRv);
        payMessageRv.setLayoutManager(new LinearLayoutManager(this));
        payMessageAdaper = new PayMessageAdaper(R.layout.item_paymessage,shopMessageGreenBeans);
        payMessageRv.setAdapter(payMessageAdaper);
        //该已经是主线程 接受数据且adaper刷新
        MessageMangerUlis.getInstance().queryMessage(new MessageMangerUlis.IMessageListener() {
            @Override
            public void OnResult(boolean isSuccess, List<ShopMessageGreenBean> shopMessageGreenBean) {
                shopMessageGreenBeans.addAll(shopMessageGreenBean);
                payMessageAdaper.notifyDataSetChanged();
            }
        });*/
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_pay_message;
    }

    @Override
    public void showLoaDing() {
        showLoaDing();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoading(isSuccess,errorBean);
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