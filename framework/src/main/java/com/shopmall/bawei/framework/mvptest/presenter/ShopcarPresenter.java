package com.shopmall.bawei.framework.mvptest.presenter;

import android.util.Log;

import com.shopmall.bawei.framework.callback.IShopcar;
import com.shopmall.bawei.framework.callback.Itest;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.manager.ShopCarmanager;
import com.shopmall.bawei.framework.manager.ShopUserManager;
import com.shopmall.bawei.framework.mvptest.repository.ShopcarRepository;
import com.shopmall.bean.Checkinven;
import com.shopmall.bean.OrderBean;
import com.shopmall.bean.Registbean;
import com.shopmall.bean.ShopcarBean;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class ShopcarPresenter extends Constant.ShopcarConstartPresenter {

    public ShopcarPresenter(Constant.ShopcarConstartView shopcarConstartView) {
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
    // 从服务端购物车删除多个产品
    @Override
    public void removeManyProduct(String url) {
          mRepository.removeManyProduct(url, new IShopcar() {
              @Override
              public void onSucess(Object... objects) {
                  if (objects!=null){
                      Registbean registbean=(Registbean) objects[0];
                      if (registbean.getCode().equals("200")){
                            ShopCarmanager.getShopCarmanager().removeselect();
                      }
                  }
              }

              @Override
              public void onError(String mag) {

              }
          });
    }
    //更新服务端购物车产品的数量
    @Override
    public void updateProductNum(String url, final int newnum, final ShopcarBean.ResultBean shopcar, final int positon) {
        mRepository.updateProductNum(url,newnum, shopcar, new IShopcar() {
            @Override
            public void onSucess(Object... objects) {
                    if (objects!=null){
                        Registbean registbean=(Registbean) objects[0];
                        if (registbean.getCode().equals("200")){
                            ShopCarmanager.getShopCarmanager().updateshopcarnum(shopcar,newnum,positon);
                        }
                    }
            }

            @Override
            public void onError(String mag) {

            }
        });
    }
    // 检查服务端多个产品是否库存充足
    @Override
    public void checkInventory(String url, final Itest itest) {
         mRepository.checkInventory(url, new IShopcar() {
             @Override
             public void onSucess(Object... objects) {
                 if (objects!=null){
                     Checkinven checkinven =(Checkinven) objects[0];
                     if (checkinven.getCode().equals("200")){
                         List<Checkinven.ResultBean> result = checkinven.getResult();
                         List<ShopcarBean.ResultBean> shopcarBeanList = ShopCarmanager.getShopCarmanager().getSelectshopcarBeanList();

                         for (int i = 0; i <result.size() ; i++) {
                             ShopcarBean.ResultBean resultBean = shopcarBeanList.get(i);
                             Checkinven.ResultBean resultBean1 = result.get(i);
                             if (Integer.parseInt(resultBean.getProductNum())>Integer.parseInt(resultBean1.getProductNum())){
                                    itest.ontest(resultBean.getProductName()+"产品数量不足,库存："+resultBean1.getProductNum());
                                    return;
                             }
                         }
                         itest.ontest("200");
                     }
                 }
             }

             @Override
             public void onError(String mag) {

             }
         });
    }

    @Override
    public void updatePhone(String url, String phone) {
             mRepository.updatePhone(url, phone, new IShopcar() {
                 @Override
                 public void onSucess(Object... objects) {

                        if (objects!=null){
                            Registbean registbean=(Registbean)objects[0];
                            if (registbean.getCode().equals("200")){
                                mView.get().Success(registbean.getMessage());
                                if (registbean.getResult()!=null){
                                    ShopUserManager.getInstance().setphone(registbean.getResult());
                                }
                            }
                        }
                 }

                 @Override
                 public void onError(String mag) {
                      mView.get().Error(mag);
                 }
             });
    }

    @Override
    public void updateAddress(String url, String address) {
        mRepository.updateAddress(url,address, new IShopcar() {
            @Override
            public void onSucess(Object... objects) {
                if (objects!=null){
                    Registbean registbean=(Registbean)objects[0];
                    if (registbean.getCode().equals("200")){
                        mView.get().Success(registbean.getMessage());
                        if (registbean.getResult()!=null){
                            ShopUserManager.getInstance().setaddress(registbean.getResult());
                        }

                    }
                }
            }

            @Override
            public void onError(String mag) {
                mView.get().Error(mag);
            }
        });
    }

    @Override
    public void getOrderInfo(String url, List<ShopcarBean.ResultBean> shop) {
             mRepository.getOrderInfo(url, shop, new IShopcar() {
                 @Override
                 public void onSucess(Object... objects) {
                        if (objects!=null){
                                OrderBean orderBean=(OrderBean)objects[0];
                                if (orderBean.getCode().equals("200")){
                                    mView.get().Success(orderBean,"order");
                                }
                        }
                 }

                 @Override
                 public void onError(String mag) {

                 }
             });
    }

    //生成订单后删除生成后的数据
    @Override
    public void orderremoveManyProduct(String url) {
        Log.e("delete",""+url);
        mRepository.orderremoveManyProduct(url, new IShopcar() {
            @Override
            public void onSucess(Object... objects) {
                if (objects!=null){
                    Registbean registbean=(Registbean) objects[0];
                    if (registbean.getCode().equals("200")){
                        ShopCarmanager.getShopCarmanager().deleteselectorder();
                        mView.get().Success(registbean,"remove");
                    }
                }
            }

            @Override
            public void onError(String mag) {

            }
        });
    }
    // 请求服务端，是否支付成功
    @Override
    public void confirmServerPayResult(String url, final boolean isShop,String OutTradeNo,String OrderInfo) {
        mRepository.confirmServerPayResult(url,isShop, OutTradeNo,OrderInfo, new IShopcar() {
            @Override
            public void onSucess(Object... objects) {
                  if (objects!=null){
                      if (isShop){
                          mView.get().Success(objects,isShop);
                      }else {
                          mView.get().Success(objects,isShop);
                      }

                  }
            }

            @Override
            public void onError(String mag) {
                if (mag!=null){
                    mView.get().Error(mag);
                }
            }
        });
    }
//查找待支付的订单
    @Override
    public void findForPay(String url) {
       mRepository.findForPay(url, new IShopcar() {
           @Override
           public void onSucess(Object... objects) {
                 if (objects!=null){
                     mView.get().Success(objects);
                 }
           }

           @Override
           public void onError(String mag) {
                if (mag!=null){
                    mView.get().Error(mag);
                }
           }
       });
    }
    //查找待发货的订单
    @Override
    public void findForsend(String url) {
        mRepository.findForsend(url, new IShopcar() {
            @Override
            public void onSucess(Object... objects) {
                   if (objects!=null){
                       mView.get().Success(objects);
                   }
            }

            @Override
            public void onError(String mag) {
                if (mag!=null){
                    mView.get().Error(mag);
                }
            }
        });
    }


    @Override
    protected void createRepository() {
        mRepository=new ShopcarRepository();
    }
}
