package framework.mvpc;

import android.util.Log;

import framework.Contact;
import framework.JsonDataCallBace;
import framework.mvpc.CallBaceObserver.ClothesBeanObserver;
import framework.mvpc.CallBaceObserver.HomeBeanObserver;
import framework.mvpc.CallBaceObserver.JavaBeanObserver;
import io.reactivex.Observer;
import mode.ClothesBean;
import mode.HomeBean;
import mode.LableBean;


public class jsonPresenter extends Contact.centetUserpepostory{
    public jsonPresenter(Contact.CenterUserIview centerUserIview) {
        super(centerUserIview);
    }
    public static Observer<LableBean> javabeanObserver;
    public static Observer<ClothesBean> clothesBeanObserver;
    public static Observer<HomeBean> homeBeanObserver;
    @Override
    public void getshopcal(int count, final JsonDataCallBace userc) {
        Repostory.getshopcal(count);
        javabeanObserver = new JavaBeanObserver(){
            @Override
            public void onNext(LableBean javabean) {
                userc.javabean(javabean);
            }

            @Override
            public void onError(Throwable e) {
            }
        };
        clothesBeanObserver = new ClothesBeanObserver(){
            @Override
            public void onNext(ClothesBean clothesBean) {
                userc.clothesBean(clothesBean);
            }

            @Override
            public void onError(Throwable e) {
            }
        };
        homeBeanObserver = new HomeBeanObserver(){
            @Override
            public void onNext(HomeBean homeBean) {
                userc.homeBean(homeBean);
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
