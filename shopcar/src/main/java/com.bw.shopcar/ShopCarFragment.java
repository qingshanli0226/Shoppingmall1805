package com.bw.shopcar;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.framework.BaseFragment;
import com.bw.framework.CacheManager;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.bw.net.bean.ShopCarBean;
import com.bw.shopcar.adapter.ShopCarAdapter;
import com.bw.shopcar.contract.ShopCarContract;
import com.bw.shopcar.presenter.ShopCarPresenter;
import com.shopmall.bawei.shopcar.R;

import java.nio.file.Path;
import java.util.List;

public class ShopCarFragment extends BaseFragment<ShopCarPresenter, ShopCarContract.ShopCarView> implements ShopCarContract.ShopCarView,View.OnClickListener{

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
    protected int getLayoutId() {
        return R.layout.fragment_shopcar;
    }



    @Override
    protected void initView(View view) {
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

        tvShopcartEdit.setOnClickListener(this);
        cbAll.setOnClickListener(this);
        checkboxAll.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        shopCarAdapter = new ShopCarAdapter();
        recyclerview.setAdapter(shopCarAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        CacheManager.getInstance().setShopCarDataChangerListener(iShopcarDataChangeListener);
    }

    private CacheManager.IShopcarDataChangeListener iShopcarDataChangeListener = new CacheManager.IShopcarDataChangeListener() {
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
            if (isEditMode){
                cbAll.setChecked(isAllSelect);
            }else {
                checkboxAll.setChecked(isAllSelect);
            }
        }
    };

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
        shopCarAdapter.updataData(shopCarBeans);

        if (CacheManager.getInstance().isAllSelected()){
            checkboxAll.setChecked(true);
        }else {
            checkboxAll.setChecked(false);
        }
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
        Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showsLoaing() {

    }

    @Override
    public void hidesLoading(boolean isSuccess) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.tv_shopcart_edit){
            edit();
        }else if (v.getId() == R.id.checkbox_all){ //正常模式下全选
            Log.i("---", "onClick: 正常模式点击全选");
            checkAll();
        }else if(v.getId() == R.id.cb_all){
            Log.i("---", "onClick: 编辑模式点击全选");

            editCheckAll();
        }else if (v.getId() == R.id.btn_delete){
            List<ShopCarBean> deleteShopcarBeanList = CacheManager.getInstance().getDeleteShopcarBeanList();
            if (deleteShopcarBeanList.size()>0){
                deleteProduct(deleteShopcarBeanList);
            }else {
                Toast.makeText(context, "没有可以删除的数据", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void deleteProduct(List<ShopCarBean> deleteShopcarBeanList ) {
        httpPresenter.deleteProducts(deleteShopcarBeanList);
    }

    private void editCheckAll() {
        if (cbAll.isChecked()){
            CacheManager.getInstance().selectAllProductInEditMode(true);
        }else {
            CacheManager.getInstance().selectAllProductInEditMode(false);
        }
    }

    private void checkAll() {
        if (checkboxAll.isChecked()){
            newAllSelect = true;
            httpPresenter.selectAllProduct(newAllSelect);
        }else {
            newAllSelect = false;
            httpPresenter.selectAllProduct(newAllSelect);
        }
    }

    private void edit() {
        if (!isEditMode){//正常模式,进入编辑模式
            isEditMode = true;
            llDelete.setVisibility(View.VISIBLE);
            llCheckAll.setVisibility(View.GONE);
            shopCarAdapter.setEditMode(isEditMode);
            tvShopcartEdit.setText("完成");
            if (CacheManager.getInstance().isAllSelectInEditMode()){
                cbAll.setChecked(true);
            }

        }else {//编辑模式,进入正常模式
            isEditMode = false;
            llDelete.setVisibility(View.GONE);
            llCheckAll.setVisibility(View.VISIBLE);
            shopCarAdapter.setEditMode(isEditMode);
            tvShopcartEdit.setText("编辑");
        }
    }
}
