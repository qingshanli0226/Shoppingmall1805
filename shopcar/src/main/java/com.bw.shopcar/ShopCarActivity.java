package com.bw.shopcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.framework.BaseActivity;
import com.bw.framework.CacheManager;
import com.bw.net.bean.ShopCarBean;
import com.bw.shopcar.adapter.ShopCarAdapter;
import com.bw.shopcar.contract.ShopCarContract;
import com.bw.shopcar.presenter.ShopCarPresenter;
import com.shopmall.bawei.shopcar.R;

import java.util.List;


@Route(path = "/activity/activity_shopCart")
public class ShopCarActivity extends BaseActivity<ShopCarPresenter, ShopCarContract.ShopCarView> implements ShopCarContract.ShopCarView
,CacheManager.IShopcarDataChangeListener{

    private ImageButton ibShopcartBack;
    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;

    private boolean newAllSelect;
    private boolean isEditMode = false; //该标记位，当为true时，该页面为编辑模式，可以删除列表的商品时速局。当为false时，当前页面为正常模式，可以支付

    private ShopCarAdapter shopCarAdapter;



    @Override
    protected void initView() {
        super.initView();
        ARouter.getInstance().inject(this);

        ibShopcartBack = (ImageButton) findViewById(R.id.ib_shopcart_back);
        tvShopcartEdit = (TextView) findViewById(R.id.tv_shopcart_edit);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) findViewById(R.id.ll_delete);
        cbAll = (CheckBox) findViewById(R.id.cb_all);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnCollection = (Button) findViewById(R.id.btn_collection);
        ivEmpty = (ImageView) findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = (TextView) findViewById(R.id.tv_empty_cart_tobuy);

        CacheManager.getInstance().setShopCarDataChangerListener(this);

        shopCarAdapter = new ShopCarAdapter();
        recyclerview.setAdapter(shopCarAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        shopCarAdapter.notifyDataSetChanged();

        tvShopcartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("---", "onClick: 点击编辑按钮");
                edit();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            List<ShopCarBean> deleteShopcarBeanList = CacheManager.getInstance().getDeleteShopcarBeanList();

            @Override
            public void onClick(View v) {
                if (deleteShopcarBeanList.size()>0){
                    deleteProduct(deleteShopcarBeanList);
                }else {
                    Toast.makeText(ShopCarActivity.this, "当前购物车没有要删除的数据", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/orderActivity/orderInfoActivity").navigation();
            }
        });

    }

    private void deleteProduct(List<ShopCarBean> deleteShopcarBeanList) {
            httpPresenter.deleteProducts(deleteShopcarBeanList);
    }

    private void edit() {
        Log.i("---", "edit: "+isEditMode);
            if (!isEditMode) {//如果当前页面时非编辑模式，那么则进入编辑模式
                isEditMode = true;//进入编辑模式
                llDelete.setVisibility(View.VISIBLE);
                llCheckAll.setVisibility(View.GONE);
                shopCarAdapter.setEditMode(isEditMode);
                tvShopcartEdit.setText("完成");

                if (CacheManager.getInstance().isAllSelectInEditMode()) {
                    cbAll.setChecked(true);
                }

            } else {//从编辑模式进入到正常模式
                isEditMode = false;
                llDelete.setVisibility(View.GONE);
                llCheckAll.setVisibility(View.VISIBLE);
                shopCarAdapter.setEditMode(isEditMode);
                tvShopcartEdit.setText("编辑");
            }
    }




    @Override
    protected void initPresenter() {
        super.initPresenter();
        httpPresenter = new ShopCarPresenter();
        shopCarAdapter.setShopcarPresenter(httpPresenter);
    }

    @Override
    protected void initData() {
        super.initData();
        List<ShopCarBean> shopCarBeans = CacheManager.getInstance().getShopCarBeans();
        Log.i("---", "initData: "+shopCarBeans.size());
        shopCarAdapter.updataData(shopCarBeans);


        if (CacheManager.getInstance().isAllSelected()) {
            checkboxAll.setChecked(true);
        } else {
            checkboxAll.setChecked(false);
        }

        //设置全选的点击事件
        //设置全选的点击事件
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkboxAll.isChecked()) {
                    newAllSelect = true;
                    httpPresenter.selectAllProduct(newAllSelect);
                } else {
                    newAllSelect = false;
                    httpPresenter.selectAllProduct(newAllSelect);
                }
            }
        });

        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAll.isChecked()) {//在编辑模式下，所有商品都被选中了
                    CacheManager.getInstance().selectAllProductInEditMode(true);
                } else {
                    CacheManager.getInstance().selectAllProductInEditMode(false);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopcar;
    }

    @Override
    public void onProductNumChange(String result, int position, String newNum) {
        CacheManager.getInstance().updatePositionProductNum(position,newNum);
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
    public void onError(String message) {

    }

    @Override
    public void showsLoaing() {
        showLoading();
    }

    @Override
    public void hidesLoading(boolean isSuccess) {
        hideLoadingPage(isSuccess);
    }

    @Override
    public void showEmpty() {

    }



    //监听页面 刷新数据
    @Override
    public void onDataChanged(List<ShopCarBean> shopCarBeanList) {
        shopCarAdapter.updataData(shopCarBeanList);
    }

    @Override
    public void onOneDataChanged(int position, ShopCarBean shopCarBean) {
        shopCarAdapter.notifyItemChanged(position);
    }

    @Override
    public void onMoneyChanged(String moneyValue) {
        tvShopcartTotal.setText(moneyValue);
    }

    @Override
    public void onAllSelected(boolean isAllSelect) {
        if (isEditMode) {
            cbAll.setChecked(isAllSelect);
        } else {
            checkboxAll.setChecked(isAllSelect);
        }
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopCarDataChangerListener(this);
    }
}
