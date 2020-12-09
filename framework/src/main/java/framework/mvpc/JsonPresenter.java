package framework.mvpc;



import android.util.Log;

import java.util.List;

import framework.Contact;
import framework.JsonDataCallBace;
import framework.mvpc.CallBaceObserver.ShopBeanObserver;
import framework.mvpc.CallBaceObserver.ClothesBeanObserver;
import framework.mvpc.CallBaceObserver.HomeBeanObserver;
import framework.mvpc.CallBaceObserver.JavaBeanObserver;
import framework.mvpc.CallBaceObserver.LoginBeanObserver;
import framework.mvpc.CallBaceObserver.RegisterBeanObserver;
import io.reactivex.Observer;
import mode.BaseBean;
import mode.ClothesBean;
import mode.HomeBean;
import mode.LableBean;
import mode.LoginBean;
import mode.RegisterBean;
import mode.ShopcarBean;

public class JsonPresenter extends Contact.centetUserpepostory{
    private Contact.CenterUserIview centerUserIviewc;
    public JsonPresenter(Contact.CenterUserIview centerUserIview) {
        super(centerUserIview);
        this.centerUserIviewc = centerUserIview;
    }

    public static Observer<LableBean> javabeanObserver;
    public static Observer<ClothesBean> clothesBeanObserver;
    public static Observer<HomeBean> homeBeanObserver;
    public static Observer<RegisterBean> registerBeanObserver;
    public static Observer<LoginBean> loginBeanObserver;
    public static Observer<BaseBean<List<ShopcarBean>>> beanObserver;
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
    public void shcarShop(int count, final JsonDataCallBace jsonDataCallBace) {
        Repostory.getshopcal(count);
        beanObserver = new ShopBeanObserver(){
            @Override
            public void onNext(BaseBean<List<ShopcarBean>> listBaseBean) {
                jsonDataCallBace.shopBean(listBaseBean);
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
