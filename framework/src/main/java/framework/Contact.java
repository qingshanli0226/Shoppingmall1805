package framework;


import java.util.List;

import framework.Mvp.Imodel;
import framework.Mvp.Iview;
import framework.Mvp.Presenter;
import framework.Mvp.Repository;
import mode.InventoryBean;
import mode.OrderInfoBean;
import mode.ShopcarBean;
import retrofit2.http.Url;
import view.loadinPage.UnpaidBean;

public
interface Contact {
    interface CenterUserIview extends Iview {

    }

    interface centerUserImodel extends Imodel {
         void getshopcal(int count);

         void loginAndRegister(int count,String username,String password);
         void shcarShop(int count);
         void addShcarshop(ShopcarBean shopcarBean);
    }

    abstract class centerUserRepostory extends Repository<centerUserImodel> {
         public abstract void getshopcal(int count);

         public abstract void loginAndRegister(int count,String username,String password);
         public abstract  void shcarShop(int count);
         public abstract void addShcarshop(ShopcarBean shopcarBean);
    }

    abstract class centetUserpepostory extends Presenter<centerUserRepostory,CenterUserIview> {

        public centetUserpepostory(CenterUserIview centerUserIview) {
            super(centerUserIview);
        }

        public abstract   void getshopcal(int count, JsonDataCallBace jsonDataCallBace);
        public abstract  void loginAndRegister(int count,String username,String password,JsonDataCallBace jsonDataCallBace);
        public  abstract  void shcarShop(int count,JsonDataCallBace jsonDataCallBace);
        public  abstract void addShcarshop(ShopcarBean shopcarBean);
    }

    //购物车v层
    interface ICenterShoppingIview extends Iview{
        void onShopIview();//获取购物车数据返回
    }
    //购物车m层
    interface ICenterShoppingIModel extends Imodel{
        void GainShopCar();//获取购物车数据
    }
    //购物车仓库层
    abstract  class CenterShoppingReposotry extends Repository<ICenterShoppingIModel>{
        public abstract  void GainShopCar();//仓库层获取购物车数据
    }
    //购物车P层
    abstract class CenterShoppingPresenter extends Presenter<CenterShoppingReposotry,ICenterShoppingIview>{

        public CenterShoppingPresenter(ICenterShoppingIview iCenterShoppingIview) {
            super(iCenterShoppingIview);
        }
        public abstract  void GainShopCar();//仓库层获取购物车数据//p层获取购物车数据
    }


    //orderv层
    interface ICenterOrderIview extends Iview{
        void onUnpaidSuccess(UnpaidBean unpaidBean);
        void onError(String Error);
    }
    //orderm层
    interface ICenterOrderIModel extends Imodel{
        void goBindingPhone(String Url);
        void goBindingPoint(String Url);
        void goUnpaidOrder(IOrderData iOrderData);
    }
    //order仓库层
    abstract  class CenterOrderReposotry extends Repository<ICenterOrderIModel>{
        public abstract  void goBindingPhone(String Url);
        public abstract  void goBindingPoint(String Url);
        public abstract  void goUnpaidOrder(IOrderData iOrderData);
    }
    //orderP层
    abstract class CenterOrderPresenter extends Presenter<CenterOrderReposotry,ICenterOrderIview>{

        public CenterOrderPresenter(ICenterOrderIview iCenterOrderIview) {
            super(iCenterOrderIview);
        }

        public abstract  void goBindingPhone(String Url);
        public abstract  void goBindingPoint(String Url);
        public abstract  void goUnpaidOrder();

    }

    //orderv层
    interface ICenterUserIview extends Iview{
        void onUnpaidSuccess();
        void onError(String Error);
    }
    //orderm层
    interface ICenterUserIModel extends Imodel{
        void onLogOut(IUserDataReturn iUserDataReturn);
    }
    //order仓库层
    abstract  class CenterUserReposotry extends Repository<ICenterUserIModel>{
        public  abstract  void onLogOut(IUserDataReturn iUserDataReturn);
    }
    //orderP层
    abstract class CenterUserPresenter extends Presenter<CenterUserReposotry,ICenterUserIview>{

        public CenterUserPresenter(ICenterUserIview iCenterUserIview) {
            super(iCenterUserIview);
        }
        public  abstract  void onLogOut(IUserDataReturn iUserDataReturn);

    }



}
