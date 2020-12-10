package com.shopmall.bawei.framework.mvptest.presenter;

import com.shopmall.bawei.framework.callback.IShopcar;
import com.shopmall.bawei.framework.callback.Itest;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.manager.ShopCarmanager;
import com.shopmall.bawei.framework.mvptest.repository.ShopcarRepository;
import com.shopmall.bean.Registbean;
import com.shopmall.bean.ShopcarBean;

import org.json.JSONObject;

import java.util.HashMap;

public class ShopcarPresenter extends Constart.ShopcarConstartPresenter {

    public ShopcarPresenter(Constart.ShopcarConstartView shopcarConstartView) {
        super(shopcarConstartView);
    }
     //添加商品
    @Override
    public void addshopcarData(String url, JSONObject jsonObject) {
          mRepository.addshopcarData(url, jsonObject, new IShopcar() {
              @Override
              public void onSucess(Object... objects) {
                  if (objects!=null){
                      Registbean registbean=(Registbean) objects[0];
                      if (registbean.getCode().equals("200")){
                          ShopCarmanager.getShopCarmanager().ShopcarData();
                      }
                  }

              }

              @Override
              public void onError(String mag) {

              }
          });
    }
   //检查库存情况
    @Override
    public void checkOneProductInventory(String url, HashMap<String, String> map, final Itest itest) {
            mRepository.checkOneProductInventory(url,map, new IShopcar() {
                @Override
                public void onSucess(Object... objects) {
                     if (objects!=null){
                        Registbean registbean=(Registbean) objects[0];
                         itest.ontest(registbean.getMessage());
                     }
                }

                @Override
                public void onError(String mag) {

                }
            });
    }
    //更新服务端购物车产品的选择
    @Override
    public void updateProductSelected(String url, final ShopcarBean.ResultBean shopcar, final int positon) {
              mRepository.updateProductSelected(url, shopcar, new IShopcar() {
                  @Override
                  public void onSucess(Object... objects) {
                         if (objects!=null){
                             Registbean registbean=(Registbean) objects[0];
                             if (registbean.getCode().equals("200")){
                                 ShopCarmanager.getShopCarmanager().updateshopcarselect(shopcar,positon);
                             }
                         }
                  }

                  @Override
                  public void onError(String mag) {

                  }
              });
    }
    // 全选服务端购物车产品或者全不选
    @Override
    public void selectAllProduct(String url, boolean allselect) {
        mRepository.selectAllProduct(url, allselect, new IShopcar() {
            @Override
            public void onSucess(Object... objects) {
                if (objects!=null){
                    Registbean registbean=(Registbean) objects[0];
                    if (registbean.getCode().equals("200")){
                       ShopCarmanager.getShopCarmanager().ShopcarData();
                    }
                }
            }

            @Override
            public void onError(String mag) {

            }
        });
    }


    @Override
    protected void createRepository() {
        mRepository=new ShopcarRepository();
    }
}
