package com.shopmall.bawei.shopmall1805;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.deom.BaseActivity;
import com.bawei.deom.CacheManager;
import com.bawei.deom.ShopUserManager;
import com.bawei.deom.addPage.AddCountroller;
import com.bawei.deom.addPage.AddImpl;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.home.MainActivity;
import com.shopmall.bawei.shopmall1805.login.LoginActivity;
import com.shopmall.bawei.shopmall1805.user.ShangPing;

import java.util.List;

import bean.Shoppingcartproducts;

@Route(path = "/mainactivity/DetailsActivity")
public class DetailsActivity extends BaseActivity<AddImpl,AddCountroller.AddView> implements AddCountroller.AddView,CacheManager.IShopcarDataChangeListener,View.OnClickListener {
    private ImageView image;
    private TextView text;
    private TextView price;
    private Button pop;
    private TextView gouwu;
    private RelativeLayout rootview;



    int  newNum=1;
    private int size=1;
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
        Intent intent=getIntent();
        shangp= (ShangPing) intent.getSerializableExtra("shangp");
        Glide.with(this).load(shangp.getUrl()).into(image);
        text.setText(shangp.getProductName());
        price.setText(shangp.getProductPrice());

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!ShopUserManager.getInstance().isUserLogin()){
//                    Intent intent2=new Intent(XIangqingActivity.this, LoginActivity.class);
//                    return;
//                }

                final PopupWindow popupWindow=new PopupWindow();
                View view= LayoutInflater.from(DetailsActivity.this).inflate(R.layout.pop,null);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                final ImageView pic = view.findViewById(R.id.pic);
                Glide.with(DetailsActivity.this).load(shangp.getUrl()).into(pic);
                TextView name = view.findViewById(R.id.name);
                name.setText(shangp.getProductName()+"");
                TextView yuan = view.findViewById(R.id.yuan);
                yuan.setText(shangp.getProductPrice()+"");
                final TextView num = view.findViewById(R.id.num);
                num.setText(""+size);

                Button del=view.findViewById(R.id.del);
                del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                    }
                });
                Button add=view.findViewById(R.id.add);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        num.setText(String.valueOf(size++));
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
//                        checkHasProduct();
                        prine.CheckOneProductNum(shangp.getProductId(),String.valueOf(newNum));
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setContentView(view);

                popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
            }
        });
        gouwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this, "1111", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
     ShangPing  shangp;
    @Override
    protected void intView() {
        image = (ImageView) findViewById(R.id.image);
        text = (TextView) findViewById(R.id.text);
        price = (TextView) findViewById(R.id.price);
        pop = (Button) findViewById(R.id.pop);
        gouwu = (TextView) findViewById(R.id.gouwu);
        rootview = (RelativeLayout) findViewById(R.id.rootview);
        ARouter.getInstance().inject(
                this
        );
        CacheManager.getInstance().setShopcarDataChangeListerner(this);
         findViewById(R.id.pop).setOnClickListener(this);

    }

    @Override
    public void onCheckOneProductInventoryView(String productNum) {
        //服务端将仓库数量返回
             if (Integer.valueOf(productNum)>=1){
                 if (checkIfShopcarListHasProduct()){
                     //当前仓库有盖上平吧盖上平添加到购物车
                     //添加个判断判断当前这个商品在购物车是不是已经有了，如果有了只是把这个商品数量增加一个如果购物车上没有这个商品再把商品添加到购物车上防止一个商品出现两条数据
                     Shoppingcartproducts.ResultBean shopcarBean=CacheManager.getInstance().getShopcarBan(shangp.getProductId());
                     int  oldNum=Integer.parseInt(shopcarBean.getProductNum());
                     newNum=oldNum+1;
                     prine.UpdateProductNum(shangp.getProductId(),String.valueOf(newNum),shangp.getProductName(),shangp.getUrl(),shangp.getProductPrice());
                 }else {
                     prine.AddOneProduct(shangp.getProductId(),"1",shangp.getProductName(),shangp.getUrl(),shangp.getProductPrice());
                 }
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
    public void onAddShoppingView(String addResult) {
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
    public void onProductNumChange(String result) {
    //已经成功把购物车商品数据增加一个
        //更新本地缓存中的商品数量始让他们两个保持一致
      CacheManager.getInstance().updateProductNum(shangp.getProductId(),String.valueOf(newNum));

    }

    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }
    private  void checkHasProduct(){
        prine.CheckOneProductNum(shangp.getProductId(),"1");
    }

    @Override
    public void onDataChanged(List<Shoppingcartproducts.ResultBean> shopcarBeanlist) {

    }

    @Override
    public void onOneDataChanger(int pstion, Shoppingcartproducts.ResultBean shopcarBeanlist) {

    }

    @Override
    public void onMeneyChanged(String moneyValue) {

    }

    @Override
    public void onAllSelected(boolean isAllSelect) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    private void showShopcarAnim(final  int type) {
        final  ImageView imageView=new ImageView(this);
        final  RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(100,900);
        imageView.setLayoutParams(params);
         Glide.with(DetailsActivity.this).load(shangp.getUrl()).into(imageView);
         rootview.addView(imageView);
         int []startPrint=new int[2];
         imageView.getLocationInWindow(startPrint);

         int endPoint[]=new int[2];
         endPoint[0]=startPrint[0];
         endPoint[1]=startPrint[1]-1000;

         int[] control1=new int[2];
         control1[0]=startPrint[0]+400;
         control1[1]=startPrint[1]+600;

         int[] countrol2=new int[2];
         countrol2[0]=startPrint[0]+400;
         countrol2[1]=startPrint[1]-300;
        Path path=new Path();
        path.moveTo(400,400);
        final PathMeasure pathMeasure=new PathMeasure(path,false);
        finish(); float lenth=pathMeasure.getLength();
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,lenth);
        valueAnimator.setDuration(5000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                 float value=(float) animation.getAnimatedValue();
                 float[]nextPostion=new float[2];
                 pathMeasure.getPosTan(value,nextPostion,null);
                 imageView.setTranslationX(nextPostion[0]);
                 imageView.setTranslationY(nextPostion[1]);

            }
        });
    }
}
