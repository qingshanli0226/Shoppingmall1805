package framework.mvpc;


import android.util.Log;

import net.FoodService;
import net.RxjavaRetortUlis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import framework.Contact;

import framework.mvpc.CallBaceObserver.ClothesBeanObserver;
import framework.mvpc.CallBaceObserver.HomeBeanObserver;
import framework.mvpc.CallBaceObserver.JavaBeanObserver;
import framework.mvpc.CallBaceObserver.LoginBeanObserver;
import framework.mvpc.CallBaceObserver.RegisterBeanObserver;
import framework.mvpc.CallBaceObserver.ShopBeanObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mode.BaseBean;
import mode.ClothesBean;
import mode.HomeBean;
import mode.LableBean;
import mode.LoginBean;
import mode.RegisterBean;
import mode.ShopcarBean;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public
class JsonModel implements Contact.centerUserImodel {

    private HashMap<String,String> hashMap = new HashMap<>();
    @Override
    public void getshopcal(int count) {
        FoodService foodService  = RxjavaRetortUlis.getInstance().create(FoodService.class);

        Observable<ClothesBean> clothesBeanObservable = null;
        Observable<LableBean> javabeanObservable = null;
        Observable<HomeBean> homeBeanObservable = null;
        if (count==0){
            clothesBeanObservable = foodService.getSkirt();
        }else if (count==1){
            clothesBeanObservable = foodService.getJacket();
        }else if (count==2){
            clothesBeanObservable = foodService.getTrouser();
        }else if (count==3){
            clothesBeanObservable = foodService.getCoat();
        }else if (count==4){
            clothesBeanObservable = foodService.getAccessories();
        }else if (count==5){
            clothesBeanObservable = foodService.getBagUrl();//包
        }else if (count==6){
            clothesBeanObservable = foodService.getDressUpUrl();
        }else if (count==7){
            clothesBeanObservable = foodService.getAreLife();
        }else if (count==8){
            clothesBeanObservable = foodService.getOfficeSupplies();
        }else if (count==9){
            clothesBeanObservable = foodService.getNumericalCode();
        }else if (count==10){
            clothesBeanObservable = foodService.getTheGameZone();
        }else if (count==11){//首页
            homeBeanObservable = foodService.getHome();
        }else if (count==12){
            javabeanObservable = foodService.getLable();
        }
        if (clothesBeanObservable!=null){
            clothesBeanObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ClothesBeanObserver(){
                        @Override
                        public void onNext(ClothesBean clothesBean) {
                            JsonPresenter.clothesBeanObserver.onNext(clothesBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            JsonPresenter.clothesBeanObserver.onError(e);
                        }
                    });
        }
        if (javabeanObservable!=null){
            javabeanObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new JavaBeanObserver(){
                        @Override
                        public void onNext(LableBean javabean) {
                            JsonPresenter.javabeanObserver.onNext(javabean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            JsonPresenter.javabeanObserver.onError(e);
                        }
                    });

        }
        if (homeBeanObservable!=null){
            homeBeanObservable.subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new HomeBeanObserver(){
                      @Override
                      public void onNext(HomeBean homeBean) {
                          JsonPresenter.homeBeanObserver.onNext(homeBean);
                      }

                      @Override
                      public void onError(Throwable e) {
                          JsonPresenter.homeBeanObserver.onError(e);
                      }
                  });
        }

    }

    @Override
    public void loginAndRegister(int count, final String username, String password) {
        hashMap.put("name",username);
        hashMap.put("password",password);
        FoodService foodService  = RxjavaRetortUlis.getInstance().create(FoodService.class);
        Observable<RegisterBean> register = null;
        Observable<LoginBean> login = null;
        if (count==1){
              register = foodService.goToRegister(hashMap);
        }else {
              login = foodService.goToLogin(hashMap);
        }
        if (register!=null){
            register.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RegisterBeanObserver(){
                        @Override
                        public void onNext(RegisterBean registerBean) {
                           JsonPresenter.registerBeanObserver.onNext(registerBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            JsonPresenter.registerBeanObserver.onError(e);
                        }
                    });
        }
        if (login !=null){
            login.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new LoginBeanObserver(){
                        @Override
                        public void onNext(LoginBean loginBean) {
                            if (loginBean!=null){
                                JsonPresenter.loginBeanObserver.onNext(loginBean);
                            }
                        }
                        @Override
                        public void onError(Throwable e) {
                            JsonPresenter.loginBeanObserver.onError(e);

                        }
                    });
        }



    }

    @Override
    public void shcarShop(int count) {
        FoodService foodService = RxjavaRetortUlis.getInstance().create(FoodService.class);
        foodService.getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopBeanObserver(){
                    @Override
                    public void onNext(BaseBean<List<ShopcarBean>> listBaseBean) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    @Override
    public void addShcarshop(ShopcarBean shopcarBean) {
        Log.i("====","添加的商品"+shopcarBean.toString());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", shopcarBean.getProductId());
            jsonObject.put("productNum", shopcarBean.getProductNum());
            jsonObject.put("productName", shopcarBean.getProductName());
            jsonObject.put("url", shopcarBean.getUrl());
            jsonObject.put("productPrice", shopcarBean.getProductPrice());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

        RxjavaRetortUlis.getInstance().create(FoodService.class)
                .addOneProduct(requestBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        Log.i("====","返回的数据"+stringBaseBean.toString());
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
