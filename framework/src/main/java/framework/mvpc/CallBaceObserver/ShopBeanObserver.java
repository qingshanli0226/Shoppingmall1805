package framework.mvpc.CallBaceObserver;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import mode.BaseBean;
import mode.ShopcarBean;

public
class ShopBeanObserver implements Observer<BaseBean<List<ShopcarBean>>> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseBean<List<ShopcarBean>> listBaseBean) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
