package com.shopmall.bawei.shopmall1805.shoppingcar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.base.BaseFragment;
import com.example.framework.manager.CacheManager;
import com.example.net.bean.RemoveManyProductBean;
import com.example.net.bean.SelectAllBean;
import com.example.net.bean.ShopCarBean;
import com.example.net.bean.UpdateProductNumBean;
import com.example.net.bean.UpdateProductSelectedBean;
import com.shopmall.bawei.shopcar.IOnShopCarItemChildClickListener;
import com.shopmall.bawei.shopcar.ShopCarAdapter;
import com.shopmall.bawei.shopcar.ShopCarContract;
import com.shopmall.bawei.shopcar.ShopCarEditAdapter;
import com.shopmall.bawei.shopcar.ShopCarPresenterImpl;
import com.shopmall.bawei.shopmall1805.R;
import com.shoppmall.common.adapter.error.ErrorBean;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCarFragment extends BaseFragment<ShopCarPresenterImpl, ShopCarContract.ShopCarView> implements CacheManager.IShopcarDataChangeListener, ShopCarContract.ShopCarView, IOnShopCarItemChildClickListener {
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private boolean isEdit=true;
    private List<ShopCarBean.ResultBean> list=new ArrayList<>();
    private List<ShopCarBean.ResultBean> editList=new ArrayList<>();
    private ShopCarAdapter adapter;
    private ShopCarEditAdapter editAdapter;
    private String productNumChangeId="";
    private String productNumChangeNum="";
    private boolean isClickCheckBox=false;
    private boolean isClickCb=false;
    private int clickposition=-1;
    @Override
    protected void initDate() {
        presenter=new ShopCarPresenterImpl();
        presenter.attchView(this);
        List<ShopCarBean.ResultBean> shopCarList = CacheManager.getInstance().getShopCarList();
        getEditList();
        boolean isAllSelect=true;
        for (ShopCarBean.ResultBean resultBean : shopCarList) {
            if(!resultBean.isProductSelected()){
                isAllSelect=false;
            }
        }
        checkboxAll.setChecked(isAllSelect);
        if(shopCarList.size()>0){
            showSuccess();
            list.clear();
            list.addAll(shopCarList);
            llCheckAll.setVisibility(View.VISIBLE);
        }else {
            list.clear();
            showEmptyCarPage();
        }
        adapter.updataData(list);
        String value = CacheManager.getInstance().getMoneyValue();
        tvShopcartTotal.setText("¥"+value);

    }

    private void getEditList() {
        List<ShopCarBean.ResultBean> shopCarEditList = CacheManager.getInstance().getShopCarEditList();
        editList.clear();
        editList.addAll(shopCarEditList);
        editAdapter.updataData(editList);
    }

    @Override
    protected void initLisenter() {
        CacheManager.getInstance().setShopcarDataChangeListener(this);
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.selectAllProduct(cbAll.isChecked());
                isClickCheckBox=true;
                List<ShopCarBean.ResultBean> shopCarEditList = CacheManager.getInstance().getShopCarEditList();
                for (ShopCarBean.ResultBean resultBean : shopCarEditList) {
                    Log.i("Edit", "onClick:"+resultBean.isProductSelected());
                }
            }

        });
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CacheManager.getInstance().selectAllEditProduct(cbAll.isChecked());
            }
        });
    }

    @Override
    protected void initView() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView)findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) findViewById(R.id.ll_delete);
        cbAll = (CheckBox) findViewById(R.id.cb_all);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnCollection = (Button) findViewById(R.id.btn_collection);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new ShopCarAdapter(getContext(),this);
        editAdapter=new ShopCarEditAdapter(getContext(),this);

        recyclerview.setAdapter(adapter);

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
    protected int getLayoutID() {
        return R.layout.fragment_shopping_car;
    }

    @Override
    public void onDataChanged(List<ShopCarBean.ResultBean> shopCarBeanList) {
       if(isEdit){
           if(shopCarBeanList.size()>0){
               showSuccess();
               list.clear();
               list.addAll(shopCarBeanList);
               llCheckAll.setVisibility(View.VISIBLE);
           }else {
               list.clear();
               editList.clear();
               showEmptyCarPage();

           }

           adapter.updataData(list);
       }else {
           editList.clear();
           editList.addAll(shopCarBeanList);
           editAdapter.updataData(editList);
       }
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
        Toast.makeText(getContext(), ""+bean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRemoveManyError(ErrorBean bean) {
        Toast.makeText(getContext(), ""+bean.getErrorMessage(), Toast.LENGTH_SHORT).show();
        hideLoading(true,null);
    }

    @Override
    public void onSelectAllOk(SelectAllBean bean) {

        CacheManager.getInstance().selectAllProduct(checkboxAll.isChecked());

    }

    @Override
    public void onSelectAllError(ErrorBean bean) {
        Toast.makeText(getContext(), ""+bean.getErrorMessage(), Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getContext(), ""+bean.getErrorMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), ""+bean.getMessage(), Toast.LENGTH_SHORT).show();
            }

    }

    @Override
    public void onProductSelectChangeError(ErrorBean bean) {
        Toast.makeText(getContext(), ""+bean.getErrorMessage(), Toast.LENGTH_SHORT).show();
        hideLoading(true,null);
    }

    @Override
    public void showloading() {
        showLoading();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {

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