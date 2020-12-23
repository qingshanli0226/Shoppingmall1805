package com.shopmall.bawei.shopmall1805.ui.fragment;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.base.BaseFragment;
import com.shopmall.bawei.framework.callback.Itest;
import com.shopmall.bawei.framework.manager.ShopCarmanager;
import com.shopmall.bawei.framework.manager.ShopUserManager;
import com.shopmall.bawei.framework.shopcar.ShopCarNet;
import com.shopmall.bawei.pay.ui.pay.util.OrderInfoUtil2_0;
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
    private Button deletebutton;
    private Button shopcarJisuan;
    @Override
    protected void createViewid(View inflate) {

         ShopCarmanager.getShopCarmanager().registiShopcarDataChangeListener(this);
         ARouter.getInstance().inject(this);



        shopcarBianji = inflate.findViewById(R.id.shopcar_bianji);

        shopcarRecycle = inflate.findViewById(R.id.shopcar_recycle);

        shopcarMoney = inflate.findViewById(R.id.shopcar_money);

        shopcarAllselect = inflate.findViewById(R.id.shopcar_allselect);

        shopcarJisuan = inflate.findViewById(R.id.shopcar_jisuan);


    }

    @Override
    protected void createEnvent() {
        final PopupWindow popupWindow=new PopupWindow();
        final View inflate = LayoutInflater.from(getContext()).inflate(R.layout.popu_shopcar, null);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(RecyclerView.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(60);
        checkBox = inflate.findViewById(R.id.popu_shopcar_allselect);
        deletebutton = inflate.findViewById(R.id.popu_shopcar_delect);
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
                if (checkBox.isChecked()){
                    ShopCarmanager.getShopCarmanager().deteallselect();
                }else {
                    ShopCarmanager.getShopCarmanager().clearalldeteshopcar();
                }
               // Toast.makeText(getContext(), ""+checkBox.isChecked(), Toast.LENGTH_SHORT).show();
                shopcarAdapter.notifyDataSetChanged();
            }
        });

        //点击删除按钮
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "删除", Toast.LENGTH_SHORT).show();
                ShopCarNet.getShopCarNet().removeManyProduct(Constants.REMOVEMANY_PRODUCT);
                shopcarAdapter.setIsselect(false);
                shopcarBianji.setText("编辑");
                popupWindow.dismiss();
            }
        });

        //点击结算
        shopcarJisuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ishopcarBeanList = ShopCarmanager.getShopCarmanager().isShopcarBeanList();
                if (!ishopcarBeanList){
                    Toast.makeText(getContext(), "请选中商品后再进行结算！！！", Toast.LENGTH_SHORT).show();
                    return;
                }
                ShopCarNet.getShopCarNet().checkInventory(Constants.CHECKINVENTORY, new Itest() {
                   @Override
                   public void ontest(String msg) {
                       if (msg.equals("200")){
                           String getphone = ShopUserManager.getInstance().getphone();
                           String getaddress = ShopUserManager.getInstance().getaddress();
                           if (getphone==null||getaddress==null){
                               ARouter.getInstance().build("/user/InfoMainActivity").navigation();
                           }else {
                               String money = ShopCarmanager.getShopCarmanager().getMoney();
                               OrderInfoUtil2_0.setMoney(money);
                               ARouter.getInstance().build("/order/OrderActivity").navigation();
                           }
                       }else {
                           Toast.makeText(getContext(), ""+msg, Toast.LENGTH_SHORT).show();
                       }
                   }
               });
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
