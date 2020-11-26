package com.shopmall.bawei.shopmall1805.mvp.prsenter;

import android.util.Log;

import com.shopmall.bawei.shopmall1805.mvp.contract.HContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import baseurl.HomeBean;
import mvp.presenter.BasePresenter;

public class HPresenter extends BasePresenter<HContract.getmodel,HContract.geteview> {
    public HPresenter(HContract.getmodel imoudel, HContract.geteview iview) {
        super(imoudel, iview);
    }
    public void inithomedata(){
        Log.i("TAG", "inithomedata: ");
      imoudel.sethome(new Observer<HomeBean>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(HomeBean homeBean) {
              Log.i("TAG", "onNext: "+homeBean);
        iview.getdata(homeBean);

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
