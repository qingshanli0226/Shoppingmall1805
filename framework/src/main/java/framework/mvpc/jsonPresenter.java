package framework.mvpc;

import android.util.Log;

import framework.Contact;
import framework.User2;
import framework.mvpc.ExtractObserver.TypeHome;
import framework.mvpc.ExtractObserver.TypeLable;
import framework.mvpc.ExtractObserver.TypecloBean;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import mode.ClothesBean;
import mode.HomeBean;
import mode.javabean;

public class jsonPresenter extends Contact.centetUserpepostory{
    public jsonPresenter(Contact.CenterUserIview centerUserIview) {
        super(centerUserIview);
    }

    public static Observer<javabean> javabeanObserver;
    public static Observer<ClothesBean> clothesBeanObserver;
    public static Observer<HomeBean> homeBeanObserver;

    @Override
    public void getshopcal(int count,final User2 userc) {
        Repostory.getshopcal(count);
        javabeanObserver = new TypeLable(){
            @Override
            public void onNext(javabean javabean) {
                Log.i("====","获取到的javabean数据"+javabean.toString());
                userc.successLable(javabean);

            }

            @Override
            public void onError(Throwable e) {
            }
        };

        clothesBeanObserver = new TypecloBean(){
            @Override
            public void onNext(ClothesBean clothesBean) {
                userc.successClassifs(clothesBean);

            }

            @Override
            public void onError(Throwable e) {
            }
        };
        homeBeanObserver = new TypeHome(){
            @Override
            public void onNext(HomeBean homeBean) {
                Log.i("====","这是home的数据"+homeBean.toString());
                userc.successHome(homeBean);
            }

            @Override
            public void onError(Throwable e) {
            }
        };
    }


    @Override
    protected void createRepostory() {
        Log.i("====","这是Presenter层");
        Repostory = new JsonRepository();
    }
}
