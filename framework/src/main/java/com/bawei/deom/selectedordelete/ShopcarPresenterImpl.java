package com.bawei.deom.selectedordelete;

import com.bawei.deom.CacheManager;
import com.bawei.deom.ClassInterface;
import com.bawei.deom.NetFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import bean.BaseBean;
import bean.ConfirmServerPayResultBean;
import bean.FindForPayBean;
import bean.GetOrderInfo;
import bean.InventoryBean;
import bean.Shoppingcartproducts;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopcarPresenterImpl extends ShopcarContract.SelectedandDeletedCountrollerShow {


    @Override
    public void updateProductNum(String productId, String productNum, String productName, String url, String productPrice, final int position, final String newNum) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("productNum", productNum);
            jsonObject.put("productName", productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        ClassInterface.getUserInterface().updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new NetFunction<BaseBean<String>,String>())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        pView.onProductNumChange(s,position,newNum);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void updateProductSelected(String productId, boolean productSelected, String productName, String url, String productPrice, final int position) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("productId",productId);
            jsonObject.put("productSelected",productSelected);
            jsonObject.put("productName",productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice",productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        ClassInterface.getUserInterface().updateProductSelected(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new NetFunction<BaseBean<String>,String>())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        pView.ononProductSelected(s,position);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void selectAllProduct(boolean isAllSelect) {
      JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("selected",isAllSelect);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        ClassInterface.getUserInterface().selectAllProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new NetFunction<BaseBean<String>,String>())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                      pView.onAllSelected(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void deleteProducts(List<Shoppingcartproducts.ResultBean> products) {
        JSONArray jsonArray=new JSONArray();
        for (Shoppingcartproducts.ResultBean shopcarBean:products){
            JSONObject jsonObject=new JSONObject();
            try {
                jsonObject.put("productId", shopcarBean.getProductId());
                jsonObject.put("productName", shopcarBean.getProductName());
                jsonObject.put("url", shopcarBean.getUrl());
                jsonObject.put("productNum", shopcarBean.getProductNum());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonArray.toString());
        ClassInterface.getUserInterface().removeManyProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .map(new NetFunction<BaseBean<String>,String>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                    pView.onDeleteProducts(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void checkInventory(List<Shoppingcartproducts.ResultBean> products) {  //检查服务端多个产品是否库存充足
          JSONArray jsonArray=new JSONArray();
          for (Shoppingcartproducts.ResultBean shopcarBean:products){
              JSONObject jsonObject=new JSONObject();
              try {
                  jsonObject.put("productId", shopcarBean.getProductId());
                  jsonObject.put("productNum", shopcarBean.getProductNum());
                  jsonObject.put("productName", shopcarBean.getProductName());
                  jsonObject.put("url", shopcarBean.getUrl());
                  jsonArray.put(jsonObject);
              } catch (JSONException e) {
                  e.printStackTrace();
              }
          }
          RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonArray.toString());
          ClassInterface.getUserInterface()
                  .checkInventory(requestBody)
                  .subscribeOn(Schedulers.io()).map(new NetFunction<BaseBean<List<InventoryBean>>,List<InventoryBean>>())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Observer<List<InventoryBean>>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(List<InventoryBean> inventoryBeans) {
                              pView.onInventory(inventoryBeans);
                      }

                      @Override
                      public void onError(Throwable e) {

                      }

                      @Override
                      public void onComplete() {

                      }
                  });
    }

    @Override
    public void getOrderInfo(List<Shoppingcartproducts.ResultBean> products) { //向服务端下订单接口
            JSONArray jsonArray=new JSONArray();
            for (Shoppingcartproducts.ResultBean shopcarBean:products){
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("productName",shopcarBean.getProductName());
                    jsonObject.put("productId",shopcarBean.getProductId());
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        JSONObject object = new JSONObject();
        try {
            object.put("subject", "buy");
            object.put("totalPrice", CacheManager.getInstance().getMoneyValue());
            object.put("body",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
            RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),object.toString());
            ClassInterface.getUserInterface().getOrderInfo(requestBody)
                    .subscribeOn(Schedulers.io())
                    .map(new NetFunction<BaseBean<GetOrderInfo>,GetOrderInfo>())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<GetOrderInfo>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(GetOrderInfo getOrderInfo) {
                              pView.onOrderInfo(getOrderInfo);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

    }

    @Override
    public void confirmServerPayResult(GetOrderInfo getOrderInfo, boolean flag) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("outTradeNo",getOrderInfo.getOutTradeNo());
            jsonObject.put("result",getOrderInfo.getOrderInfo());
            jsonObject.put("clientPayResult",flag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
        ClassInterface.getUserInterface().confirmServerPayResult(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConfirmServerPayResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ConfirmServerPayResultBean confirmServerPayResultBean) {
                              pView.onConfirmServerPayResult(confirmServerPayResultBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void findForPay() {
        ClassInterface.getUserInterface().findForPay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindForPayBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindForPayBean findForPayBean) {
                               pView.onFindForPay(findForPayBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
