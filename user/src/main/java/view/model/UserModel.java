package view.model;

import net.FoodService;
import net.RxjavaRetortUlis;

import framework.Contact;
import framework.IUserDataReturn;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mode.BaseBean;

public
class UserModel implements Contact.ICenterUserIModel {

    @Override
    public void onLogOut(final IUserDataReturn iUserDataReturn) {
        RxjavaRetortUlis.getInstance().create(FoodService.class)
                .getLogout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<String> stringBaseBean) {
                        if (stringBaseBean!=null){
                            iUserDataReturn.onSuccess(stringBaseBean.getResult());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iUserDataReturn.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
