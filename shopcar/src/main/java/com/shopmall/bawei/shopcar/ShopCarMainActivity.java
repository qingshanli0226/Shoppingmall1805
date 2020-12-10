package com.shopmall.bawei.shopcar;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.framework.base.BaseActivity;
import com.shopmall.bawei.framework.manager.ShopCarmanager;
import com.shopmall.bawei.shopcaradapter.ShopcarAdapter;
import com.shopmall.bean.ShopcarBean;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/shopcar/ShopCarMainActivity")
public class ShopCarMainActivity extends BaseActivity implements ShopCarmanager.IShopcarDataChangeListener{
    private List<ShopcarBean.ResultBean> shopcarlist=new ArrayList<>();
    private RecyclerView shopcarRecycle;
     private ShopcarAdapter shopcarAdapter;
    private TextView shopcarBianji;
    private CheckBox checkBox;
    private CheckBox shopcarAllselect;
    private TextView shopcarMoney;
    private Button shopcarJisuan;
    @Override
    protected void oncreatePresenter() {

    }

    @Override
    protected void initEnvent() {
        final PopupWindow popupWindow=new PopupWindow();
        View inflate = LayoutInflater.from(this).inflate(R.layout.popu_shopcar, null);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(RecyclerView.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(60);
        checkBox = inflate.findViewById(R.id.popu_shopcar_allselect);
        shopcarBianji.setOnClickListener(new View.OnClickListener() {
            @Override
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v) {
                String s = shopcarBianji.getText().toString();
                if (s.equals("编辑")){
                    popupWindow.showAsDropDown(shopcarRecycle,0,0, Gravity.BOTTOM);
                    shopcarAdapter.setIsselect(true);
                    shopcarBianji.setText("完成");
                }else {
                    shopcarAdapter.setIsselect(false);
                    popupWindow.dismiss();
                    shopcarBianji.setText("编辑");
                }

            }
        });
    }

    @Override
    protected void initview() {
        ShopCarmanager.getShopCarmanager().registiShopcarDataChangeListener(this);
        shopcarBianji = findViewById(R.id.shopcar_bianji);

        shopcarRecycle = findViewById(R.id.shopcar_recycle);


        shopcarAllselect = findViewById(R.id.shopcar_allselect);
        shopcarMoney = findViewById(R.id.shopcar_money);
        shopcarJisuan = findViewById(R.id.shopcar_jisuan);



    }

    @Override
    protected void initData() {
       shopcarlist=ShopCarmanager.getShopCarmanager().getShopcarBeanList();
        if (shopcarlist==null) {
            Toast.makeText(this, "购物车暂无商品", Toast.LENGTH_SHORT).show();
            return;
        }
        shopcarMoney.setText(ShopCarmanager.getShopCarmanager().getMoney());
       shopcarRecycle.setLayoutManager(new LinearLayoutManager(this));
       shopcarAdapter=new ShopcarAdapter();
       shopcarRecycle.setAdapter(shopcarAdapter);

       shopcarAdapter.updataData(shopcarlist);


    }

    @Override
    protected int layoutid() {
        return R.layout.shopcar_activity_main;
    }


    @Override
    public void shopcarData(List<ShopcarBean.ResultBean> shopcarBeans) {
        shopcarAdapter.updataData(shopcarlist);
    }

    @Override
    public void undateshopcar(int positon, ShopcarBean.ResultBean shopcar) {
            shopcarAdapter.notifyItemChanged(positon);
    }

    @Override
    public void getMoney(String money) {
            shopcarMoney.setText(money);
    }

    @Override
    public void getallselect(boolean isallselect) {
              shopcarAllselect.setChecked(isallselect);

    }

    @Override
    public void getdeleteallselect(boolean isallselect) {
          checkBox.setChecked(isallselect);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShopCarmanager.getShopCarmanager().uniShopcarDataChangeListener(this);
    }
}
