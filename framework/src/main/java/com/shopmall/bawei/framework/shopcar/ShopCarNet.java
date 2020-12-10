package com.shopmall.bawei.framework.shopcar;

import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.base.BaseUrl;
import com.shopmall.bawei.framework.callback.Itest;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.mvptest.presenter.ShopcarPresenter;
import com.shopmall.bean.ShopcarBean;

import org.json.JSONObject;

import java.util.HashMap;

public class ShopCarNet extends BaseUrl<ShopcarPresenter> implements Constart.ShopcarConstartView {
     private volatile static ShopCarNet shopCarNet;
     public static ShopCarNet getShopCarNet(){
           if (null==shopCarNet){
               synchronized (ShopCarNet.class){
                   if (shopCarNet==null){
                       shopCarNet=new ShopCarNet();
                   }
               }
           }
           return shopCarNet;
     }


    /**
     * 添加数据
     * @param url
     * @param jsonObject
     */
     public void addshopcarData(String url, JSONObject jsonObject){

            mPresenter.addshopcarData(url,jsonObject);
     }

    /**
     * 检验库存情况
     */
    public void checkOneProductInventory(String url, HashMap<String,String> map, final Itest itest){

             mPresenter.checkOneProductInventory(url,map,itest);
    }

    /**
     * 结算勾选改变产品选择
     */
    public void updateProductSelected(ShopcarBean.ResultBean shopcar,int positon){
               mPresenter.updateProductSelected(Constants.UPDATE_PRODUCTSELECTED,shopcar,positon);
    }

    /**
     * 全选服务端购物车产品或者全不选
     */
    public void selectAllProduct(boolean allselect){
            mPresenter.selectAllProduct(Constants.SELECTALL_PRODUCT,allselect);
    }





    @Override
    public void Success(Object... objects) {

    }

    @Override
    public void Error(String s) {

    }


    @Override
    protected void createPresenter() {
          mPresenter=new ShopcarPresenter(this);
    }
}
