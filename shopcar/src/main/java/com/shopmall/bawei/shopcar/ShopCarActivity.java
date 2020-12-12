package com.shopmall.bawei.shopcar;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.base.BaseActivity;
import com.example.framework.manager.CacheManager;
import com.example.framework.view.ToolBar;
import com.example.net.bean.RemoveManyProductBean;
import com.example.net.bean.SelectAllBean;
import com.example.net.bean.ShopCarBean;
import com.example.net.bean.UpdateProductNumBean;
import com.example.net.bean.UpdateProductSelectedBean;
import com.shoppmall.common.adapter.error.ErrorBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Route(path = "/shopCar/ShopCarActivity")
public class ShopCarActivity extends BaseActivity<ShopCarPresenterImpl, ShopCarContract.ShopCarView> implements CacheManager.IShopcarDataChangeListener, ShopCarContract.ShopCarView,IOnShopCarItemChildClickListener {
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private  Intent intent;
    private boolean flag=false;
    private ToolBar emptyToolbar;
    private LinearLayout llEmpty;
    private boolean isEdit=true;
    private LinearLayout llShopcar;
    private List<ShopCarBean.ResultBean> list=new ArrayList<>();
    private ShopCarAdapter adapter;
    private List<ShopCarBean.ResultBean> editList=new ArrayList<>();
    private ShopCarEditAdapter editAdapter;
    private String productNumChangeId="";
    private String productNumChangeNum="";
    private boolean isClickCheckBox=false;
    private boolean isClickCb=false;
    private int clickposition=-1;
    List<ShopCarBean.ResultBean> deleteList=new ArrayList<>();
    @Override
    protected void initListener() {
        super.initListener();
        CacheManager.getInstance().setShopcarDataChangeListener(this);
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.selectAllProduct(cbAll.isChecked());
                isClickCheckBox=true;

            }

        });
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CacheManager.getInstance().selectAllEditProduct(cbAll.isChecked());
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteList.clear();
                List<ShopCarBean.ResultBean> shopCarEditList = CacheManager.getInstance().getShopCarEditList();
                for (ShopCarBean.ResultBean resultBean : shopCarEditList) {
                    if(resultBean.isProductSelected()){
                        deleteList.add(resultBean);
                    }
                }
                presenter.removeManyProduct(deleteList);
            }
        });
        emptyToolbar.setToolBarClickListner(new ToolBar.IToolBarClickListner() {
            @Override
            public void onLeftClick() {
                String type = intent.getStringExtra("type");
                Serializable extra = intent.getSerializableExtra("good");
                ARouter.getInstance().build("/detailpage/DetailActivity").withSerializable("good",extra).withString("type",type).navigation();
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        String type = intent.getStringExtra("type");
        Serializable extra = intent.getSerializableExtra("good");
        ARouter.getInstance().build("/detailpage/DetailActivity").withSerializable("good",extra).withString("type",type).navigation();
        finish();
    }

    @Override
    public void onRightClick() {
        if(isEdit){
            toolBar.setToolBarRightTv("完成");
            llDelete.setVisibility(View.VISIBLE);
            llCheckAll.setVisibility(View.GONE);
            recyclerview.setAdapter(editAdapter);
        }else {
            toolBar.setToolBarRightTv("编辑");
            llDelete.setVisibility(View.GONE);
            llCheckAll.setVisibility(View.VISIBLE);
            recyclerview.setAdapter(adapter);
        }
        isEdit=!isEdit;
        adapter.updataData(list);
        editAdapter.updataData(editList);
    }

    @Override
    protected void initPresenter() {
        presenter=new ShopCarPresenterImpl();
        presenter.attchView(this);
    }

    @Override
    protected void initData() {
        intent = getIntent();
        List<ShopCarBean.ResultBean> shopCarList = CacheManager.getInstance().getShopCarList();
        getEditList();
        boolean isAllSelect=true;
        for (ShopCarBean.ResultBean resultBean : shopCarList) {
            if(!resultBean.isProductSelected()){
                isAllSelect=false;
            }
        }
        checkboxAll.setChecked(isAllSelect);
        Toast.makeText(this, ""+shopCarList.size(), Toast.LENGTH_SHORT).show();
        if(shopCarList.size()>0){
            showSuccess();
            list.clear();
            list.addAll(shopCarList);
            llCheckAll.setVisibility(View.VISIBLE);
        }else {
            list.clear();
            showEmpty();
        }
        adapter.updataData(list);
        String value = CacheManager.getInstance().getMoneyValue();
        tvShopcartTotal.setText("¥"+value);

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_shop_car;
    }

    @Override
    protected void initView(){
        llShopcar = (LinearLayout) findViewById(R.id.ll_shopcar);
        llEmpty = (LinearLayout) findViewById(R.id.ll_empty);
        emptyToolbar = (ToolBar) findViewById(R.id.empty_toolbar);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) findViewById(R.id.ll_delete);
        cbAll = (CheckBox) findViewById(R.id.cb_all);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnCollection = (Button) findViewById(R.id.btn_collection);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ShopCarAdapter(this,this);
        editAdapter=new ShopCarEditAdapter(this,this);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void onDataChanged(List<ShopCarBean.ResultBean> shopCarBeanList) {
        if(shopCarBeanList.size()>0){
            showSuccess();
            list.clear();
            list.addAll(CacheManager.getInstance().getShopCarList());
            editList.clear();
            editList.addAll(CacheManager.getInstance().getShopCarEditList());
            editAdapter.updataData(editList);
            adapter.updataData(list);
            if(isEdit){
                llCheckAll.setVisibility(View.VISIBLE);
            }
        }else if (shopCarBeanList.size()==0){
            list.clear();
            editList.clear();
            editAdapter.updataData(editList);
            adapter.updataData(list);
            showEmpty();
            cbAll.setChecked(false);
            Log.i("Yoyo", "onDataChanged:aaaaaaa ");
        }



    }

    private void showSuccess() {
        llEmpty.setVisibility(View.GONE);
        llShopcar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onOneDataChanged(int position, ShopCarBean.ResultBean shopCarBean) {
        if(isEdit){
            ShopCarBean.ResultBean bean = list.get(position);
            bean=shopCarBean;
            adapter.updataData(list);
        }else {
            getEditList();
            editAdapter.updataData(editList);
        }
    }

    private void getEditList() {
        List<ShopCarBean.ResultBean> shopCarEditList = CacheManager.getInstance().getShopCarEditList();
        editList.clear();
        editList.addAll(shopCarEditList);
        editAdapter.updataData(editList);
    }
    @Override
    public void onMoneyChanged(String moneyValue) {
        tvShopcartTotal.setText("¥"+moneyValue);
    }

    @Override
    public void onAllSelected(boolean isAllSelect) {
        if(isClickCheckBox){
            checkboxAll.setChecked(isAllSelect);
            for (ShopCarBean.ResultBean resultBean : list) {
                presenter.productSelectChange(resultBean.getProductId(),isAllSelect,resultBean.getProductName(),resultBean.getUrl(),resultBean.getProductPrice()+"");
            }
            isClickCheckBox=false;
            adapter.updataData(list);
        }else {
            checkboxAll.setChecked(isAllSelect);
            getEditList();
            editAdapter.updataData(editList);
        }



    }

    @Override
    public void onRemoveManyOk(RemoveManyProductBean bean) {
        CacheManager.getInstance().deleteManyProduct(deleteList);
    }

    @Override
    public void onRemoveManyError(ErrorBean bean) {
        Toast.makeText(this, ""+bean.getErrorMessage(), Toast.LENGTH_SHORT).show();
        hideLoading(true,null);
    }

    @Override
    public void onSelectAllOk(SelectAllBean bean) {

        CacheManager.getInstance().selectAllProduct(checkboxAll.isChecked());

    }

    @Override
    public void onSelectAllError(ErrorBean bean) {
        Toast.makeText(this, ""+bean.getErrorMessage(), Toast.LENGTH_SHORT).show();
        hideLoading(true,null);
    }

    @Override
    public void onProductNumChangeOk(UpdateProductNumBean bean) {
        ShopCarBean.ResultBean shopcarBean = CacheManager.getInstance().getShopcarBean(productNumChangeId);
        List<ShopCarBean.ResultBean> shopCarList = CacheManager.getInstance().getShopCarList();
        int index = shopCarList.indexOf(shopcarBean);
        CacheManager.getInstance().upDataProductNum(index,productNumChangeNum);
        CacheManager.getInstance().upDataEditProductNum(index,productNumChangeNum);
    }

    @Override
    public void onProductNumChangeError(ErrorBean bean) {
        Toast.makeText(this, ""+bean.getErrorMessage(), Toast.LENGTH_SHORT).show();
        hideLoading(true,null);
    }

    @Override
    public void onProductSelectChangeOk(UpdateProductSelectedBean bean) {

        if(bean.getCode().equals("200")){
            if(clickposition!=-1){
                CacheManager.getInstance().updateProductSelected(clickposition);
                clickposition=-1;
            }
        }else {
            Toast.makeText(this, ""+bean.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onProductSelectChangeError(ErrorBean bean) {
        Toast.makeText(this, ""+bean.getErrorMessage(), Toast.LENGTH_SHORT).show();
        hideLoading(true,null);
    }

    @Override
    public void showloading() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {
        llEmpty.setVisibility(View.VISIBLE);
        llShopcar.setVisibility(View.GONE);
    }

    @Override
    public void onProductNumChange(String id, int num, String name, String url, String price) {
        presenter.productNumChange(id,num,name,url,price);
        productNumChangeId=id;
        productNumChangeNum=num+"";
    }

    @Override
    public void onProductSelectChange(int position,boolean isSelect) {
        if(isEdit){
            ShopCarBean.ResultBean bean = list.get(position);
            clickposition=position;
            presenter.productSelectChange(bean.getProductId(),isSelect,bean.getProductName(),bean.getUrl(),bean.getProductPrice()+"");
        }else {
            CacheManager.getInstance().updateProductEditSelected(position);
            List<ShopCarBean.ResultBean> editList = CacheManager.getInstance().getShopCarEditList();
            boolean isEditSelectAll=true;
            for (ShopCarBean.ResultBean resultBean : editList) {
                if(!resultBean.isProductSelected()){
                    isEditSelectAll=false;
                }
            }
            cbAll.setChecked(isEditSelectAll);
        }
    }

}