package com.bawei.shopcar;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.ErrorBean;
import com.bawei.common.view.MyToolBar;
import com.bawei.framework.BaseActivity;
import com.bawei.framework.CacheManager;
import com.bawei.net.mode.InventoryBean;
import com.bawei.net.mode.OrderInfoBean;
import com.bawei.net.mode.ShopcarBean;
import com.bawei.shopcar.contract.ShopcarContract;
import com.bawei.shopcar.presenter.ShopcarPresenterImpl;

import java.util.List;


@Route(path = "/shopcar/ShopcarActivity")
public class ShopcarActivity extends BaseActivity<ShopcarPresenterImpl, ShopcarContract.IShopcarView> implements ShopcarContract.IShopcarView, View.OnClickListener,MyToolBar.IToolBarClickListner {

    private RecyclerView shopcarRv;
    private ShopcarAdapter shopcarAdapter;
    private TextView totalPriceTv;
    private CheckBox allSelectCheckBox;
    private boolean newAllSelect;
    private boolean isEditMode = false;
    private RelativeLayout normalLayout;
    private RelativeLayout editLayout;
    private CheckBox editAllSelectCheckBox;
    private MyToolBar myToolBar;


    @Override
    protected void initPresenter() {
        httpPresenter = new ShopcarPresenterImpl();
        shopcarAdapter.setShopcarPresenter(httpPresenter);
    }

    private CacheManager.IShopcarDataChangeListener iShopcarDataChangeListener = new CacheManager.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
            shopcarAdapter.updateData(shopcarBeanList);
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
            if (isEditMode) {
                editAllSelectCheckBox.setChecked(isAllSelect);
            } else {
                allSelectCheckBox.setChecked(isAllSelect);
            }
        }
    };

    @Override
    protected void initData() {
        destroy();
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        shopcarAdapter.updateData(shopcarBeanList);
        totalPriceTv.setText(CacheManager.getInstance().getMoneyValue());

        CacheManager.getInstance().setShopcarDataChangeListener(iShopcarDataChangeListener);
        if (CacheManager.getInstance().isAllSelected()) {
            allSelectCheckBox.setChecked(true);
        } else {
            allSelectCheckBox.setChecked(false);
        }

        allSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allSelectCheckBox.isChecked()) {
                    newAllSelect = true;
                    httpPresenter.selectAllProduct(newAllSelect);
                } else {
                    newAllSelect = false;
                    httpPresenter.selectAllProduct(newAllSelect);
                }
            }
        });

        editAllSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editAllSelectCheckBox.isChecked()) {
                    CacheManager.getInstance().selectAllProductInEditMode(true);
                } else {
                    CacheManager.getInstance().selectAllProductInEditMode(false);
                }
            }
        });
    }

    @Override
    protected void initView() {
        shopcarRv = findViewById(R.id.shopcarRv);
        shopcarRv.setLayoutManager(new LinearLayoutManager(this));
        shopcarAdapter = new ShopcarAdapter();
        shopcarRv.setAdapter(shopcarAdapter);
        totalPriceTv = findViewById(R.id.sumValue);
        allSelectCheckBox = findViewById(R.id.allSelect);
        normalLayout = findViewById(R.id.normalLayout);
        editLayout = findViewById(R.id.editLayout);
        editAllSelectCheckBox = findViewById(R.id.allEditSelect);
        findViewById(R.id.deleteBtn).setOnClickListener(this);
        findViewById(R.id.payBtn).setOnClickListener(this);
        myToolBar = findViewById(R.id.toolbar);
        myToolBar.setToolBarClickListner(this);

        //EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//设置沙箱环境.
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {
        if (!isEditMode) {
            isEditMode = true;
            //myToolBar.setToolbarRightTv("完成");
            shopcarAdapter.setEditMode(isEditMode);
            normalLayout.setVisibility(View.GONE);
            editLayout.setVisibility(View.VISIBLE);
            if (CacheManager.getInstance().isAllSelectInEditMode()) {
                editAllSelectCheckBox.setChecked(true);
            }
        } else {
            isEditMode = false;
            //myToolBar.setToolbarRightTv("编辑");
            shopcarAdapter.setEditMode(isEditMode);
            normalLayout.setVisibility(View.VISIBLE);
            editLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_shopcar;
    }

    @Override
    public void onProductNumChange(String result, int position, String newNum) {
        Toast.makeText(this, "修改产品数量成功", Toast.LENGTH_SHORT).show();

        CacheManager.getInstance().updateProductNum(position, newNum);
    }

    @Override
    public void onProductSelected(String result, int position) {
        Toast.makeText(this, "该商品在购物车的选择发生改变", Toast.LENGTH_SHORT).show();
        CacheManager.getInstance().updateProductSelected(position);
    }

    @Override
    public void onAllSelected(String result) {
        Toast.makeText(this, "所有的商品的选择状态发生了改变,全选状态为:" + newAllSelect, Toast.LENGTH_SHORT).show();
        CacheManager.getInstance().selectAllProduct(newAllSelect);
    }

    @Override
    public void onDeleteProducts(String result) {
        Toast.makeText(this, "删除购物车数据成功", Toast.LENGTH_SHORT).show();
        CacheManager.getInstance().processDeleteProducts();
    }

    private boolean checkInventoryIsEnough(List<InventoryBean> inventoryBeans) {
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getSelectedProductBeanList();
        for (InventoryBean inventoryBean : inventoryBeans) {
            for (ShopcarBean shopcarBean : shopcarBeanList) {
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
    public void onInventory(List<InventoryBean> inventoryBean) {

        if (checkInventoryIsEnough(inventoryBean)) {
            httpPresenter.getOrderInfo(CacheManager.getInstance().getSelectedProductBeanList());
        } else {
            Toast.makeText(this, ((String) inventoryBean.get(0).getProductName()) + "库存不充足", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onOrderInfo(final OrderInfoBean orderInfoBean) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                PayTask payTask = new PayTask(ShopcarActivity.this);
//                Map<String, String> resultMap = payTask.payV2(orderInfoBean.getOrderInfo(), true);
//                if (resultMap.get("resultStatus").equals("9000")) {//返回值是9000时代表支付成功
//                    handler.sendEmptyMessage(1);
//                } else {
//                    handler.sendEmptyMessage(2);
//                }
//            }
//        };

//        Thread thread = new Thread(runnable);
//        thread.start();
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1: {
                    Toast.makeText(ShopcarActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    CacheManager.getInstance().removeSelectedProducts();
                    ARouter.getInstance().build("/main/MainActivity").withInt("index", 0).navigation();
                    break;
                }
                case 2: {
                    Toast.makeText(ShopcarActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    CacheManager.getInstance().removeSelectedProducts();
                    ARouter.getInstance().build("/main/MainActivity").withInt("index", 0).navigation();
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    public void showLoaDing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }


    protected void destroy() {
        CacheManager.getInstance().unSetShopcarDataChangerListener(iShopcarDataChangeListener);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.deleteBtn) {
            List<ShopcarBean> deleteShopcarBeanList = CacheManager.getInstance().getDeleteShopcarBeanList();
            if (deleteShopcarBeanList.size() > 0) {
                httpPresenter.deleteProducts(deleteShopcarBeanList);
            } else {
                Toast.makeText(this, "当前没有要删除的数据", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.payBtn) {
            httpPresenter.checkInventory(CacheManager.getInstance().getSelectedProductBeanList());
        }
    }
}
