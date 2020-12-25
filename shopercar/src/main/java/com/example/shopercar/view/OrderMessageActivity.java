package com.example.shopercar.view;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.framwork.BaseActivity;
import com.example.framwork.BaseMVPActivity;
import com.example.framwork.BaseMVPFragment;
import com.example.framwork.CacheManager;
import com.example.framwork.sql.MySqlOpenHelper;
import com.example.net.InventoryBean;
import com.example.net.OrderInfoBean;
import com.example.net.bean.ConfirmServerPayResultBean;
import com.example.net.bean.ErrorBean;
import com.example.net.bean.ShopcarBean;
import com.example.shopercar.contract.ShopCarContract;
import com.example.shopercar.presenter.ShopCarPresenterImpl;


import java.util.List;
import java.util.Map;

@Route(path = "/Order/OrderActivity")
public class OrderMessageActivity extends BaseMVPActivity<ShopCarPresenterImpl, ShopCarContract.ShopCarIView> implements ShopCarContract.ShopCarIView, View.OnClickListener {

    private RecyclerView shopcarRv;
    private ShopcarAdapter shopcarAdapter;
    private TextView txtShopCar;
    private TextView txtEditor;
    private CheckBox allSelect;
    private TextView sumNote;
    private TextView sumValue;
    private Button payBtn;
    private CheckBox allEditSelect;
    private Button saveBtn;
    private Button deleteBtn;

    private MySqlOpenHelper mySqlOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private boolean newAllSelect;

    private boolean isEditMode = false; //该标记位，当为true时，该页面为编辑模式，可以删除列表的商品时速局。当为false时，当前页面为正常模式，可以支付
    private RelativeLayout normalLayout;//正常模式下的底部布局
    private RelativeLayout editLayout;//编辑模式下的底部布局
    private CheckBox editAllSelectCheckBox;
    private CacheManager.IShopCarChangeLinsterner iShopcarDataChangeListener = new CacheManager.IShopCarChangeLinsterner() {
        @Override
        public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
            shopcarAdapter.updatelist(shopcarBeanList);
        }

        @Override
        public void onOneDataChanged(int position, ShopcarBean shopcarBean) {
            shopcarAdapter.notifyItemChanged(position);//只更新这个位置的Item UI
        }

        @Override
        public void onMoneyChanged(String moneyValue) {
            sumValue.setText(moneyValue);
        }

        @Override
        public void onAllSelected(boolean isAllSelect) {
            if (isEditMode) {
                editAllSelectCheckBox.setChecked(isAllSelect);
            } else {
                allSelect.setChecked(isAllSelect);
            }
        }
    };
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1: {
                    Toast.makeText(OrderMessageActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    mySql();

                    Intent intent = new Intent();
                    intent.setAction("unorderbroadcast");
                    intent.putExtra("pay","");
                    OrderMessageActivity.this.sendBroadcast(intent);
                    //第一步将这些支付成功的商品，从购物车中删除
                    CacheManager.getInstance().removeSelectedProducts();
                    //第二步跳转的主页面，并且显示HomeFragment

                    ARouter.getInstance().build("/main/MainActivity").withInt("index",3).navigation();
                    break;
                }
                case 2: {
                    Toast.makeText(OrderMessageActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    //第一步将这些支付成功的商品，从购物车中删除
                    CacheManager.getInstance().removeSelectedProducts();
                    //第二步跳转的主页面，并且显示HomeFragment
                    ARouter.getInstance().build("/main/MainActivity").withInt("index",3).navigation();
                    break;
                }
                default:
                    break;
            }
        }
    };

    private void mySql() {
        mySqlOpenHelper=new MySqlOpenHelper(OrderMessageActivity.this,"MoneyHouse.db", null, 2);
        sqLiteDatabase=mySqlOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("money","");
        sqLiteDatabase.insert("house",null,contentValues);
    }

    private ShopCarPresenterImpl shopCarPresenter;


    @Override
    protected int getLayoutId() {
        return com.example.shopercar.R.layout.activity_order_info;
    }


    @Override
    protected void initView() {
        shopcarRv = findViewById(com.example.shopercar.R.id.shopcarRv);
        txtShopCar = findViewById(com.example.shopercar.R.id.txt_shopCar);
        txtEditor = findViewById(com.example.shopercar.R.id.txt_editor);
        normalLayout = findViewById(com.example.shopercar.R.id.normalLayout);
        allSelect = findViewById(com.example.shopercar.R.id.allSelect);
        sumNote = findViewById(com.example.shopercar.R.id.sumNote);
        sumValue = findViewById(com.example.shopercar.R.id.sumValue);
        payBtn =findViewById(com.example.shopercar.R.id.payBtn);
        editLayout = findViewById(com.example.shopercar.R.id.editLayout);
        allEditSelect = findViewById(com.example.shopercar.R.id.allEditSelect);
        saveBtn = findViewById(com.example.shopercar.R.id.saveBtn);
        deleteBtn = findViewById(com.example.shopercar.R.id.deleteBtn);






        shopcarRv.setLayoutManager(new LinearLayoutManager(this));
        shopcarAdapter = new ShopcarAdapter();
        shopcarRv.setAdapter(shopcarAdapter);

//        txtEditor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editor();
//            }
//        });

        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//设置沙箱环境.
    }

    @Override
    protected void initData() {
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(OrderMessageActivity.this, "正在支付...", Toast.LENGTH_SHORT).show();
                shopCarPresenter.checkInventory(CacheManager.getInstance().getSelectedProductBeanList());
            }
        });
    }

    private void editor() {
        if (!isEditMode) {//如果当前页面时非编辑模式，那么则进入编辑模式
            isEditMode = true;//进入编辑模式
            //更新列表
            shopcarAdapter.setEditMode(isEditMode);
            //更新底部布局
            normalLayout.setVisibility(View.GONE);
            editLayout.setVisibility(View.VISIBLE);
            if (CacheManager.getInstance().isAllSelectInEditMode()) {
                allEditSelect.setChecked(true);
            }
        } else {//从编辑模式进入到正常模式
            isEditMode = false;
            shopcarAdapter.setEditMode(isEditMode);
            normalLayout.setVisibility(View.VISIBLE);
            editLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initPresenter() {
        shopCarPresenter = new ShopCarPresenterImpl();
        shopCarPresenter.attatch(this);
        shopcarAdapter.setShopcarAdapter(shopCarPresenter);



    }

    @Override
    protected void iniHttpView() {
        //获取购物车数据
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        shopcarAdapter.updatelist(shopcarBeanList);
        sumValue.setText(CacheManager.getInstance().getMoneyValue());

        //去监听数据的改变,改变后去刷新UI
        CacheManager.getInstance().setShopcarDataChangeListener(iShopcarDataChangeListener);
        if (CacheManager.getInstance().isAllSelected()) {
            allSelect.setChecked(true);
        } else {
            allSelect.setChecked(false);
        }

        //设置全选的点击事件
        allSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allSelect.isChecked()) {
                    newAllSelect= true;
                    shopCarPresenter.selectAllProduct(newAllSelect);
                } else {
                    newAllSelect = false;
                    shopCarPresenter.selectAllProduct(newAllSelect);
                }
            }
        });

        allEditSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allEditSelect.isChecked()) {//在编辑模式下，所有商品都被选中了
                    CacheManager.getInstance().selectAllProductInEditMode(true);
                } else {
                    CacheManager.getInstance().selectAllProductInEditMode(false);
                }
            }
        });
    }

    private boolean checkInventoryIsEnough(List<InventoryBean> inventoryBeans) {
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getSelectedProductBeanList();
        for(InventoryBean inventoryBean:inventoryBeans) {
            for(ShopcarBean shopcarBean:shopcarBeanList) {
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
    public void onProductNumChange(String result, int position, String newNum) {
        CacheManager.getInstance().updateProductNum(position, newNum);
    }

    @Override
    public void onProductSelect(String result, int position) {
        Toast.makeText(this, "该商品在购物车的选择发生改变", Toast.LENGTH_SHORT).show();
        //需要保证服务端购物车该商品选择的状态和本地该商品在购物车上选择的状态一致性
        CacheManager.getInstance().updateProductSelected(position);
    }

    @Override
    public void onAllSelect(String result) {
        Toast.makeText(this, "所有的商品的选择状态发生了改变,全选状态为:"+newAllSelect, Toast.LENGTH_SHORT).show();
        //更新本地缓存的数据的选择状态
        CacheManager.getInstance().selectAllProduct(newAllSelect);
    }

    @Override
    public void onDeleteProduct(String result) {
        Toast.makeText(this, "删除购物车数据成功", Toast.LENGTH_SHORT).show();
        //在缓存中，将删除列表中商品在购物车上删掉
        CacheManager.getInstance().processDeleteProducts();
    }

    @Override
    public void onInventory(List<InventoryBean> inventoryBeans) {
        if (checkInventoryIsEnough(inventoryBeans)) {//库存充足的条件
            //充足情况下，向服务端下订单
            shopCarPresenter.getOrderInfo(CacheManager.getInstance().getSelectedProductBeanList());
        } else {
            Toast.makeText(this, ((String)inventoryBeans.get(0).getProductName()) + "库存不充足", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onOnderInfo(final OrderInfoBean orderInfoBean) {
//        服务端已经成功下订单
//        使用支付宝完成支付功能
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(OrderMessageActivity.this);
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

    @Override
    public void onConfirmServerPay(ConfirmServerPayResultBean confirmServerPayResultBean) {

    }

    @Override
    public void onError(String code, String message) {

    }

    @Override
    public void showLoadings() {
        showLoading();
    }


    @Override
    public void hideLoading(boolean isSuccess, ErrorBean message) {
        hideLoadingPage(isSuccess,message);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == com.example.shopercar.R.id.deleteBtn) {
            List<ShopcarBean> deleteShopcarBeanList = CacheManager.getInstance().getDeleteShopcarBeanList();
            if (deleteShopcarBeanList.size()>0) {
                shopCarPresenter.deleteProducts(deleteShopcarBeanList);
            } else {
                Toast.makeText(this, "当前没有要删除的数据", Toast.LENGTH_SHORT).show();
            }
        } else if (view.getId() == com.example.shopercar.R.id.payBtn) {
            //第一步检查购物车上商品在仓库中是否都有库存
            shopCarPresenter.checkInventory(CacheManager.getInstance().getSelectedProductBeanList());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shopCarPresenter.detachview();
        CacheManager.getInstance().unSetShopcarDataChangerListener(iShopcarDataChangeListener);
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}
