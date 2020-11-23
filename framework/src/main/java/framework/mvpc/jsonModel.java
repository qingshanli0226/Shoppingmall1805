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
import mode.HomeBean;

public
class jsonModel implements Contact.centerUserImodel {

    @Override
    public void gethomeurl() {
        Log.i("====","这是model层");
        FoodService foodService = RxjavaRetortUlis.getInstance().create(FoodService.class);

        Observable<HomeBean> getfood = foodService.getfood();

        RxjavaRetortUlis.getInstance().create(FoodService.class)
                .getfood()
              .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        getfood.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final HomeBean homeBean) {
                Log.i("====","model===="+homeBean.toString());
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
}
