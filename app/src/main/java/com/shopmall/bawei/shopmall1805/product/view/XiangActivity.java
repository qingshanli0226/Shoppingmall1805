package com.shopmall.bawei.shopmall1805.product.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.framework.BaseActivity;
import com.example.framework.CacheManager;
import com.example.framework.MySql;
import com.example.net.ShopUserManger;
import com.example.net.ConfigUrl;
import com.example.net.ShopcarBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.entity.ShopEntityDao;
import com.shopmall.bawei.shopmall1805.product.contract.ProductDetailContract;
import com.shopmall.bawei.shopmall1805.product.presenter.ProductDetailPresenterImpl;

import java.util.List;

public class XiangActivity extends BaseActivity<ProductDetailPresenterImpl, ProductDetailContract.IProductDetailView>implements ProductDetailContract.IProductDetailView {

    private ImageView imv;
    private TextView textView;
    public static ShopEntityDao shopEntityDao;
    private MySql mySql;
    private SQLiteDatabase sqLiteDatabase;
    private TextView tv_shopcar,tv_bj;
    private Button button;
    private ImageView ivGoodinfoPhoto,btnSub,btnAdd;
    private TextView tvGoodinfoName,tvGoodinfoPrice,tvCount,tv_money;
    private Button btGoodinfoCancel,btGoodinfoConfim;
    private LinearLayout ll_bj;
    private String path,name,id,money;
    private String s;
    private int newNum;
    private ImageButton imageButton;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xiang;
    }

    protected void initView() {
        imv = (ImageView) findViewById(R.id.iv_good_info_image);
        textView = findViewById(R.id.tv_good_info_collection);
        tv_shopcar = findViewById(R.id.tv_good_info_cart);
        button = findViewById(R.id.btn_good_info_addcart);
        tv_bj = findViewById(R.id.tv_shopcart_edit);
        ll_bj = findViewById(R.id.ll_delete);
        tv_money = findViewById(R.id.tv_good_info_price);
        imageButton = findViewById(R.id.ib_good_info_back);
    }

    @Override
    protected void initData() {
        initView();
        Intent intent = getIntent();
        path = intent.getStringExtra("path");
        name = intent.getStringExtra("name");
         money = intent.getStringExtra("money");
         id = intent.getStringExtra("id");

         imageButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 finish();
             }
         });

         httpPresenter = new ProductDetailPresenterImpl();
        Glide.with(this).load(ConfigUrl.BASE_IMAGE+path).into(imv);

        tv_money.setText("$"+money+"");

        ARouter.getInstance().inject(this);

        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance()
                        .build("/duoduo/shopcar")
                        .navigation();
            }
        });
        mySql = new MySql(this);
        sqLiteDatabase = mySql.getWritableDatabase();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("Name",name);
                values.put("Path",path);
                values.put("Money",money);
                sqLiteDatabase.insert("shopcar",null,values);
            }
        });

        tv_shopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance()
                        .build("/duo/shopcar")
                        .navigation();
            }
        });

//        tv_bj.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ll_bj.setVisibility(View.VISIBLE);
//            }
//        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupWindow popupWindow = new PopupWindow();
                popupWindow.setWidth(GridView.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(GridView.LayoutParams.WRAP_CONTENT);
                View inflate = LinearLayout.inflate(XiangActivity.this,R.layout.popupwindow_add_product,null);
                popupWindow.setContentView(inflate);
                ivGoodinfoPhoto = inflate.findViewById(R.id.iv_goodinfo_photo);
                tvGoodinfoName = inflate.findViewById(R.id.tv_goodinfo_name);
                tvGoodinfoPrice = inflate.findViewById(R.id.tv_goodinfo_price);
                btnSub = inflate.findViewById(R.id.btn_sub);
                tvCount = inflate.findViewById(R.id.tv_count);
                btnAdd = inflate.findViewById(R.id.btn_add);
                btGoodinfoCancel = inflate.findViewById(R.id.bt_goodinfo_cancel);
                btGoodinfoConfim = inflate.findViewById(R.id.bt_goodinfo_confim);

                btGoodinfoConfim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        onCheckOneProduct(1+"");
                        httpPresenter.checkOneProductNum(id,"1");

                        popupWindow.dismiss();
                    }
                });
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        s = tvCount.getText().toString();
                        int num= Integer.parseInt(s);
                        num++;
                        tvCount.setText(num+"");
                    }
                });
                btnSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        s = tvCount.getText().toString();
                        int num= Integer.parseInt(s);
                        if (num!=1){
                            num--;
                        }
                        tvCount.setText(num+"");
                    }
                });
                if (!ShopUserManger.getInstance().isUserLogin()){
                    ARouter.getInstance()
                            .build("/duoduo/user")
                            .navigation();
                    return;
                }
                popupWindow.showAtLocation(inflate, Gravity.BOTTOM,0,0);
                btGoodinfoCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public void onCheckOneProduct(String productNum) {
        //判断是否有同一条数据
        if (Integer.valueOf(productNum)>=1){
            if (checkIfShopcarListHasProduct()){
                ShopcarBean shopcarBan = CacheManager.getInstance().getShopcarBan(id);
                int oldNum = Integer.valueOf(shopcarBan.getProductNum());
                newNum = oldNum+1;
                httpPresenter.updateProductNum(id,String.valueOf(newNum),name,path,money);
            }else {
                httpPresenter.addOneProduct(id,"1",name,path,money);
            }
        }
    }

    private boolean checkIfShopcarListHasProduct() {
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        if (shopcarBeanList.size() != 0){
            for (ShopcarBean shopcarBean : shopcarBeanList) {
                Log.i("TAG", "checkIfShopcarListHasProduct: "+shopcarBean.getProductId());
                if (shopcarBean.getProductId().equals(id)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean ShopcarListHashMap() {
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        for (ShopcarBean shopcarBean : shopcarBeanList) {
            if (id.equals(shopcarBean.getProductId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onAddProduct(String addResult) {
        Toast.makeText(this, "cg", Toast.LENGTH_SHORT).show();
        ShopcarBean shopcarBean = new ShopcarBean();
        shopcarBean.setProductId(id);
        shopcarBean.setProductName(name);
        shopcarBean.setProductNum("1");
        shopcarBean.setProductPrice(money);
        shopcarBean.setUrl(path);
        shopcarBean.setProductSelected(false);


        CacheManager.getInstance().add(shopcarBean);
    }

    @Override
    public void onProductNumChange(String result) {
        CacheManager.getInstance().upDataNum(id,String.valueOf(newNum));
    }

    @Override
    public void onErroy(String message) {

    }

    @Override
    public void showsloading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void showErroy(String message) {

    }

    @Override
    public void showEmpty() {

    }

}