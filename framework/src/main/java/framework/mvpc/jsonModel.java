package framework.mvpc;


import android.util.Log;

import net.FoodService;
import net.RxjavaRetortUlis;

import framework.Contact;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mode.ClothesBean;
import mode.HomeBean;
import mode.javabean;

public
class jsonModel implements Contact.centerUserImodel {

    @Override
    public void gethomeurl() {
        Log.i("====","这是model层");
        FoodService foodService = RxjavaRetortUlis.getInstance().create(FoodService.class);
        Observable<HomeBean> getfood = foodService.getfood();


        getfood.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final HomeBean homeBean) {
                        Log.i("====","homebean"+homeBean.getResult().toString());
                            Observable<HomeBean> observable = new Observable<HomeBean>() {
                                @Override
                                protected void subscribeActual(Observer<? super HomeBean> observer) {
                                    observer.onNext(homeBean);
                                    observer.onComplete();
                                }
                            };
                            observable.subscribe(jsonPresenter.observer);

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
    public void getshopcal(int count) {
        Log.i("====","count"+count);
        FoodService foodService  = RxjavaRetortUlis.getInstance().create(FoodService.class);
        Observable<ClothesBean> getfood = null;
        if (count==0){
            Log.i("====","当前点击是0");
             getfood = foodService.getsharFood();
        }else if (count==1){
            getfood = foodService.getsharFoodshang();
        }else if (count==2){
            Log.i("====","当前点击是2");
            getfood = foodService.getsharFoodxiq();
        }else if (count==3){
            getfood = foodService.getsharFoodpei();
        }else if (count==4){
            getfood = foodService.getsharFood();
        }
        getfood.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClothesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final ClothesBean homeBean) {
                        Log.i("====","model===="+homeBean.toString());
                        Observable<ClothesBean> observable = new Observable<ClothesBean>() {
                            @Override
                            protected void subscribeActual(Observer<? super ClothesBean> observer) {
                                observer.onNext(homeBean);
                                observer.onComplete();
                            }
                        };
                        observable.subscribe(jsonPresenter.observer2);
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
    public void getBiaoCal() {
        FoodService foodService = RxjavaRetortUlis.getInstance().create(FoodService.class);
        Observable<javabean> getfood = foodService.getbiao();


        getfood.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<javabean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final javabean homeBean) {
                        Log.i("====","homebean"+homeBean.getResult().toString());
                        Observable<javabean> observable = new Observable<javabean>() {
                            @Override
                            protected void subscribeActual(Observer<? super javabean> observer) {
                                observer.onNext(homeBean);
                                observer.onComplete();
                            }
                        };
                        observable.subscribe(jsonPresenter.observer3);

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
