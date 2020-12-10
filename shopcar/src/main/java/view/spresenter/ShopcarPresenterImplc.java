package view.presenter;

import net.FoodService;
import net.RxjavaRetortUlis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mode.BaseBean;
import mode.ShopcarBean;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import view.ShopView.FragmentShopcar;
import view.contract.ShopcarContractc;

public
class ShopcarPresenterImplc extends ShopcarContractc.ShopcarPresenter {
    private FragmentShopcar fragmentShopcar;
    public ShopcarPresenterImplc(FragmentShopcar fragmentShopcar) {
        this.fragmentShopcar = fragmentShopcar;
    }

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
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RxjavaRetortUlis.getInstance().create(FoodService.class)
                .updateProductNum(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        iHttpView.showLoaDing();
                    }
                }).subscribe(new Observer<BaseBean<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBean<String> stringBaseBean) {
                    iHttpView.onProductNumChange(stringBaseBean.getResult(),position,newNum);
                    iHttpView.hideLoading(true,null);
            }

            @Override
            public void onError(Throwable e) {
                    //iHttpView.hideLoading(false,);
            }

            @Override
            public void onComplete() {

            }
        });



    }

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

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

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
                        //iHttpView.hideLoading(false,);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

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
    }

    @Override
    public void checkInventory(List<ShopcarBean> products) {

    }

    @Override
    public void getOrderInfo(List<ShopcarBean> products) {

    }
}
