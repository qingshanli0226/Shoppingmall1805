package framework.mvpc;


import android.util.Log;

import framework.Contact;
import framework.JsonDataCallBace;
import framework.mvpc.CallBaceObserver.ClothesBeanObserver;
import framework.mvpc.CallBaceObserver.HomeBeanObserver;
import framework.mvpc.CallBaceObserver.JavaBeanObserver;
import framework.mvpc.CallBaceObserver.LoginBeanObserver;
import framework.mvpc.CallBaceObserver.RegisterBeanObserver;
import io.reactivex.Observer;
import mode.ClothesBean;
import mode.HomeBean;
import mode.LableBean;
import mode.LoginBean;
import mode.RegisterBean;

public class jsonPresenter extends Contact.centetUserpepostory{
    private Contact.CenterUserIview centerUserIviewc;
    public jsonPresenter(Contact.CenterUserIview centerUserIview) {
        super(centerUserIview);
        this.centerUserIviewc = centerUserIview;
    }
    public static Observer<LableBean> javabeanObserver;
    public static Observer<ClothesBean> clothesBeanObserver;
    public static Observer<HomeBean> homeBeanObserver;
    public static Observer<RegisterBean> registerBeanObserver;
    public static Observer<LoginBean> loginBeanObserver;
    @Override
    public void getshopcal(int count, final JsonDataCallBace userc) {
        Repostory.getshopcal(count);
        javabeanObserver = new JavaBeanObserver(){
            @Override
            public void onNext(LableBean javabean) {
                if (javabean==null){

                }else {
                    userc.javabean(javabean);

                }
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
                if (homeBean==null){

                }
            }

            @Override
            public void onError(Throwable e) {
            }
        };
    }

    @Override
    public void loginAndRegister(int count, String username, String password, final JsonDataCallBace userc) {
        Repostory.loginAndRegister(count,username,password);
        registerBeanObserver  = new RegisterBeanObserver(){
            @Override
            public void onNext(RegisterBean registerBean) {
                userc.registerBean(registerBean);
            }

            @Override
            public void onError(Throwable e) {
            }
        };
        loginBeanObserver = new LoginBeanObserver(){
            @Override
            public void onNext(LoginBean loginBean) {
                userc.loginBean(loginBean);
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
