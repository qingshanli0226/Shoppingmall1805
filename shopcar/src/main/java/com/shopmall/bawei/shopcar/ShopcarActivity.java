package com.shopmall.bawei.shopcar;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.net.bean.InventoryBean;
import com.example.net.bean.OrderInfoBean;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.framework.example.framework.BaseActivity;
import com.shopmall.bawei.framework.example.framework.manager.CacheManager;
import com.shopmall.bawei.shopcar.adapter.ShopcarAdapter;
import com.shopmall.bawei.shopcar.shopcarmvp.ShopcarContract;
import com.shopmall.bawei.shopcar.shopcarmvp.ShopcarPresenterImpl;

import java.util.List;

@Route(path = "/goodcar/ShopcarActivity")
public class ShopcarActivity extends BaseActivity<ShopcarPresenterImpl, ShopcarContract.IShopcarView> implements View.OnClickListener, ShopcarContract.IShopcarView {

    private RecyclerView shopcarRv;
    private ShopcarAdapter shopcarAdapter;
    private CheckBox allSelectCheckBox;
    private TextView totalPriceTv;
    private CheckBox editAllSelectCheckBox;
    private RelativeLayout normalLayout;
    private RelativeLayout editLayout;
    private boolean isEditMode = false;

    private boolean newAllSelect;

    private TextView tvBianJi;
    private TextView tvFinish;

    @Override
    protected void initpreseter() {
        httpresenter = new ShopcarPresenterImpl();
        shopcarAdapter.setShopcarPresenter(httpresenter);
    }

    private CacheManager.IShopcarDataChangeListener iShopcarDataChangeListener = new CacheManager.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
            shopcarAdapter.updataData(shopcarBeanList);
        }

        @Override
        public void onOneDataChanged(int position, ShopcarBean shopcarBean) {
            shopcarAdapter.notifyItemChanged(position);
        }

        @Override
        public void onMoneyChanged(String moneyValue) {
            totalPriceTv.setText(moneyValue);
        }

        @Override
        public void onAllSelected(boolean isAllSelect) {
            if (isEditMode){
                editAllSelectCheckBox.setChecked(isAllSelect);
            }else {
                allSelectCheckBox.setChecked(isAllSelect);
            }
        }
    };

    @Override
    protected void initdate() {

        //获取购物车数据
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        Log.i("---", "initdate: "+shopcarBeanList.size());
        shopcarAdapter.updataData(shopcarBeanList);
        totalPriceTv.setText(CacheManager.getInstance().getMoneyValue());


        //去监听数据的改变,改变后去刷新UI
        CacheManager.getInstance().setShopcarDataChangeListener(iShopcarDataChangeListener);
        if (CacheManager.getInstance().isAllSelected()){
            allSelectCheckBox.setChecked(true);
        }else {
            allSelectCheckBox.setChecked(false);
        }

        //设置全选的点击事件
        allSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allSelectCheckBox.isChecked()){
                    newAllSelect = true;
                    httpresenter.selectAllProduct(newAllSelect);
                }else {
                    newAllSelect = false;
                    httpresenter.selectAllProduct(newAllSelect);
                }
            }
        });

        editAllSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editAllSelectCheckBox.isChecked()){
                    CacheManager.getInstance().selectAllProductInEditMode(true);
                }else {
                    CacheManager.getInstance().selectAllProductInEditMode(false);
                }
            }
        });



    }

    @Override
    protected void initview() {
        ARouter.getInstance().inject(this);

        shopcarRv = (RecyclerView) findViewById(R.id.shopcarRv);

        allSelectCheckBox = (CheckBox) findViewById(R.id.allSelect);

        totalPriceTv = (TextView) findViewById(R.id.sumValue);

        normalLayout = (RelativeLayout) findViewById(R.id.normalLayout);
        editAllSelectCheckBox = (CheckBox) findViewById(R.id.allEditSelect);
        editLayout = (RelativeLayout) findViewById(R.id.editLayout);

        tvBianJi = (TextView)findViewById(R.id.tvBianJi);
        tvFinish = (TextView)findViewById(R.id.tvFinish);

        findViewById(R.id.deleteBtn).setOnClickListener(this);
        findViewById(R.id.payBtn).setOnClickListener(this);

       CacheManager.getInstance().setShopcarDataChangeListener(iShopcarDataChangeListener);

        shopcarAdapter = new ShopcarAdapter();
        shopcarRv.setAdapter(shopcarAdapter);
        shopcarRv.setLayoutManager(new LinearLayoutManager(this));

        if (CacheManager.getInstance().isAllSelected()){
            allSelectCheckBox.setChecked(true);
        }else {
            allSelectCheckBox.setChecked(false);
        }

        //进入编辑模式
        tvBianJi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBianJi.setVisibility(View.GONE);
                tvFinish.setVisibility(View.VISIBLE);

                isEditMode=true;

                //更新列表
                shopcarAdapter.setEditMode(isEditMode);
                //更新底部布局
                normalLayout.setVisibility(View.GONE);
                editLayout.setVisibility(View.VISIBLE);
                if (CacheManager.getInstance().isAllSelectInEditMode()) {
                    editAllSelectCheckBox.setChecked(true);
                }
            }
        });

        //进入正常模式
        tvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEditMode = false;
                tvBianJi.setVisibility(View.VISIBLE);
                tvFinish.setVisibility(View.GONE);
                shopcarAdapter.setEditMode(isEditMode);
                normalLayout.setVisibility(View.VISIBLE);
                editLayout.setVisibility(View.GONE);

            }
        });
           //沙箱环境
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//设置沙箱环境.
    }


    @Override
    protected int getlayoutid() {
        return R.layout.activity_shopcar;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.payBtn) {
            //第一步检查购物车上商品在仓库中是否都有库存
//            httpresenter.checkInventory(CacheManager.getInstance().getSelectedProductBeanList());

            //跳转到提交订单页面
            ARouter.getInstance().build("/Pay/PayActivity").navigation();

        } else if (id == R.id.saveBtn) {

        } else if (id == R.id.deleteBtn) {
            List<ShopcarBean> deleteShopcarBeanList = CacheManager.getInstance().getDeleteShopcarBeanList();
            if (deleteShopcarBeanList.size()>0) {
                httpresenter.deleteProducts(deleteShopcarBeanList);
            } else {
                Toast.makeText(this, "当前没有要删除的数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onProductNumChange(String result, int position, String newNum) {

        //当服务端的商品数据发生改变后，本地缓存的商品数据的数量也要改变，保证和服务端数据一致
        CacheManager.getInstance().updateProductNum(position,newNum);

    }

    @Override
    public void onProductSelected(String result, int position) {
        //需要保证服务端购物车该商品选择的状态和本地该商品在购物车上选择的状态一致性
        CacheManager.getInstance().updateProductSelected(position);
    }

    @Override
    public void onAllSelected(String result) {
        //更新本地缓存的数据的选择状态
        CacheManager.getInstance().selectAllProduct(newAllSelect);
    }

    @Override
    public void onDeleteProducts(String result) {
        //在缓存中，将删除列表中商品在购物车上删掉
        CacheManager.getInstance().processDeleteProducts();
    }

    @Override
    public void onInventory(List<InventoryBean> inventoryBean) {

        if (checkInventoryIsEnough(inventoryBean)){
            //充足情况下，向服务端下订单
            httpresenter.getOrderInfo(CacheManager.getInstance().getSelectedProductBeanList());
        }else {
            Toast.makeText(this, ""+inventoryBean.get(0).getProductName()+"库存不充足", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkInventoryIsEnough(List<InventoryBean> inventoryBeans) {
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getSelectedProductBeanList();
        for (InventoryBean inventoryBean:inventoryBeans) {
            for (ShopcarBean shopcarBean:shopcarBeanList){
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
        //服务端已经成功下订单
        //使用支付宝完成支付功能
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                PayTask payTask = new PayTask(ShopcarActivity.this);
//                Map<String,String> resultMap = payTask.payV2(orderInfoBean.getOrderInfo(), true);
//                if (resultMap.get("resultStatus").equals("9000")) {//返回值是9000时代表支付成功
//                    handler.sendEmptyMessage(1);
//                } else {
//                    handler.sendEmptyMessage(2);
//                }
//            }
//        };
//
//        Thread thread = new Thread(runnable);
//        thread.start();
    }

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 1: {
//                    Toast.makeText(ShopcarActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                    //第一步将这些支付成功的商品，从购物车中删除
//                    CacheManager.getInstance().removeSelectedProducts();
//                    //第二步跳转的主页面，并且显示HomeFragment
//                    ARouter.getInstance().build("/main/MainActivity").navigation();
//                    break;
//                }
//                case 2: {
//                    Toast.makeText(ShopcarActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
//                    //第一步将这些支付成功的商品，从购物车中删除
//                    CacheManager.getInstance().removeSelectedProducts();
//                    //第二步跳转的主页面，并且显示HomeFragment
//                    ARouter.getInstance().build("/main/MainActivity").navigation();
//                    break;
//                }
//                default:
//                    break;
//            }
//        }
//    };

    @Override
    public void onErroy(String message) {

    }

    @Override
    public void showsloading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void showErroy(String message) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopcarDataChangerListener(iShopcarDataChangeListener);
    }
}
