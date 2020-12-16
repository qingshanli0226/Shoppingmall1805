package com.bawei.shopcar;


import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.ErrorBean;
import com.bawei.framework.BaseFragment;
import com.bawei.framework.CacheManager;
import com.bawei.framework.ShopUserManager;
import com.bawei.net.mode.InventoryBean;
import com.bawei.net.mode.ShopcarBean;
import com.bawei.shopcar.contract.ShopcarContract;
import com.bawei.shopcar.presenter.ShopcarPresenterImpl;

import java.util.List;

public class ShopcarFragment extends BaseFragment<ShopcarPresenterImpl, ShopcarContract.IShopcarView> implements ShopcarContract.IShopcarView, View.OnClickListener {

    private RecyclerView shopcarRv;
    private ShopcarAdapter shopcarAdapter;
    private CheckBox allSelectCheckBox;
    private TextView totalPriceTv;
    private boolean newAllSelect;
    private boolean isEditMode = false;
    private RelativeLayout normalLayout;
    private CheckBox editAllSelectCheckBox;
    private RelativeLayout editLayout;
    private Button payBtn;

    private CacheManager.IShopcarDataChangeListener iShopcarDataChangeListener = new CacheManager.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
            shopcarAdapter.updateData(shopcarBeanList);
        }

        @Override
        public void onOneDataChanged(int postion, ShopcarBean shopcarBean) {
            shopcarAdapter.notifyItemChanged(postion);
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
    protected void initView() {
        shopcarRv = findViewById(R.id.shopcarRv);
        totalPriceTv = findViewById(R.id.sumValue);
        allSelectCheckBox = findViewById(R.id.allSelect);
        normalLayout = findViewById(R.id.normalLayout);
        editLayout = findViewById(R.id.editLayout);
        payBtn = findViewById(R.id.payBtn);
        editAllSelectCheckBox = findViewById(R.id.allEditSelect);
        payBtn.setOnClickListener(this);

        shopcarRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        shopcarAdapter = new ShopcarAdapter();
        shopcarRv.setAdapter(shopcarAdapter);

        findViewById(R.id.deleteBtn).setOnClickListener(this);
    }

    @Override
    protected void initHttpData() {
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        shopcarAdapter.updateData(shopcarBeanList);
        totalPriceTv.setText(CacheManager.getInstance().getMoneyValue());

        CacheManager.getInstance().setShopcarDataChangeListener(iShopcarDataChangeListener);

        allSelectCheckBox.setChecked(CacheManager.getInstance().isAllSelected());

        allSelectCheckBox.setOnClickListener(this);

        editAllSelectCheckBox.setOnClickListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_shopcar;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new ShopcarPresenterImpl();
        shopcarAdapter.setShopcarPresenter(httpPresenter);
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {
        super.onRightClick();
        if (!isEditMode) {
            isEditMode = true;
            toolBar.setToolbarRightTv("完成");
            shopcarAdapter.setEditMode(isEditMode);
            normalLayout.setVisibility(View.GONE);
            editLayout.setVisibility(View.VISIBLE);
            if (CacheManager.getInstance().isAllSelectInEditMode()) {
                editAllSelectCheckBox.setChecked(true);
            }
        } else {
            isEditMode = false;
            toolBar.setToolbarRightTv("编辑");
            shopcarAdapter.setEditMode(isEditMode);
            normalLayout.setVisibility(View.VISIBLE);
            editLayout.setVisibility(View.GONE);
        }
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
        if (checkInventoryIsEnough(inventoryBean)) {

        } else {
            Toast.makeText(getContext(), "库存不足", Toast.LENGTH_SHORT).show();
        }
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
    public void showLoaDing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.deleteBtn) {
            List<ShopcarBean> deleteShopcarBeanList = CacheManager.getInstance().getDeleteShopcarBeanList();
            if (deleteShopcarBeanList.size() > 0) {
                httpPresenter.deleteProducts(deleteShopcarBeanList);
            } else {
                Toast.makeText(getContext(), "没有删除的数据", Toast.LENGTH_SHORT).show();
            }
        } else if (view.getId() == R.id.allSelect) {
            if (allSelectCheckBox.isChecked()) {
                newAllSelect = true;
                httpPresenter.selectAllProduct(newAllSelect);
            } else {
                newAllSelect = false;
                httpPresenter.selectAllProduct(newAllSelect);
            }
        } else if (view.getId() == R.id.allEditSelect) {
            CacheManager.getInstance().selectAllProductInEditMode(editAllSelectCheckBox.isChecked());
        } else if (view.getId() == R.id.payBtn) {
            Toast.makeText(getContext(), "点击", Toast.LENGTH_SHORT).show();
            if (ShopUserManager.getInstance().isBanDingPhone() && ShopUserManager.getInstance().isBanDingAddress()) {
                ARouter.getInstance().build("/order/OrderActivity").navigation();

            } else {
                Toast.makeText(getContext(), "请绑定电话或地址", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build("/order/BanDing").navigation();
            }
            httpPresenter.checkInventory(CacheManager.getInstance().getSelectedProductBeanList());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopcarDataChangerListener(iShopcarDataChangeListener);
    }
}
