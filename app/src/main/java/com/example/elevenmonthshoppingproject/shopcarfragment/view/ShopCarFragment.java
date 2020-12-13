package com.example.elevenmonthshoppingproject.shopcarfragment.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.elevenmonthshoppingproject.R;
import com.example.elevenmonthshoppingproject.adapter.ShopcarAdapter;
import com.example.framwork.BaseMVPFragment;
import com.example.elevenmonthshoppingproject.home.view.HotAdapter;
import com.example.framwork.CacheManager;
import com.example.net.InventoryBean;
import com.example.net.OrderInfoBean;
import com.example.net.bean.ShopcarBean;
import com.example.shopercar.contract.ShopCarContract;
import com.example.shopercar.presenter.ShopCarPresenterImpl;

import java.util.List;

public class ShopCarFragment extends BaseMVPFragment<ShopCarPresenterImpl, ShopCarContract.ShopCarIView> implements ShopCarContract.ShopCarIView {
    private RecyclerView shopcarRv;
    private ShopcarAdapter shopcarAdapter;
    private TextView totalPriceTv;
    private CheckBox allSelectCheckBox;
    private boolean newAllSelect;
    private ShopCarPresenterImpl shopCarPresenter;


    private RelativeLayout reView;
    private CheckBox productSelect;
    private ImageView productImage;
    private TextView productName;
    private TextView productPrice;
    private ImageView btnAdd;
    private ImageView btnSub;
    private TextView productCount;







    private boolean isEditMode = false; //该标记位，当为true时，该页面为编辑模式，可以删除列表的商品时速局。当为false时，当前页面为正常模式，可以支付
    private RelativeLayout normalLayout;//正常模式下的底部布局
    private RelativeLayout editLayout;//编辑模式下的底部布局
    private CheckBox editAllSelectCheckBox;
    private int i;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopcar;
    }

    private  CacheManager.IShopCarChangeLinsterner iShopCarChangeLinsterner=new CacheManager.IShopCarChangeLinsterner() {
        @Override
        public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
            shopcarAdapter.updatelist(shopcarBeanList);
        }

        @Override
        public void onOneDataChanged(int position, ShopcarBean shopcarBean) {
            shopcarAdapter.notifyItemChanged(position);
        }

        @Override
        public void onMoneyChanged(String moneyValue) {
            totalPriceTv.setText(moneyValue+"");

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
    protected void iniView(View view) {
        reView = view.findViewById(com.example.shopercar.R.id.re_view);
        productSelect = view.findViewById(com.example.shopercar.R.id.productSelect);
        productImage = view.findViewById(com.example.shopercar.R.id.productImage);
        productName =view. findViewById(com.example.shopercar.R.id.productName);
        productPrice = view.findViewById(com.example.shopercar.R.id.productPrice);
        btnAdd = view.findViewById(com.example.shopercar.R.id.btnAdd);
        btnSub =view.findViewById(com.example.shopercar.R.id.btnSub);
        productCount = view.findViewById(com.example.shopercar.R.id.productCount);

//        shopcarRv = view.findViewById(R.id.shopcarRv);
//        shopcarRv.setLayoutManager(new LinearLayoutManager(getActivity()));
//        shopcarAdapter = new ShopcarAdapter();
//        shopcarRv.setAdapter(shopcarAdapter);
        totalPriceTv = view.findViewById(R.id.sumValue);
        allSelectCheckBox =view.findViewById(R.id.allSelect);
        normalLayout = view.findViewById(R.id.normalLayout);
        editLayout = view.findViewById(R.id.editLayout);
        editAllSelectCheckBox =view. findViewById(R.id.allEditSelect);


        Intent intent = getActivity().getIntent();
        String hotimg = intent.getStringExtra("hotimg");
        String hottxt = intent.getStringExtra("hottxt");
        String hotprice = intent.getStringExtra("hotprice");
        final String count = intent.getStringExtra("count");

        reView.setVisibility(View.VISIBLE);
        if (allSelectCheckBox.isChecked()){
            productSelect.setChecked(true);
        }else {
            productSelect.setChecked(false);
        }
        productSelect.setChecked(true);
        productCount.setText(count+"");
        Glide.with(this).load(hotimg).into(productImage);
        productName.setText(hottxt);
        productPrice.setText(hotprice);
        productCount.setText(count+"");
         i = Integer.parseInt(count);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                productCount.setText(ShopCarFragment.this.i +"");

            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopCarFragment.this.i--;
                productCount.setText(ShopCarFragment.this.i +"");
                if (ShopCarFragment.this.i ==0){
                    reView.setVisibility(View.GONE);
                }

            }
        });


    }
    @Override
    protected void iniData() {

    }

    @Override
    protected void iniPresenter() {
//        shopCarPresenter=new ShopCarPresenterImpl();
//        shopcarAdapter.setShopcarAdapter(ihttpPresenter);
    }

    @Override
    protected void iniHttpData() {
        //获取购物车
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
//        shopcarAdapter.updatelist(shopcarBeanList);
        totalPriceTv.setText(CacheManager.getInstance().getMoneyValue());

        CacheManager.getInstance().setShopcarDataChangeListener(iShopCarChangeLinsterner);
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
                    ihttpPresenter.selectAllProduct(newAllSelect);
                } else {
                    newAllSelect = false;
//                    ihttpPresenter.selectAllProduct(newAllSelect);
                }
            }
        });

        editAllSelectCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editAllSelectCheckBox.isChecked()) {//在编辑模式下，所有商品都被选中了
                    CacheManager.getInstance().selectAllProductInEditMode(true);
                } else {
                    CacheManager.getInstance().selectAllProductInEditMode(false);
                }
            }
        });


    }

    @Override
    public void onError(String code, String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onProductNumChange(String result, int position, String newNum) {
        CacheManager.getInstance().updateProductNum(position, newNum);
    }

    @Override
    public void onProductSelect(String result, int position) {
        CacheManager.getInstance().updateProductSelected(position);
    }

    @Override
    public void onAllSelect(String result) {
        CacheManager.getInstance().selectAllProduct(newAllSelect);
    }

    @Override
    public void onDeleteProduct(String result) {
        CacheManager.getInstance().processDeleteProducts();
    }

    @Override
    public void onInventory(List<InventoryBean> inventoryBeans) {

    }

    @Override
    public void onOnderInfo(OrderInfoBean orderInfoBean) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopcarDataChangerListener(iShopCarChangeLinsterner);
    }
}
