package framework.mvpc;



import android.util.Log;

import framework.Contact;
import framework.JsonDataCallBace;
import framework.mvpc.CallBaceObserver.BeanObserver;
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
import mode.ShopcarBean;

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
    public static Observer<ShopcarBean> beanObserver;
    @Override
    public void getshopcal(int count, final JsonDataCallBace jsonDataCallBace) {
        Repostory.getshopcal(count);
        javabeanObserver = new JavaBeanObserver(){
            @Override
            public void onNext(LableBean javabean) {
                if (javabean==null){

                }else {
                    jsonDataCallBace.javabean(javabean);

                }
            }
            @Override
            public void onError(Throwable e) {

            }
        };
        clothesBeanObserver = new ClothesBeanObserver(){
            @Override
            public void onNext(ClothesBean clothesBean) {
                jsonDataCallBace.clothesBean(clothesBean);
            }

            @Override
            public void onError(Throwable e) {
            }
        };
        homeBeanObserver = new HomeBeanObserver(){
            @Override
            public void onNext(HomeBean homeBean) {
                jsonDataCallBace.homeBean(homeBean);
                if (homeBean==null){

                }
            }

            @Override
            public void onError(Throwable e) {
            }
        };

    }

    @Override
    public void loginAndRegister(int count, String username, String password, final JsonDataCallBace jsonDataCallBace) {
        Repostory.loginAndRegister(count, username, password);
        registerBeanObserver = new RegisterBeanObserver() {
            @Override
            public void onNext(RegisterBean registerBean) {
                jsonDataCallBace.registerBean(registerBean);
            }

            @Override
            public void onError(Throwable e) {
            }
        };

        loginBeanObserver = new LoginBeanObserver() {
            @Override
            public void onNext(LoginBean loginBean) {
                jsonDataCallBace.loginBean(loginBean);
            }

            @Override
            public void onError(Throwable e) {
            }


        };
    }


    @Override
    public void shcarShop(int count, JsonDataCallBace jsonDataCallBace) {
        Repostory.getshopcal(count);
        beanObserver = new BeanObserver<ShopcarBean>() {
            @Override
            public void onNext(ShopcarBean shopcarBean) {
                super.onNext(shopcarBean);
            }

            @Override
            public void onError(Throwable e) {

                super.onError(e);
            }
        };
    }


    @Override
    protected void createRepostory() {
        Log.i("====","这是Presenter层");
        Repostory = new JsonRepository();
    }
}
