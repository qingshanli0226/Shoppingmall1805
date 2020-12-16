package com.shopmall.bawei.shopmall1805.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.common.Constants;
import com.shopmall.framework.callback.ITest;
import com.shopmall.framework.manager.ShopUserManager;
import com.shopmall.framework.service.ShopCarNet;
import com.shopmall.net.bean.DetailsData;
import com.shopmall.net.glide.Myglide;
import com.shopmall.net.share.RestName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class GoodsInfoActivity extends AppCompatActivity {
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView imageDetails;
    private TextView nameDetails;
    private TextView moneyDetails;
    private TextView shopCar;
    private Button jiaShopcarDetails;
    private TextView shopLike;

    private DetailsData details;
    private int num=1;
    private PopupWindow popupWindow;
    private ImageView iv_goodinfo_photo;
    private TextView tv_goodinfo_name;
    private TextView tv_goodinfo_price;
    private TextView shop_jian;
    private TextView shop_num;
    private TextView shop_jia;
    private Button bt_goodinfo_cancel;
    private Button bt_goodinfo_confim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);

        initView();

        initData();

        initEnvent();
    }

    private void initEnvent() {
        ibGoodInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        jiaShopcarDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = 1;
                popupWindow = new PopupWindow();
                View inflate = LayoutInflater.from(GoodsInfoActivity.this).inflate(R.layout.popupwindow_add_product, null);
                getPopuView(inflate);

                popupWindow.setContentView(inflate);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(400);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
                popuEnvent();
            }
        });

        shopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/shopcar/ShopCarActivity").navigation();
            }
        });

        shopLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GoodsInfoActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void popuEnvent() {
        shop_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num<=1){

                }else {
                    num--;
                    shop_num.setText(num+"");
                }
            }
        });

        shop_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num>=8){

                }else {
                    num++;
                    shop_num.setText(num+"");
                }
            }
        });

        bt_goodinfo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        bt_goodinfo_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShopUserManager.getInstance().getUserName()==null){
                    ARouter.getInstance().build("/user/UserActivity").navigation();
                    return;
                }

                final HashMap<String, String> map = new HashMap<>();
                map.put("productId",details.getId());
                map.put("productNum",num+"");

                ShopCarNet.getShopCarNet().checkOneProductInventory(Constants.CHECKONE__PRODUCT, map, new ITest() {
                    @Override
                    public void onTest(String msg) {
                        if (msg.equals(RestName.PRODUCT_MESSAGE)){
                            Toast.makeText(GoodsInfoActivity.this, "产品数量不足", Toast.LENGTH_SHORT).show();
                        }else {
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("productId",details.getId());
                                jsonObject.put("productNum",String.valueOf(num));
                                jsonObject.put("productName",details.getName());
                                jsonObject.put("url",details.getImage());
                                jsonObject.put("productPrice",details.getPrice());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            ShopCarNet.getShopCarNet().addShopCarData(Constants.ADDONE_PRODUCT,jsonObject);
                            Toast.makeText(GoodsInfoActivity.this, "已加入购物车", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                popupWindow.dismiss();
            }
        });
    }

    private void getPopuView(View inflate) {
        ARouter.getInstance().inject(this);
        iv_goodinfo_photo = inflate.findViewById(R.id.iv_goodinfo_photo);
        tv_goodinfo_name = inflate.findViewById(R.id.tv_goodinfo_name);
        tv_goodinfo_price = inflate.findViewById(R.id.tv_goodinfo_price);

        shop_jian = inflate.findViewById(R.id.shop_jian);
        shop_num = inflate.findViewById(R.id.shop_num);
        shop_jia = inflate.findViewById(R.id.shop_jia);

        bt_goodinfo_cancel = inflate.findViewById(R.id.bt_goodinfo_cancel);
        bt_goodinfo_confim = inflate.findViewById(R.id.bt_goodinfo_confim);

        Myglide.getMyglide().disGlide(this,iv_goodinfo_photo,details.getImage());
        tv_goodinfo_name.setText(details.getName());
        tv_goodinfo_price.setText("￥"+details.getPrice());
    }

    private void initData() {
        details = (DetailsData) getIntent().getSerializableExtra("details");
        Myglide.getMyglide().disGlide(this,imageDetails,details.getImage());
        nameDetails.setText(details.getName());
        moneyDetails.setText("￥"+details.getPrice());
    }

    private void initView() {
        shopLike = (TextView) findViewById(R.id.shop_like);
        ibGoodInfoBack = (ImageButton) findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = (ImageButton) findViewById(R.id.ib_good_info_more);
        imageDetails = (ImageView) findViewById(R.id.image_details);
        nameDetails = (TextView) findViewById(R.id.name_details);
        moneyDetails = (TextView) findViewById(R.id.money_details);
        shopCar = (TextView) findViewById(R.id.shop_car);
        jiaShopcarDetails = (Button) findViewById(R.id.jia_shopcar_details);

    }
}