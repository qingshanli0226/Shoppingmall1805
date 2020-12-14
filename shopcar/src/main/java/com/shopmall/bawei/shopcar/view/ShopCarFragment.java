package com.shopmall.bawei.shopcar.view;


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

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseFragment;
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


public class ShopCarFragment extends BaseFragment<ShopcarPresenterImpl, ShopCarContract.IShopCarView> implements ShopCarContract.IShopCarView, View.OnClickListener,CacheManager.IShopcarDataCharListerter {

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
    private LinearLayout llEmptyShopcart;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;

    private Button btnCollection;
    private boolean newAllSelect;
    private boolean isEditMode = false; // 当为true时进入编辑模式，当为false则为正常模式
    private ShopCarAdpter shopCarAdpter;
    private List<ShopcarBean> shopcarList;
    @Override
    protected void initPreseter() {
        httpresetnter = new ShopcarPresenterImpl();
        shopCarAdpter.setShopcarPresenter(httpresetnter);
    }

    @Override
    protected void initView(View inflate) {
        //初始化控件
        llEmptyShopcart = inflate.findViewById(R.id.ll_empty_shopcart);
        ivEmpty = inflate.findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = inflate.findViewById(R.id.tv_empty_cart_tobuy);
        ibShopcartBack = inflate.findViewById(R.id.ib_shopcart_back);
        tvShopcartEdit = inflate.findViewById(R.id.tv_shopcart_edit);
        recyclerview = inflate.findViewById(R.id.recyclerview);
        shopCarAdpter = new ShopCarAdpter();
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(shopCarAdpter);
        llCheckAll = inflate.findViewById(R.id.ll_check_all);
        checkboxAll = inflate.findViewById(R.id.checkbox_all);
        tvShopcartTotal = inflate.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = inflate.findViewById(R.id.btn_check_out);
        llDelete = inflate.findViewById(R.id.ll_delete);
        cbAll = inflate.findViewById(R.id.cb_all);
        btnDelete = inflate.findViewById(R.id.btn_delete);
        btnCollection = inflate.findViewById(R.id.btn_collection);
        //去监听数据的改变，然后刷新UI
        CacheManager.getInstance().setshopcarChangedListenter(this);
    }

    @Override
    protected void initdate() {
        btnDelete.setOnClickListener(this);
        tvShopcartEdit.setOnClickListener(this);
        btnCheckOut.setOnClickListener(this);
        //获取购物车商品的数据
        shopcarList = CacheManager.getInstance().getShopcarList();
        shopCarAdpter.updataData(shopcarList);
        //商品的价格
        tvShopcartTotal.setText("￥"+CacheManager.getInstance().getMoney());
        //选择去结算的数量
        btnCheckOut.setText("去结算("+CacheManager.getInstance().getSelectedShopBeans().size()+")");

        //判断商品是否全部选择，全部选择就让为true，不然的话就为false
        if (CacheManager.getInstance().isAllSelected()) {
            checkboxAll.setChecked(true);
        } else {
            checkboxAll.setChecked(false);
        }


        //设置正常全选的点击事件
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkboxAll.isChecked()) {
                    newAllSelect = true;
                    httpresetnter.selectedAllProduct(newAllSelect);
                } else {
                    newAllSelect = false;
                    httpresetnter.selectedAllProduct(newAllSelect);
                }
            }
        });
        //设置编辑全选的点击事件
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAll.isChecked()) {
                    //在编辑模式下，所有商品都被选中了
                    CacheManager.getInstance().selectAllProductInEditMode(true);
                } else {
                    CacheManager.getInstance().selectAllProductInEditMode(false);
                }
            }
        });
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_shopping_car;
    }

    @Override
    public void onClick(View v) {
        if (v == tvShopcartEdit){
            if (!isEditMode){//当前页面非编辑页面，则进入编辑页面
                isEditMode = true;//进入编辑模式
                llCheckAll.setVisibility(View.GONE);
                shopCarAdpter.setEditMode(isEditMode);//存进现在的状态
                tvShopcartEdit.setText("完成");
                llDelete.setVisibility(View.VISIBLE);

                if (CacheManager.getInstance().isAllSelectInEditMode()) {
                    cbAll.setChecked(true);
                }
            }else {//当前模式为编辑模式，再次点击则进入正常模式
                isEditMode = false;
                tvShopcartEdit.setText("编辑");
                llDelete.setVisibility(View.GONE);
                shopCarAdpter.setEditMode(isEditMode);////存进现在的状态
                llCheckAll.setVisibility(View.VISIBLE);
            }
        }else if (v == btnDelete){//删除你选择的数据
            List<ShopcarBean> shopcarBeans = CacheManager.getInstance().getdeleteShopBeanList();
            if (shopcarBeans.size()>0){
                httpresetnter.deleteProduct(shopcarBeans);
            }else {
                Toast.makeText(getContext(), "当前没有商品可以让您删除", Toast.LENGTH_SHORT).show();
            }
        }else if (v == btnCheckOut){
            //点击进入到订单详情页面
            if (CacheManager.getInstance().getSelectedShopBeans().size()>0){
                ARouter.getInstance().build("/order/Activity").navigation();
            }else {
                Toast.makeText(getContext(), "当前没有购买的商品", Toast.LENGTH_SHORT).show();
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
        //只更新这个item的Ui数据
        shopCarAdpter.notifyItemChanged(position);
    }
    //刷新购物车上面的钱的数量
    @Override
    public void onManeyvhanged(String moneyValue) {
        tvShopcartTotal.setText("￥"+moneyValue);
    }
    //刷新购物车的全选择的框
    @Override
    public void onAllselected(boolean isAllSelect) {
        if (isEditMode) {
            cbAll.setChecked(isAllSelect);
        } else {
            checkboxAll.setChecked(isAllSelect);
        }
    }



    //更改服务端商品数量
    @Override
    public void onProductChangeNum(String result, int position, String newNum) {
        //现在数据改变，本地缓存的数量也要发生改变，保证缓存与服务端的数量一样
        CacheManager.getInstance().updateProduceNum(position,newNum);
    }
    //代表服务端商品选择发生了变化
    @Override
    public void onProductSelected(String result, int position) {
        //更新本地缓存选择与服务端商品的选择保持一致
        CacheManager.getInstance().updateProductSelected(position);
        //更新选择去结算的数量
        btnCheckOut.setText("去结算("+CacheManager.getInstance().getSelectedShopBeans().size()+")");
}

    @Override
    public void onProductAllSelected(String result) {
        //更新本地缓存的数据的选择状态
        CacheManager.getInstance().selectAllProduct(newAllSelect);
        //更新选择去结算的数量
        btnCheckOut.setText("去结算("+CacheManager.getInstance().getSelectedShopBeans().size()+")");
    }

    @Override
    public void onDeleteProduct(String result) {
        //删除成功之后将你的商品从购物车移除
        CacheManager.getInstance().delteShopBeanDeleteList();
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
