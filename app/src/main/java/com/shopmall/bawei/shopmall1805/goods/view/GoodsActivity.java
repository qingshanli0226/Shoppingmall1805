package com.shopmall.bawei.shopmall1805.goods.view;

import android.content.Intent;
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
import com.example.common2.GetShopCarBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.goods.contract.AddShopCarContract;
import com.shopmall.bawei.shopmall1805.goods.presenter.AddShopCarPresenter;
import com.shopmall.bawei.user.view.UserMainActivity;

import java.util.List;

import mvp.CacheManager;
import mvp.view.BaseMVPActivity;
import mvp.ShopUserManager;

public class GoodsActivity extends BaseMVPActivity<AddShopCarPresenter, AddShopCarContract.IAddShopCar> implements AddShopCarContract.IAddShopCar, CacheManager.IShopcarDataChangeListener {
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
private int newNum=1;
private  String productId;




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
        car_count.setText(""+newNum);
        car_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newNum++;
                car_count.setText(""+newNum);
            }
        });

        car_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newNum>1) {
                    newNum--;
                    car_count.setText("" + newNum);
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
                boolean userLogin = ShopUserManager.getInstance().isUserLogin();
                if (userLogin) {

                    ihttpPresenter.addShopCar(id, String.valueOf(newNum), title, image, pay);
                    Toast.makeText(GoodsActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                    //第二步判断仓库是否有足够的产品
                    checkHasProduct();
                }else {
                    Intent intent = new Intent(GoodsActivity.this, UserMainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Toast.makeText(GoodsActivity.this, "用户未登录", Toast.LENGTH_SHORT).show();
                }

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
        CacheManager.getInstance().unSetShopcarDataChangerListener(this);


    }

    @Override
    public void onAddShopCar(String addResult) {
        GetShopCarBean addShopCarBean = new GetShopCarBean();
        addShopCarBean.setProductId(id);
        addShopCarBean.setProductName(title);
        addShopCarBean.setProductNum(String.valueOf(newNum));
        addShopCarBean.setProductPrice(pay);
        addShopCarBean.setUrl(image);
        CacheManager.getInstance().add(addShopCarBean);

    }


    @Override
    public void onCheckOneProduct(String productNum) {
        //服务端将仓库的产品数量返回
        if (Integer.valueOf(productNum)>=1){//当前有该商品,把商品添加到购物车
            //添加个判断,判断当前这个商品在购物车是不是已经有了,如果游乐,只是把这个数量增加一个,如果购物车
            //上没有这个商品,再把商品添加到购物车上,防止一个商品出现
            if (checkIfShopListHasPrdoyct()){
                GetShopCarBean shopCarBean = CacheManager.getInstance().getShopCarBean(productId);
                int oldNum = Integer.parseInt(shopCarBean.getProductNum());
                    newNum = oldNum+1;
                    ihttpPresenter.updateProductNum(productId,String.valueOf(newNum),title,image,pay);
            }else {
                ihttpPresenter.addShopCar(productId,"1",title,image,pay);
            }


        }

    }


    private boolean checkIfShopListHasPrdoyct(){
        //第一步获取当前购物车上的商品数据
        List<GetShopCarBean> shopcarBeanList = CacheManager.getInstance().getShopcarBeanList();
        for (GetShopCarBean shopCarBean:shopcarBeanList){
            if (productId.equals(shopCarBean.getProductId())){
                    return true;
            }
        }
        return false;
    }

    private void checkHasProduct(){
        ihttpPresenter.checkOnewProductNum(productId,"1");
    }

    @Override
    public void onProductNumChange(String result) {
        Log.i("lhj", "onProductNumChange: "+result);
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

    @Override
    public void onDataChanged(List<GetShopCarBean> shopcarBeanList) {
        car_count.setText(shopcarBeanList.size()+"");//刷新UI
    }

    @Override
    public void onOneDataChanged(int position, GetShopCarBean shopcarBean) {

    }

    @Override
    public void onMoneyChanged(String moneyValue) {

    }

    @Override
    public void onAllSelected(boolean isAllSelect) {

    }
}