package com.shopmall.bawei.shopmall1805;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.deom.BaseActivity;
import com.bawei.deom.CacheManager;
import com.bawei.deom.addPage.AddCountroller;
import com.bawei.deom.addPage.AddImpl;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.user.ShangPing;

import java.util.List;

import bean.Shoppingcartproducts;

@Route(path = "/mainactivity/XIangqingActivity")
public class XIangqingActivity extends BaseActivity<AddImpl,AddCountroller.AddView> implements AddCountroller.AddView {
    private ImageView image;
    private TextView text;
    private TextView price;
    private Button pop;
    private TextView gouwu;
    private int size=0;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_xiangqing;
    }

    @Override
    protected void inPresone() {
      prine=new AddImpl();
    }

    @Override
    protected void inData() {

    }
     ShangPing  shangp;
    @Override
    protected void intView() {
        image = (ImageView) findViewById(R.id.image);
        text = (TextView) findViewById(R.id.text);
        price = (TextView) findViewById(R.id.price);
        pop = (Button) findViewById(R.id.pop);
        gouwu = (TextView) findViewById(R.id.gouwu);
        ARouter.getInstance().inject(
                this
        );
              Intent intent=getIntent();
     shangp= (ShangPing) intent.getSerializableExtra("shangp");
        Glide.with(this).load(shangp.getUrl()).into(image);
          text.setText(shangp.getProductName());
          price.setText(shangp.getProductPrice());
          pop.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  final PopupWindow popupWindow=new PopupWindow();
                 View view= LayoutInflater.from(XIangqingActivity.this).inflate(R.layout.pop,null);
                 popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                 popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                final ImageView pic = view.findViewById(R.id.pic);
                Glide.with(XIangqingActivity.this).load(shangp.getUrl()).into(pic);
                TextView name = view.findViewById(R.id.name);
                name.setText(shangp.getProductName()+"");
                TextView yuan = view.findViewById(R.id.yuan);
                yuan.setText(shangp.getProductPrice()+"");
                final TextView num = view.findViewById(R.id.num);
                num.setText(""+size);

                Button jian=view.findViewById(R.id.jian);
                jian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if (size==0){
//                            num.setText("0");
//                            Toast.makeText(XIangqingActivity.this, "不能减了", Toast.LENGTH_SHORT).show();
//                        }else {
//
//                            size--;
//                            num.setText(""+size);
//
//                        }

                    }
                });
                Button jia=view.findViewById(R.id.jia);
                jia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if (size==99){
//                            num.setText(""+99);
//                            Toast.makeText(XIangqingActivity.this, "不能加了", Toast.LENGTH_SHORT).show();
//                        }else {
//                                      size++;
//                              num.setText(""+size);
//                        }
                       prine.checkOneProductNum(shangp.getProductId(),"1");
//                        prine.addOneProduct(productId,"1",productName,url,prodctPrice);
                    }
                });
                Button no=view.findViewById(R.id.no);
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                Button yes=view.findViewById(R.id.yes);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                popupWindow.setContentView(view);

                 popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
              }
          });
        gouwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XIangqingActivity.this, "1111", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build("/fragment/ShoppingFragment").navigation();
            }
        });

    }

    @Override
    public void CheckOneProductInventoryView(String productNum) {
        //服务端将仓库数量返回
             if (Integer.valueOf(productNum)>=1){
//                 if (checkIfShopcarListHasProduct()){
//                     //当前仓库有盖上平吧盖上平添加到购物车
//                     //添加个判断判断当前这个商品在购物车是不是已经有了，如果有了只是把这个商品数量增加一个如果购物车上没有这个商品再把商品添加到购物车上防止一个商品出现两条数据
//                     Shoppingcartproducts.ResultBean shopcarBean=CacheManager.getInstance().getShopcarBan(productId);
//                     int  oldNum=Integer.parseInt(shopcarBean.getProductNum());
//                     newNum=oldNum+1;
//                     prine.updateProductNum(productId,String.valueOf(newNum),productName,url,prodctPrice);
//                 }else {
                     prine.addOneProduct(shangp.getProductId(),"1",shangp.getProductName(),shangp.getUrl(),shangp.getProductPrice());
//                 }
             }
    }
   public  boolean checkIfShopcarListHasProduct(){
       List< Shoppingcartproducts.ResultBean> shopcarBeanList =CacheManager.getInstance().getShopcarBeanlist();
       for (Shoppingcartproducts.ResultBean shopcarBean:shopcarBeanList){
           if (shangp.getProductId().equals(shopcarBean.getProductId())){
               return true;
           }
       }
       return  false;
   }
    @Override
    public void AddShoppingView(String addResult) {
        Toast.makeText(this, ""+addResult, Toast.LENGTH_SHORT).show();
        Shoppingcartproducts.ResultBean shopcarBean=new Shoppingcartproducts.ResultBean();
        shopcarBean.setProductId(shangp.getProductId());
        shopcarBean.setProductName(shangp.getProductName());
        shopcarBean.setProductPrice(shangp.getProductPrice());
        shopcarBean.setProductNum("1");
        shopcarBean.setProductSelected(true);
        shopcarBean.setUrl(shangp.getUrl());
        CacheManager.getInstance().add(shopcarBean);
    }

    @Override
    public void UpdateProductNumView(String result) {
    //已经成功把购物车商品数据增加一个
        //更新本地缓存中的商品数量始让他们两个保持一致
  CacheManager.getInstance().updateProductNum(shangp.getProductId(),String.valueOf(shangp.getProductName()));
    }

    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }
    private  void checkHasProduct(){
        prine.checkOneProductNum(shangp.getProductId(),"1");
    }

//     ShangPing shangp;
//    int size=1;
//    DaoSession daoSession;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xiangqing);

//

//        daoSession=((ShopmallApplication)getApplication()).getDaoSession();

//        Intent intent=getIntent();
//         shangp= (ShangPing) intent.getSerializableExtra("shangp");
//        Toast.makeText(XIangqingActivity.this, ""+shangp.toString(), Toast.LENGTH_SHORT).show();
//        Glide.with(this).load("http://49.233.0.68:8080/atguigu/img/"+shangp.getPic()).into(image);
//        text.setText(""+shangp.getName());
//        price.setText("￥"+shangp.getPrice());
//        pop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

}
