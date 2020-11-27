package com.bawei.deom.countroller;

import android.util.Log;
import android.widget.Toast;

import com.bawei.deom.BaseUser;
import com.bawei.deom.ClassInterface;
import com.bawei.deom.IPrine;

import java.util.List;
import java.util.concurrent.TimeUnit;

import bean.BaseBean;
import bean.ClothesBean;
import bean.typebean.BugBean;
import bean.typebean.DigitBean;
import bean.typebean.DressupBean;
import bean.typebean.GameBean;
import bean.typebean.HomproductsBean;
import bean.typebean.JackBean;
import bean.typebean.OverCoat;
import bean.typebean.Pants;
import bean.typebean.SkirtBean;
import bean.typebean.StationeryBean;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SkirtImpl extends SkirtCommuntroller.UsShow {
    @Override
    public void UserShow(String url) {
        ClassInterface.getUserInterface().getbug(url)
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<BugBean>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(BugBean bugBean) {
                                 pView.UserView(bugBean.getResult());
                                 pView.showSuccessView2();
                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onComplete() {

                   }
               });
        ;
    }






}
