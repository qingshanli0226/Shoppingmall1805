package framework.mvpc;

import android.util.Log;

import java.util.List;

import framework.Contact;
import framework.User2;
import framework.User3;
import framework.Userc;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import mode.ClothesBean;
import mode.HomeBean;
import mode.javabean;

public class jsonPresenter extends Contact.centetUserpepostory{
    public jsonPresenter(Contact.CenterUserIview centerUserIview) {
        super(centerUserIview);
    }
    public static Observer<HomeBean> observer;
    public static Observer<ClothesBean> observer2;
    public static Observer<javabean> observer3;
    @Override
    public void getHomeurl(final Userc userc) {
        Repostory.getHomeur();

        observer = new Observer<HomeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeBean homeBean) {
                Log.i("====","首页获取到的数据是"+homeBean.getResult().toString());
                userc.Susses(homeBean);
            }

            @Override
            public void onError(Throwable e) {
                    userc.Error(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    public void getshopcal(int count,final User2 userc) {
        Repostory.getshopcal(count);
        observer2 = new Observer<ClothesBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(ClothesBean homeBean) {
                userc.Susses(homeBean);
            }

            @Override
            public void onError(Throwable e) {
                userc.Error(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    public void getBaiocal(final User3 user3) {
        Repostory.getBaiocal();
        observer3 = new Observer<javabean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(javabean homeBean) {
                user3.Susses(homeBean);
            }

            @Override
            public void onError(Throwable e) {
                user3.Error(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    protected void createRepostory() {
        Log.i("====","这是Presenter层");
        Repostory = new JsonRepository();
    }
}
