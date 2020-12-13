package com.shopmall.bawei.shopmall1805.goods.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.common2.AddShopCarBean;
import com.example.common2.LoginBean;
import com.example.common2.UrlHelp;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.goods.contract.AddShopCarContract;
import com.shopmall.bawei.shopmall1805.goods.presenter.AddShopCarPresenter;

import mvp.CacheManager;
import mvp.view.BaseMVPActivity;
import mvp.view.ShopUserManager;

public class GoodsActivity extends BaseMVPActivity<AddShopCarPresenter, AddShopCarContract.IAddShopCar> implements AddShopCarContract.IAddShopCar{
    private ImageView bank;
    private ImageView goodsImage;
    private TextView goodsText;
    private TextView goodsPay;
    private Button addcar;
    private TextView car_count;

private  String image;
private  String title;
private  String pay;
private  String id;




    @Override
    protected void initView() {
        Intent intent = getIntent();
        image = intent.getStringExtra("goods_image");
        title = intent.getStringExtra("goods_title");
        pay = intent.getStringExtra("goods_pay");
        id = intent.getStringExtra("goods_Id");
        bank = (ImageView) findViewById(R.id.bank);
        goodsImage = (ImageView) findViewById(R.id.goods_image);
        goodsText = (TextView) findViewById(R.id.goods_text);
        goodsPay = (TextView) findViewById(R.id.goods_pay);
        addcar = (Button) findViewById(R.id.addcar);
        Glide.with(this).load(image).into(goodsImage);
        goodsPay.setText("￥"+pay);
        goodsText.setText(title);
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popwindow();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_list;
    }

    @Override
    protected void initPresenter() {

        ihttpPresenter=new AddShopCarPresenter();

    }

    @Override
    protected void initData() {

    }

    private void popwindow() {

        final PopupWindow popupWindow = new PopupWindow(GoodsActivity.this);
        View inflate = LayoutInflater.from(GoodsActivity.this).inflate(R.layout.car_popupwindow, null);
        popupWindow.setContentView(inflate);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置外部点击取消
        popupWindow.setOutsideTouchable(true);
        Button no = inflate.findViewById(R.id.no);
        ImageView car_image = inflate.findViewById(R.id.car_image);
        TextView car_title = inflate.findViewById(R.id.car_title);
        TextView car_pay = inflate.findViewById(R.id.car_pay);
        Button yes = inflate.findViewById(R.id.yes);
        car_count = inflate.findViewById(R.id.car_count);
        ImageView car_cut = inflate.findViewById(R.id.car_cut);
        ImageView car_add = inflate.findViewById(R.id.car_add);
        car_count.setText(""+GoodsCount.shoppingcount);
        car_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsCount.shoppingcount++;
                car_count.setText(""+GoodsCount.shoppingcount);
            }
        });

        car_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GoodsCount.shoppingcount>1) {
                    GoodsCount.shoppingcount--;
                    car_count.setText("" + GoodsCount.shoppingcount);
                }

            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ihttpPresenter.addShopCar(id, GoodsCount.shoppingcount+"",title,image,pay);
                GoodsCount.shoppingcount=1;
                Toast.makeText(GoodsActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
        Glide.with(GoodsActivity.this).load(image).into(car_image);
        car_title.setText(title);
        car_pay.setText(pay);


        //设置弹出的位置
        popupWindow.showAtLocation(addcar, Gravity.BOTTOM,0,0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onAddShopCar(String addResult) {

    }

    @Override
    public void showError(String code, String message) {

    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }
}