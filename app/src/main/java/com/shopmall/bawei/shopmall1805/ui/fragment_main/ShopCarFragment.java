package com.shopmall.bawei.shopmall1805.ui.fragment_main;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.shopmall.bawei.framework.base.BaseFragment;
import com.shopmall.bawei.framework.manager.ShopCarmanager;
import com.shopmall.bawei.framework.shopcar.ShopCarNet;
import com.shopmall.bawei.shopcaradapter.ShopcarAdapter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bean.ShopcarBean;

import java.util.List;

public class ShopCarFragment extends BaseFragment implements ShopCarmanager.IShopcarDataChangeListener {

    private ShopcarAdapter shopcarAdapter;
    private RecyclerView shopcarRecycle;
    private TextView shopcarBianji;
    private TextView shopcarMoney;
    private CheckBox shopcarAllselect;
    private CheckBox checkBox;
    @Override
    protected void createViewid(View inflate) {

         ShopCarmanager.getShopCarmanager().registiShopcarDataChangeListener(this);




        shopcarBianji = inflate.findViewById(R.id.shopcar_bianji);

        shopcarRecycle = inflate.findViewById(R.id.shopcar_recycle);

        shopcarMoney = inflate.findViewById(R.id.shopcar_money);

        shopcarAllselect = inflate.findViewById(R.id.shopcar_allselect);


    }

    @Override
    protected void createEnvent() {
        final PopupWindow popupWindow=new PopupWindow();
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.popu_shopcar, null);
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
                     popupWindow.showAsDropDown(shopcarRecycle,0,0,Gravity.BOTTOM);
                     shopcarAdapter.setIsselect(true);
                     shopcarBianji.setText("完成");
                 }else {
                     shopcarAdapter.setIsselect(false);
                     popupWindow.dismiss();
                     shopcarBianji.setText("编辑");
                 }

             }
         });

        //计算的全选/全不选
        shopcarAllselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Toast.makeText(getContext(), ""+shopcarAllselect.isChecked(), Toast.LENGTH_SHORT).show();
                ShopCarNet.getShopCarNet().selectAllProduct(shopcarAllselect.isChecked());
            }
        });

        //删除的全选/全不选
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    @Override
    protected void createData() {
         shopcarRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
         shopcarAdapter=new ShopcarAdapter();
         shopcarRecycle.setAdapter(shopcarAdapter);
    }

    @Override
    protected int fragmentid() {
        return R.layout.shopcar_fragment;
    }

    @Override
    protected void createPresenter() {

    }


    @Override
    public void shopcarData(List<ShopcarBean.ResultBean> shopcarBeans) {
        Log.e("shopcarFragment",""+shopcarBeans);
        shopcarAdapter.updataData(shopcarBeans);
    }

    @Override
    public void undateshopcar(int positon, ShopcarBean.ResultBean shopcar) {
        shopcarAdapter.notifyItemChanged(positon);
    }

    @Override
    public void getMoney(String money) {
          shopcarMoney.setText(money+"");
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
    public void onDestroy() {
        super.onDestroy();
        ShopCarmanager.getShopCarmanager().uniShopcarDataChangeListener(this);
    }
}
