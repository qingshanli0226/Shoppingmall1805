package view.spresenter;

import android.util.Log;

import net.FoodService;
import net.RxjavaRetortUlis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.util.List;

import framework.CacheManager;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mode.BaseBean;
import mode.InventoryBean;
import mode.OrderInfoBean;
import mode.ShopcarBean;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import view.loadinPage.ExceptionUtil;
import view.sview.FragmentShopcar;
import view.contract.ShopcarContractc;

public
class ShopcarPresenterImplc extends ShopcarContractc.ShopcarPresenter {

    //更新服务端购物车产品数量的接口
    @Override
    public void updateProductNum(String productId, String productNum, String productName, String url, String productPrice, final int position, final String newNum) {
        Log.i("====","购物车的选择"+productId+"数量"+productNum+"名字"+productName+"路径"+url+"价格"+productPrice+"下标"+position+"添加数量"+newNum);
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
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RxjavaRetortUlis.getInstance().create(FoodService.class)
                .updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //iHttpView.showLoaDing();
                    }
                }).subscribe(new Observer<BaseBean<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBean<String> stringBaseBean) {
                    Log.i("====","修改"+stringBaseBean);
                    iHttpView.onProductNumChange(stringBaseBean.getResult(),position,newNum);
                    iHttpView.hideLoading(true,null);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("====","修改数量"+e.getMessage());
                iHttpView.hideLoading(false,ExceptionUtil.getErrorBean(e));
            }

            @Override
            public void onComplete() {

            }
        });



    }
    //更新服务端购物车产品的选择
    @Override
    public void updateProductSelected(String productId, boolean productSelected, String productName, String url, String productPrice, final int position) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("productSelected", productSelected);
            jsonObject.put("productName", productName);
            jsonObject.put("url", url);
            jsonObject.put("productPrice", productPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RxjavaRetortUlis.getInstance().create(FoodService.class)
                .updateProductSelected(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iHttpView.showLoaDing();
                    }
                })

                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        iHttpView.onProductSelected(stringBaseBean.getResult(),position);
                    }

                    @Override
                    public void onError(Throwable e) {
                      //  iHttpView.hideLoading(false,ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
    //全选购物车产品或者全不选
    @Override
    public void selectAllProduct(boolean isAllSelect) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("selected", isAllSelect);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RxjavaRetortUlis.getInstance().create(FoodService.class)
                .selectAllProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iHttpView.showLoaDing();
                    }
                })
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        iHttpView.onAllSelected(stringBaseBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                      //  iHttpView.hideLoading(false,ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    //从服务端购物车删除一个产品
    @Override
    public void deleteProducts(List<ShopcarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for(ShopcarBean shopcarBean:products) {
            JSONObject jsonObject = new JSONObject();
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

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonArray.toString());

        RxjavaRetortUlis.getInstance().create(FoodService.class)
                .removeManyProduct(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        iHttpView.onDeleteProducts(stringBaseBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        iHttpView.hideLoading(false,ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //检查服务端多个产品是否充足
    @Override
    public void checkInventory(List<ShopcarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for (ShopcarBean shopcarBean : products){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("productId",shopcarBean.getProductId());
                jsonObject.put("productNum",shopcarBean.getProductNum());
                jsonObject.put("productName",shopcarBean.getProductName());
                jsonObject.put("url",shopcarBean.getUrl());
                jsonArray.put(jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonArray.toString());

        RxjavaRetortUlis.getInstance().create(FoodService.class)
                .checkInventory(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .subscribe(new Observer<BaseBean<List<InventoryBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<List<InventoryBean>> listBaseBean) {
                        iHttpView.onInventory(listBaseBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        iHttpView.hideLoading(false,ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //像服务器下订单接口
    @Override
    public void getOrderInfo(List<ShopcarBean> products) {
        JSONArray jsonArray = new JSONArray();
        for (ShopcarBean shopcarBean : products){
            JSONObject  jsonObject = new JSONObject();
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
            object.put("subject","buy");
            object.put("totalPrice", CacheManager.getInstance().getMoneyValue());
            object.put("body",jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), object.toString());
        RxjavaRetortUlis.getInstance().create(FoodService.class)
                .getOrderInfo(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iHttpView.showLoaDing();
                    }
                })
                .subscribe(new Observer<BaseBean<OrderInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<OrderInfoBean> orderInfoBeanBaseBean) {
                        iHttpView.onOrderInfo(orderInfoBeanBaseBean.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        iHttpView.hideLoading(false,ExceptionUtil.getErrorBean(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
