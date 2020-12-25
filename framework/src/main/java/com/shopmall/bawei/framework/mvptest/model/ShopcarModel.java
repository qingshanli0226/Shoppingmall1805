package com.shopmall.bawei.framework.mvptest.model;

import android.util.Log;

import com.shopmall.bawei.framework.callback.IShopcar;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.manager.ShopCarmanager;
import com.shopmall.bawei.net.Https;
import com.shopmall.bawei.net.HttpsFactory;
import com.shopmall.bean.Checkinven;
import com.shopmall.bean.OrderBean;
import com.shopmall.bean.OrderPaybean;
import com.shopmall.bean.Registbean;
import com.shopmall.bean.ShopcarBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopcarModel implements Constant.ShopcarConstartModel {

    @Override
    public void addshopcarData(String url, JSONObject jsonObject, final IShopcar iShopcar) {

        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                .getaddOneProduct(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Registbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Registbean registbean) {
                        Log.e("add","添加失败："+registbean.getMessage());
                         iShopcar.onSucess(registbean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("add","添加异常："+e.getMessage());
                        iShopcar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //检查库存情况
    @Override
    public void checkOneProductInventory(String url, HashMap<String, String> map, final IShopcar iShopcar) {

        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                .getcheckOneProductInventory(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Registbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Registbean registbean) {
                        Log.e("check",""+registbean.getResult());
                        iShopcar.onSucess(registbean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("check",""+e.getMessage());
                        iShopcar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    //更新服务端购物车产品的选择
    @Override
    public void updateProductSelected(String url, ShopcarBean.ResultBean shopcar, final IShopcar iShopcar) {
              JSONObject jsonObject=new JSONObject();
        boolean productSelected = shopcar.isProductSelected();
        try {
            jsonObject.put("productId",shopcar.getProductId());
            if (productSelected){
                jsonObject.put("productSelected",false);
            }else {
                jsonObject.put("productSelected",true);
            }
            jsonObject.put("productName",shopcar.getProductName());
            jsonObject.put("url",shopcar.getUrl());
            jsonObject.put("productPrice",shopcar.getProductPrice());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());

        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                   .getupdateProductSelected(url,requestBody)
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Observer<Registbean>() {
                       @Override
                       public void onSubscribe(Disposable d) {

                       }

                       @Override
                       public void onNext(Registbean registbean) {
                               iShopcar.onSucess(registbean);
                       }

                       @Override
                       public void onError(Throwable e) {
                               iShopcar.onError(e.getMessage());
                       }

                       @Override
                       public void onComplete() {

                       }
                   });
    }
    // 全选服务端购物车产品或者全不选
    @Override
    public void selectAllProduct(String url, boolean allselect, final IShopcar iShopcar) {
           JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("selected",allselect);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                .getselectAllProduct(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Registbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Registbean registbean) {
                        iShopcar.onSucess(registbean);
                    }

                    @Override
                    public void onError(Throwable e) {
                       iShopcar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    // 从服务端购物车删除多个产品
    @Override
    public void removeManyProduct(String url, final IShopcar iShopcar) {
        List<ShopcarBean.ResultBean> deleteshopcarBeanList = ShopCarmanager.getShopCarmanager().getDeleteshopcarBeanList();
        JSONArray jsonArray=new JSONArray();
        for (ShopcarBean.ResultBean resultBean : deleteshopcarBeanList) {
             JSONObject jsonObject=new JSONObject();
            try {
                jsonObject.put("productId",resultBean.getProductId());
                jsonObject.put("productNum",resultBean.getProductNum());
                jsonObject.put("productName",resultBean.getProductName());
                jsonObject.put("url",resultBean.getUrl());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonArray.toString());
        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                .getremoveManyProduct(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Registbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Registbean registbean) {
                          iShopcar.onSucess(registbean);
                    }

                    @Override
                    public void onError(Throwable e) {
                         iShopcar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //更新服务端购物车产品的数量
    @Override
    public void updateProductNum(String url,int newnum, ShopcarBean.ResultBean shopcar, final IShopcar iShopcar) {
          JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("productId",shopcar.getProductId());
            jsonObject.put("productNum",newnum);
            jsonObject.put("productName",shopcar.getProductName());
            jsonObject.put("url",shopcar.getUrl());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                .getupdateProductNum(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Registbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Registbean registbean) {
                             iShopcar.onSucess(registbean);
                    }

                    @Override
                    public void onError(Throwable e) {
                            iShopcar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    // 检查服务端多个产品是否库存充足
    @Override
    public void checkInventory(String url, final IShopcar iShopcar) {
        List<ShopcarBean.ResultBean> shopcarBeanList = ShopCarmanager.getShopCarmanager().getShopcarBeanList();
        JSONArray jsonArray=new JSONArray();
        for (ShopcarBean.ResultBean resultBean : shopcarBeanList) {
            if (resultBean.isProductSelected()){
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("productId",resultBean.getProductId());
                    jsonObject.put("productNum",resultBean.getProductNum());
                    jsonObject.put("productName",resultBean.getProductName());
                    jsonObject.put("url",resultBean.getUrl());
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
          }

        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonArray.toString());

        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                .getcheckInventory(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Checkinven>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Checkinven checkinven) {
                         iShopcar.onSucess(checkinven);
                    }

                    @Override
                    public void onError(Throwable e) {
                         iShopcar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //更改手机号
    @Override
    public void updatePhone(String url, String phone, final IShopcar iShopcar) {
          HashMap<String,String> map=new HashMap<>();
          map.put("phone",phone);
          HttpsFactory.getHttpsFactory().getinstance(Https.class)
                    .getupdatePhone(url,map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Registbean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Registbean registbean) {
                               iShopcar.onSucess(registbean);
                        }

                        @Override
                        public void onError(Throwable e) {
                              iShopcar.onError(e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }
    //更改地址
    @Override
    public void updateAddress(String url, String address, final IShopcar iShopcar) {
        HashMap<String,String> map=new HashMap<>();
        map.put("address",address);
        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                .getupdateAddress(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Registbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Registbean registbean) {
                        iShopcar.onSucess(registbean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iShopcar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getOrderInfo(String url, List<ShopcarBean.ResultBean> shop, final IShopcar iShopcar) {
                JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("subject","buy");
            jsonObject.put("subject",""+ShopCarmanager.getShopCarmanager().getMoney());
            JSONArray jsonArray=new JSONArray();
            for (ShopcarBean.ResultBean resultBean : shop) {
                JSONObject jsonObject1=new JSONObject();
                jsonObject1.put("productId",resultBean.getProductId());
                jsonObject1.put("productNum",resultBean.getProductNum());
                jsonObject1.put("productName",resultBean.getProductName());
                jsonArray.put(jsonObject1);
            }
            jsonObject.put("body",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
          Log.e("json",jsonObject.toString());
        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                  .getOrderInfo(url,requestBody)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Observer<OrderBean>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(OrderBean orderBean) {
                             iShopcar.onSucess(orderBean);
                      }

                      @Override
                      public void onError(Throwable e) {
                          iShopcar.onSucess(e.getMessage());
                      }

                      @Override
                      public void onComplete() {

                      }
                  });

    }
    //生成订单后删除生成后的数据
    @Override
    public void orderremoveManyProduct(String url, final IShopcar iShopcar) {
        List<ShopcarBean.ResultBean> selectshopcarBeanList = ShopCarmanager.getShopCarmanager().getSelectshopcarBeanList();
        JSONArray jsonArray=new JSONArray();
        for (ShopcarBean.ResultBean resultBean : selectshopcarBeanList) {
            JSONObject jsonObject=new JSONObject();
            try {
                jsonObject.put("productId",resultBean.getProductId());
                jsonObject.put("productNum",resultBean.getProductNum());
                jsonObject.put("productName",resultBean.getProductName());
                jsonObject.put("url",resultBean.getUrl());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonArray.toString());
        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                .getremoveManyProduct(url,requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Registbean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Registbean registbean) {
                        iShopcar.onSucess(registbean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iShopcar.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    // 请求服务端，是否支付成功
    @Override
    public void confirmServerPayResult(String url,boolean isShop, String OutTradeNo,String OrderInfo , final IShopcar iShopcar) {
           JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("outTradeNo",OutTradeNo);
            jsonObject.put("result",OrderInfo);
            jsonObject.put("clientPayResult",isShop);
        } catch (JSONException e) {
            e.printStackTrace();
        }
         RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());

        HttpsFactory.getHttpsFactory().getinstance(Https.class)
                  .getconfirmServerPayResult(url,requestBody)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Observer<Registbean>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(Registbean registbean) {
                              iShopcar.onSucess(registbean);
                      }

                      @Override
                      public void onError(Throwable e) {
                             iShopcar.onError(e.getMessage());
                      }

                      @Override
                      public void onComplete() {

                      }
                  });
    }

    @Override
    public void findForPay(String url, final IShopcar iShopcar) {
          HttpsFactory.getHttpsFactory().getinstance(Https.class)
                  .getfindForPay(url)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Observer<OrderPaybean>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(OrderPaybean orderPaybean) {
                               iShopcar.onSucess(orderPaybean);
                      }

                      @Override
                      public void onError(Throwable e) {
                             iShopcar.onError(e.getMessage());
                      }

                      @Override
                      public void onComplete() {

                      }
                  });
    }
   //查找待发货的订单
    @Override
    public void findForsend(String url, final IShopcar iShopcar) {
           HttpsFactory.getHttpsFactory().getinstance(Https.class)
                     .getfindForSend(url)
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(new Observer<OrderPaybean>() {
                         @Override
                         public void onSubscribe(Disposable d) {

                         }

                         @Override
                         public void onNext(OrderPaybean orderPaybean) {
                                iShopcar.onSucess(orderPaybean);
                         }

                         @Override
                         public void onError(Throwable e) {
                               iShopcar.onError(e.getMessage());
                         }

                         @Override
                         public void onComplete() {

                         }
                     });
    }
}
