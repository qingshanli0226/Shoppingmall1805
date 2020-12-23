package com.shopmall.bawei.shopcar.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.CacheManager;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.net.bean.IntonVoryBean;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.shopcar.R;
import com.shopmall.bawei.shopcar.adpter.ShopCarAdpter;
import com.shopmall.bawei.shopcar.contract.ShopCarContract;
import com.shopmall.bawei.shopcar.presenter.ShopcarPresenterImpl;

import java.util.List;

@Route(path="/goodcar/Shpping_car_Activity")
public class Shpping_car_Activity extends BaseActivity<ShopcarPresenterImpl, ShopCarContract.IShopCarView> implements ShopCarContract.IShopCarView,  View.OnClickListener, CacheManager.IShopcarDataCharListerter {
    private ImageButton ibShopcartBack;
    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llEmptyShopcart;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private boolean isEditMode = false; // 当为true时进入编辑模式，当为false则为正常模式
    private ShopCarAdpter shopCarAdpter;
    private boolean newAllSelcted;
    @Override
    protected void initpreseter() {
        httpresenter = new ShopcarPresenterImpl();
        shopCarAdpter.setShopcarPresenter(httpresenter);
    }

    @Override
    protected void initdate() {
        ARouter.getInstance().inject(this);
        tvShopcartEdit.setOnClickListener(this);
        List<ShopcarBean> shopcarList = CacheManager.getInstance().getShopcarList();
        shopCarAdpter.updataData(shopcarList);
        //商品价格
        tvShopcartTotal.setText(""+CacheManager.getInstance().getMoney());
        //判断商品是否全部选择，全部选择就让为true，不然的话就为false
        if (CacheManager.getInstance().isAllSelected()){
            checkboxAll.setChecked(true);
        }else {
            checkboxAll.setChecked(false);
        }
        //设置正常的点击事件
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkboxAll.isChecked()){
                    newAllSelcted = true;
                    httpresenter.selectedAllProduct(newAllSelcted);
                }else {
                    newAllSelcted = false;
                    httpresenter.selectedAllProduct(newAllSelcted);
                }
            }
        });
        //编辑模式的点击事件
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAll.isChecked()){
                    //在编辑模式下所有商品被选择了
                    CacheManager.getInstance().selectAllProductInEditMode(true);
                }else{
                    CacheManager.getInstance().selectAllProductInEditMode(false);
                }
            }
        });
    }

    @Override
    protected void initview() {
        //初始化控件
        ibShopcartBack = findViewById(R.id.ib_shopcart_back);
        tvShopcartEdit = findViewById(R.id.tv_shopcart_edit);
        recyclerview = findViewById(R.id.recyclerview);
        shopCarAdpter = new ShopCarAdpter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(shopCarAdpter);
        llEmptyShopcart = findViewById(R.id.ll_empty_shopcart);
        ivEmpty = findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = findViewById(R.id.tv_empty_cart_tobuy);
        llCheckAll = findViewById(R.id.ll_check_all);
        checkboxAll = findViewById(R.id.checkbox_all);
        tvShopcartTotal = findViewById(R.id.tv_shopcart_total);
        btnCheckOut = findViewById(R.id.btn_check_out);
        llDelete = findViewById(R.id.ll_delete);
        cbAll = findViewById(R.id.cb_all);
        btnDelete = findViewById(R.id.btn_delete);
        btnCollection = findViewById(R.id.btn_collection);
        tvShopcartEdit.setOnClickListener(this);
        //去监听数据的改变，然后刷新UI
        CacheManager.getInstance().setshopcarChangedListenter(this);
    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_shopca;
    }

    @Override
    public void onClick(View v) {
        if (v == tvShopcartEdit){
            if (!isEditMode){//当前页面非编辑页面，则进入编辑页面
                isEditMode = true;//进入编辑模式
                tvShopcartEdit.setText("完成");
                llCheckAll.setVisibility(View.GONE);
                llDelete.setVisibility(View.VISIBLE);
                shopCarAdpter.setEditMode(isEditMode);//存在目前的状态
            }else {
                isEditMode = false;
                llDelete.setVisibility(View.GONE);
                tvShopcartEdit.setText("完成");
                llCheckAll.setVisibility(View.VISIBLE);
                shopCarAdpter.setEditMode(isEditMode);//存进目前的状态
            }
        }
    }

    //拿到要刷新的购物车新数据
    @Override
    public void ondataChanged(List<ShopcarBean> shopcarBeanList) {
        shopCarAdpter.updataData(shopcarBeanList);
    }
    //刷新购物车上的商品数量
    @Override
    public void onOneChanged(int position, ShopcarBean shopcarBean) {
        shopCarAdpter.notifyItemChanged(position);
    }
    @Override
    public void onManeyvhanged(String moneyValue) {
        tvShopcartTotal.setText(""+moneyValue);
    }

    @Override
    public void onAllselected(boolean isAllSelect) {
        //如果为编辑模式
        if (isEditMode){
            cbAll.setChecked(isAllSelect);
        }else {
            checkboxAll.setChecked(isAllSelect);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopcarDataChangerListener(this);
    }
    //刷新Ui
    @Override
    public void onProductChangeNum(String result, int position, String newNum) {
        CacheManager.getInstance().updateProduceNum(position,newNum);
    }
    //刷新单选
    @Override
    public void onProductSelected(String result, int position) {
        CacheManager.getInstance().updateProductSelected(position);
    }

    @Override
    public void onProductAllSelected(String result) {
        CacheManager.getInstance().selectAllProduct(newAllSelcted);
    }

    @Override
    public void onDeleteProduct(String result) {

    }

    @Override
    public void onErroy(String message) {

    }

    @Override
    public void showsloading() {
        showloading();
    }

    @Override
    public void hideloading() {
        hideLoading();
    }

    @Override
    public void showErroy(String message) {
        showerror(message);
    }

    @Override
    public void showEmpty() {
        showEnpty();
    }


}
