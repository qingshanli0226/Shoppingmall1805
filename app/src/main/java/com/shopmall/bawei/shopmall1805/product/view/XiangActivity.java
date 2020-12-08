package com.shopmall.bawei.shopmall1805.product.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.framework.BaseActivity;
import com.example.framework.MySql;
import com.example.net.ShopUserManger;
import com.example.net.ConfigUrl;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.entity.ShopEntityDao;
import com.shopmall.bawei.shopmall1805.product.contract.ProductDetailContract;
import com.shopmall.bawei.shopmall1805.product.presenter.ProductDetailPresenterImpl;

public class XiangActivity extends BaseActivity<ProductDetailPresenterImpl, ProductDetailContract.IProductDetailView>implements ProductDetailContract.IProductDetailView {

    private ImageView imv;
    private TextView textView;
    public static ShopEntityDao shopEntityDao;
    private MySql mySql;
    private SQLiteDatabase sqLiteDatabase;
    private TextView tv_shopcar,tv_bj;
    private Button button;
    private ImageView ivGoodinfoPhoto,btnSub,btnAdd;
    private TextView tvGoodinfoName,tvGoodinfoPrice,tvCount;
    private Button btGoodinfoCancel,btGoodinfoConfim;
    private LinearLayout ll_bj;
    private String path,name,id,money;

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
    }

    @Override
    protected void initData() {
        initView();
        Intent intent = getIntent();
        path = intent.getStringExtra("path");
       name = intent.getStringExtra("name");
         money = intent.getStringExtra("money");
         id = intent.getStringExtra("id");

         httpPresenter = new ProductDetailPresenterImpl();

        Glide.with(this).load(ConfigUrl.BASE_IMAGE+path).into(imv);

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
                        Toast.makeText(XiangActivity.this, "加入成功", Toast.LENGTH_SHORT).show();
                    }
                });
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        httpPresenter.checkOneProductNum(id,"1");
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
        if (Integer.valueOf(productNum)>=1){
            httpPresenter.addOneProduct(id,"1",name,path,money);
        }
    }

    @Override
    public void onAddProduct(String addResult) {

    }

    @Override
    public void onProductNumChange(String result) {

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