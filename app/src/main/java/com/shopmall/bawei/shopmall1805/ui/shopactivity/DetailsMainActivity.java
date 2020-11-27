package com.shopmall.bawei.shopmall1805.ui.shopactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bean.DetailsData;
import com.shopmall.glide.Myglide;

public class DetailsMainActivity extends AppCompatActivity {
    private ImageView backDetails;


    private ImageView imageDetails;
    private TextView nameDetails;
    private TextView moneyDetails;
    private Button jiaDetails;
    private ImageView shop_image;
    private TextView shop_num;
    private TextView add_shop_name;
    private TextView shopJian;
    private TextView shopJia;
    private DetailsData details;
    private TextView addShopMoney;
    private int num=1;
    private Button addShopNo;
    private Button addShopYes;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_main);
        initview();

        initdata();

        initEnvent();
    }

    /**
     * 初始化事件
     */
    private void initEnvent() {

        //返回
        backDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 finish();
            }
        });
        /**
         * 加入购物车
         */
        jiaDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num=1;
                popupWindow=new PopupWindow();
                View inflate = LayoutInflater.from(DetailsMainActivity.this).inflate(R.layout.popupwindow_add_product, null);
                getpopuViewid(inflate);

                popupWindow.setContentView(inflate);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(265);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(v, Gravity.BOTTOM,0,0);
                popuEnvent();
            }



        });


    }

    /**
     * 点击事件
     */
    private void popuEnvent() {
        /**
         * 购买商品数量加、减点击事件
         */
        shopJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num<=1){

                }else {
                    num--;
                    shop_num.setText(num+"");
                }
            }
        });

        shopJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num>=8){

                }else {
                    num++;
                    shop_num.setText(num+"");
                }
            }
        });
        /**
         * 取消添加购物车
         */
         addShopNo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                  popupWindow.dismiss();
             }
         });
        /**
         * 确认添加购物车
         */
        addShopYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(DetailsMainActivity.this, "已加入购物车", Toast.LENGTH_SHORT).show();
                 popupWindow.dismiss();
            }
        });

    }
    /**
     * 获取popuwindow内的控件id
     * @param inflate
     */
    private void getpopuViewid(View inflate) {
        shop_image = inflate.findViewById(R.id.shop_image);
        shop_num = inflate.findViewById(R.id.shop_num);
        add_shop_name = inflate.findViewById(R.id.add_shop_name);



        addShopNo = inflate.findViewById(R.id.add_shop_no);
        addShopYes = inflate.findViewById(R.id.add_shop_yes);

        addShopMoney = inflate.findViewById(R.id.add_shop_money);


        shopJian = inflate.findViewById(R.id.shop_jian);
        shopJia = inflate.findViewById(R.id.shop_jia);

        Myglide.getMyglide().tupian(this,shop_image,details.getImage());
        add_shop_name.setText(details.getName());
        addShopMoney.setText("￥"+details.getPice());


    }
    /**
     * 数据初始化
     */
    private void initdata() {
       details =(DetailsData) getIntent().getSerializableExtra("details");
        Myglide.getMyglide().tupian(this,imageDetails,details.getImage());
        nameDetails.setText(details.getName());
        moneyDetails.setText("￥"+details.getPice());
    }

    /**
     * 初始化控件
     */
    private void initview() {



        imageDetails = findViewById(R.id.image_details);
        nameDetails = findViewById(R.id.name_details);
        moneyDetails = findViewById(R.id.money_details);
        jiaDetails = findViewById(R.id.jia_shopcar_details);



        backDetails = findViewById(R.id.back_details);

    }
}