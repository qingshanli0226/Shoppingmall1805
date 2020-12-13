package framework;


import java.util.List;

import framework.Mvp.Imodel;
import framework.Mvp.Iview;
import framework.Mvp.Presenter;
import framework.Mvp.Repository;
import mode.InventoryBean;
import mode.OrderInfoBean;

public
interface Contact {
    interface CenterUserIview extends Iview {

    }

    interface centerUserImodel extends Imodel {
         void getshopcal(int count);

         void loginAndRegister(int count,String username,String password);
         void shcarShop(int count);
    }

    abstract class centerUserRepostory extends Repository<centerUserImodel> {
         public abstract void getshopcal(int count);

         public abstract void loginAndRegister(int count,String username,String password);
         public abstract  void shcarShop(int count);
    }

    abstract class centetUserpepostory extends Presenter<centerUserRepostory,CenterUserIview> {

        public centetUserpepostory(CenterUserIview centerUserIview) {
            super(centerUserIview);
        }

        public abstract   void getshopcal(int count, JsonDataCallBace jsonDataCallBace);
        public abstract  void loginAndRegister(int count,String username,String password,JsonDataCallBace jsonDataCallBace);
        public  abstract  void shcarShop(int count,JsonDataCallBace jsonDataCallBace);
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
}
