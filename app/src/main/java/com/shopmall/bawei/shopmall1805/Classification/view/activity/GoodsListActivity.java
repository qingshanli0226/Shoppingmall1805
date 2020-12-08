package com.shopmall.bawei.shopmall1805.Classification.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;
public class GoodsListActivity extends AppCompatActivity {
    private ImageView bank;
    private ImageView goodsImage;
    private TextView goodsText;
    private TextView goodsPay;
    private Button addcar;
    private TextView car_count;

private  String image;
private  String title;
private  String pay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        Intent intent = getIntent();
         image = intent.getStringExtra("goods_image");
         title = intent.getStringExtra("goods_title");
         pay = intent.getStringExtra("goods_pay");
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

    private void popwindow() {

        final PopupWindow popupWindow = new PopupWindow(GoodsListActivity.this);
        View inflate = LayoutInflater.from(GoodsListActivity.this).inflate(R.layout.car_popupwindow, null);
        popupWindow.setContentView(inflate);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置外部点击取消
        popupWindow.setOutsideTouchable(true);
        Button no = inflate.findViewById(R.id.no);
        ImageView car_image = inflate.findViewById(R.id.car_image);
        TextView car_title = inflate.findViewById(R.id.car_title);
        TextView car_pay = inflate.findViewById(R.id.car_pay);

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
                if (GoodsCount.shoppingcount!=0) {
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
        Glide.with(GoodsListActivity.this).load(image).into(car_image);
        car_title.setText(title);
        car_pay.setText(pay);


        //设置弹出的位置
        popupWindow.showAsDropDown(addcar,0,0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}