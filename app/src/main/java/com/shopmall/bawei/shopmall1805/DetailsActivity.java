package com.shopmall.bawei.shopmall1805;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
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
import com.bawei.deom.ShopmallConstant;
import com.bawei.deom.addPage.AddCountroller;
import com.bawei.deom.addPage.AddImpl;
import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.activity.home.MainActivity;
import com.shopmall.bawei.shopmall1805.user.GoodsBean;

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
        shangp= (GoodsBean) intent.getSerializableExtra("shangp");
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
                        showShopcarAnim(1);
                    }
                });
                Button no=view.findViewById(R.id.no);
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popupWindow!=null){
                            popupWindow.dismiss();
                        }

                    }
                });
                Button yes=view.findViewById(R.id.yes);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        checkHasProduct();
                        prine.CheckOneProductNum(shangp.getProductId(),String.valueOf(newNum));
                        if (popupWindow!=null){
                            popupWindow.dismiss();
                        }

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
     GoodsBean shangp;
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
    public void onAllSelectedNum(int num) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    //贝塞尔
    private void showShopcarAnim(final  int type) {
       int []startPoint=new int[2];
       int []endPoint=new int[2];
       int []controlPoint=new int[2];
       int [] picImagePoint=new int[2];
       image.getLocationInWindow(picImagePoint);
       startPoint[0]=picImagePoint[0]+400;
       startPoint[1]=picImagePoint[1];
        Log.e("LQS 起始坐标", startPoint[0] + " " + startPoint[1]);
        int []shopcarImgPoint=new int[2];
        gouwu.getLocationInWindow(shopcarImgPoint);
        endPoint[0]=shopcarImgPoint[0]+150;
        endPoint[1]=shopcarImgPoint[1]-100;
        Log.d("LQS 终点坐标", endPoint[0] + " " + endPoint[1]);
        controlPoint[0]=startPoint[0]-300;
        controlPoint[1]=startPoint[1]+100;
        Log.d("LQS 控制点坐标", controlPoint[0] + " " + controlPoint[1]);
        //实例化一个ImageView然后将该imageview添加到更布局
        final  ImageView animImageView=new ImageView(this);
        RelativeLayout.LayoutParams animLayoutParams=new RelativeLayout.LayoutParams(100,100);
        animImageView.setLayoutParams(animLayoutParams);
        Glide.with(this).load(shangp.getUrl()).into(animImageView);
        rootview.addView(animImageView);
        //使用贝塞尔曲线完成动画
        final Path path=new Path();
        path.moveTo(startPoint[0],startPoint[1]);
        path.quadTo(controlPoint[0],controlPoint[1],endPoint[0],endPoint[1]);
        final PathMeasure pathMeasure=new PathMeasure(path,false);
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,pathMeasure.getLength());//平移属性
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value=(float)animation.getAnimatedValue();
                Log.d("LQS 动画已经平移的距离:", value + "");
                float []nextPostion=new float[2];
                pathMeasure.getPosTan(value,nextPostion,null);
                animImageView.setTranslationX(nextPostion[0]);
                animImageView.setTranslationY(nextPostion[1]);
               if (value>=pathMeasure.getLength()){
                   animImageView.setVisibility(View.GONE);
               }
            }
        });
        valueAnimator.start();
    }
}
