package com.shopmall.bawei.shopmall1805.shopcar.view;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.common2.GetShopCarBean;
import com.shopmall.bawei.order.OrderActivity;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.shopcar.adapter.ShopCarAdapter;
import com.shopmall.bawei.shopmall1805.shopcar.contract.ShopCarContract;
import com.shopmall.bawei.shopmall1805.shopcar.presenter.ShopCarPresenter;

import java.util.List;
import java.util.Map;

import http.InventoryBean;
import http.OrderInfoBean;
import mvp.CacheManager;
import mvp.view.BaseMVPFragment;
import mvp.view.BottomBar;

public class ShopCarFragment extends BaseMVPFragment<ShopCarPresenter, ShopCarContract.ISetShopCar> implements ShopCarContract.ISetShopCar {

    private TextView shopChange;
    private CheckBox shopSelect;
    private TextView shopCount;

    private Button shopPay;

    private RelativeLayout delete;
    private RecyclerView shopRv;
    private ShopCarAdapter shopCarAdapter;
    private List<GetShopCarBean> shopcarBeanList1;
    private boolean flag = true;
    private Button shopdeletno;
    private Button shopdelet;
    private boolean newAllSelect;
    private boolean isEditMode = false;



    private CacheManager.IShopcarDataChangeListener iShopcarDataChangeListener = new CacheManager.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<GetShopCarBean> shopcarBeanList) {
            shopCarAdapter.updataData(shopcarBeanList);
        }

        @Override
        public void onOneDataChanged(int position, GetShopCarBean shopcarBean) {
            shopCarAdapter.notifyItemChanged(position);
        }

        @Override
        public void onMoneyChanged(String moneyValue) {
            shopCount.setText(moneyValue);
        }

        @Override
        public void onAllSelected(boolean isAllSelect) {
            if (isEditMode) {
                shopSelect.setChecked(isAllSelect);
            } else {
                shopSelect.setChecked(isAllSelect);
            }
        }
    };


    @Override
    protected void initHttpData() {
        List<GetShopCarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        shopCarAdapter.updataData(shopcarBeanList);
        shopCount.setText(CacheManager.getInstance().getMoneyValue());
        CacheManager.getInstance().setShopcarDataChangeListener(iShopcarDataChangeListener);
        if (CacheManager.getInstance().isAllSelected()) {
            shopSelect.setChecked(true);
        } else {
            shopSelect.setChecked(false);
        }
        shopSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shopSelect.isChecked()) {

                    newAllSelect = true;
                    ihttpPresenter.selectAllProduct(newAllSelect);
                } else {
                    newAllSelect = false;
                    ihttpPresenter.selectAllProduct(newAllSelect);
                }
            }
        });

    }

    @Override
    protected void initPresenter() {

        ihttpPresenter = new ShopCarPresenter();
        shopCarAdapter.setShopcarPresenter(ihttpPresenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //获取购物车数据

        shopcarBeanList1 = CacheManager.getInstance().getShopcarBeanList();
        shopCarAdapter = new ShopCarAdapter();

        shopdeletno = (Button) findViewById(R.id.shopdeletno);
        shopdelet = (Button) findViewById(R.id.shopdelet);
        delete = (RelativeLayout) findViewById(R.id.delete);
        shopRv = (RecyclerView) findViewById(R.id.shop_rv);
        shopChange = (TextView) findViewById(R.id.shop_change);
        shopSelect = (CheckBox) findViewById(R.id.shop_select);
        shopCount = (TextView) findViewById(R.id.shop_count);
        shopPay = (Button) findViewById(R.id.shop_pay);
        shopRv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        shopRv.setAdapter(shopCarAdapter);

        if (shopcarBeanList1 != null) {
            shopCarAdapter.notifyDataSetChanged();
        }

        shopChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    shopCarAdapter.changeEditMode(flag);
                    delete.setVisibility(View.VISIBLE);
                    flag = false;
                } else {
                    shopCarAdapter.changeEditMode(flag);
                    delete.setVisibility(View.GONE);
                    flag = true;
                }
            }
        });

        shopdelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                List<GetShopCarBean> deleteShopcarBeanList = CacheManager.getInstance().getDeleteShopcarBeanList();
                if (deleteShopcarBeanList.size()>0) {
                    ihttpPresenter.deleteProducts(deleteShopcarBeanList);
                } else {
                    Toast.makeText(getContext(), "当前没有要删除的数据", Toast.LENGTH_SHORT).show();

                }
            }
        });
        shopdeletno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
                shopCarAdapter.changeEditMode(flag);
                delete.setVisibility(View.GONE);

            }
        });

        shopPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到订单页面
                startActivity(new Intent(getContext(), OrderActivity.class));
              //  ihttpPresenter.checkInventory(CacheManager.getInstance().getSelectedProductBeanList());
            }

        });
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);

    }



    @Override
    public void showError(String code, String message) {

    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onProductNumChange(String result, int position, String newNum) {
        CacheManager.getInstance().updateProductNum(position, newNum);
    }

    @Override
    public void onProductSelected(String result, int position) {
        CacheManager.getInstance().updateProductSelected(position);
    }

    @Override
    public void onAllSelected(String result) {
        CacheManager.getInstance().selectAllProduct(newAllSelect);
    }

    @Override
    public void onDeleteProducts(String result) {
        CacheManager.getInstance().processDeleteProducts();
    }

    @Override
    public void onInventory(List<InventoryBean> inventoryBean) {
        if (checkInventoryIsEnough(inventoryBean)) {//库存充足的条件
            //充足情况下，向服务端下订单
            ihttpPresenter.getOrderInfo(CacheManager.getInstance().getSelectedProductBeanList());
        } else {
            Toast.makeText(getContext(), "库存不充足", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean checkInventoryIsEnough(List<InventoryBean> inventoryBeans) {
        List<GetShopCarBean> shopcarBeanList = CacheManager.getInstance().getSelectedProductBeanList();
        for(InventoryBean inventoryBean:inventoryBeans) {
            for(GetShopCarBean shopcarBean:shopcarBeanList) {
                if (inventoryBean.getProductId().equals(shopcarBean.getProductId())) {
                    int inventoryNum = Integer.parseInt(inventoryBean.getProductNum());
                    int needNum = Integer.parseInt(inventoryBean.getProductNum());
                    if (needNum > inventoryNum) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void onOrderInfo(final OrderInfoBean orderInfoBean) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(getActivity());
                Map<String,String> resultMap = payTask.payV2(orderInfoBean.getOrderInfo(), true);
                if (resultMap.get("resultStatus").equals("9000")) {//返回值是9000时代表支付成功
                    handler.sendEmptyMessage(1);
                } else {
                    handler.sendEmptyMessage(2);
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1: {
                    Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
                    //第一步将这些支付成功的商品，从购物车中删除
                    CacheManager.getInstance().removeSelectedProducts();
                    //第二步跳转的主页面，并且显示HomeFragment
                    ARouter.getInstance().build("/main/MainActivity").withInt("index", BottomBar.HOME_INDEX).navigation();
                    break;
                }
                case 2: {
                    Toast.makeText(getContext(), "支付失败", Toast.LENGTH_SHORT).show();
                    //第一步将这些支付成功的商品，从购物车中删除

                    //生成订单
                  /*  CacheManager.getInstance().removeSelectedProducts();*/
                    //第二步跳转的主页面，并且显示HomeFragment
                    ARouter.getInstance().build("/main/MainActivity").withInt("index", BottomBar.HOME_INDEX).navigation();
                    break;
                }
                default:
                    break;
            }
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopcarDataChangerListener(iShopcarDataChangeListener);
    }
}