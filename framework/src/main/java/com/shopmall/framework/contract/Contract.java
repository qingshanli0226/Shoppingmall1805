package com.shopmall.framework.contract;

import com.shopmall.framework.callback.Home;
import com.shopmall.framework.callback.ILogin;
import com.shopmall.framework.callback.IRegister;
import com.shopmall.framework.callback.IShopCar;
import com.shopmall.framework.callback.ITest;
import com.shopmall.framework.callback.Sort;
import com.shopmall.framework.callback.Tag;
import com.shopmall.framework.mvp.IModel;
import com.shopmall.framework.mvp.IView;
import com.shopmall.framework.mvp.Presenter;
import com.shopmall.framework.mvp.Repository;
import com.shopmall.framework.view.LoadingPage;
import com.shopmall.net.bean.ShopcarBean;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 服务，管理层
 */
public interface Contract {
    /**
     * V 层
     */
    interface LoginConstartView extends IView {
        void Success(Object... objects);
        void Error(String s);
    }

    interface RegisterConstartView extends IView {
        void Success(Object... objects);
        void Error(String s);
    }

    /**
     * home主页 V 层
     */
    interface HomeConstartView extends IView {
         void Success(Object... objects);
         void Error(String s);
    }

    /**
     * 分类 V 层
     */
    interface SortConstartView extends IView {
        void Success(Object... objects);
        void Error(String s);
    }

    /**
     * 标签 V 层
     */
    interface TagConstartView extends IView {
        void Success(Object... objects);
        void Error(String s);
    }

    interface ShopCarContractView extends IView {
        void Success(Object... objects);
        void Error(String s);
    }

    /**
     * M 层
     */
    interface LoginConstartModel extends IModel {
        void login(String url,HashMap<String, String> map, LoadingPage loadingPage, ILogin iLogin);
    }

    interface RegisterConstartModel extends IModel {
        void register(String url,HashMap<String, String> map, LoadingPage loadingPage, IRegister iRegister);
    }

    /**
     * home主页M 层
     */
    interface HomeConstartModel extends IModel{
           void homec(String url, LoadingPage loadingPage, Home home);
    }

    /**
     * 分类 M 层
     */
    interface SortConstartModel extends IModel{
          void Sort(String url,LoadingPage loadingPage,  Sort sort);
    }

    /**
     * 标签 M 层
     */
    interface TagConstartModel extends IModel{
        void Tag(String url,LoadingPage loadingPage, Tag tag);
    }

    interface ShopCarContractModel extends IModel{
        //添加购物车
        void addShopCarData(String url, JSONObject jsonObject, IShopCar iShopCar);
        //检查库存情况
        void checkOneProductInventory(String url, HashMap<String,String> map, IShopCar iShopCar);
        //更该产品选择情况
        void updateProductSelected(String url, ShopcarBean.ResultBean shopCar, IShopCar iShopCar);
        // 全选服务端购物车产品或者全不选
        void selectAllProduct(String url,boolean allSelect,IShopCar iShopCar);

        void updateProductNum(String url,String num,ShopcarBean.ResultBean shopCar,IShopCar iShopCar);

        void removeManyProduct(String url,IShopCar iShopCar);

        void checkInventory(String url,IShopCar iShopCar);

        void updatePhone(String url,String phone,IShopCar iShopCar);

        void updateAddress(String url,String address,IShopCar iShopCar);
    }

    /**
     * 仓库层
     */
    abstract class LoginConstartRepository extends Repository<LoginConstartModel> {
        public abstract void login(String url,HashMap<String, String> map, LoadingPage loadingPage, ILogin iLogin);
    }

    abstract class RegisterConstartRepository extends Repository<RegisterConstartModel> {
        public abstract void register(String url,HashMap<String, String> map, LoadingPage loadingPage, IRegister iRegister);
    }

    /**
     * home主页仓库 层
     */
    abstract class HomeConstartRepository extends Repository<HomeConstartModel>{
        public abstract void homec(String url,LoadingPage loadingPage,  Home home);
    }

    /**
     * 分类 仓库 层
     */
    abstract class SortConstartRepository extends Repository<SortConstartModel>{
       public abstract void Sort(String url,LoadingPage loadingPage, Sort sort);
    }

    /**
     * 标签 仓库 层
     */
    abstract class TagConstartRepository extends Repository<TagConstartModel>{
        public abstract void Tag(String url,LoadingPage loadingPage, Tag tag);
    }

    abstract class ShopCarContractRepository extends Repository<ShopCarContractModel>{
        //添加购物车
        public abstract void addShopCarData(String url, JSONObject jsonObject, IShopCar iShopCar);
        //检查库存情况
        public abstract void checkOneProductInventory(String url, HashMap<String,String> map, IShopCar iShopCar);
        //更该产品选择情况
        public abstract void updateProductSelected(String url, ShopcarBean.ResultBean shopCar, IShopCar iShopCar);
        // 全选服务端购物车产品或者全不选
        public abstract void selectAllProduct(String url,boolean allSelect,IShopCar iShopCar);

        public abstract void updateProductNum(String url,String num,ShopcarBean.ResultBean shopCar,IShopCar iShopCar);

        public abstract void removeManyProduct(String url,IShopCar iShopCar);

        public abstract void checkInventory(String url,IShopCar iShopCar);

        public abstract void updatePhone(String url,String phone,IShopCar iShopCar);

        public abstract void updateAddress(String url,String address,IShopCar iShopCar);
    }

    /**
     * P 层
     */
    abstract class LoginConstartPresenter extends Presenter<LoginConstartView,LoginConstartRepository> {
        public LoginConstartPresenter(LoginConstartView loginConstartView) {
            super(loginConstartView);
        }

        public abstract void login(String loginUrl, HashMap<String, String> map, LoadingPage loadingPage);
    }

    abstract class RegisterConstartPresenter extends Presenter<RegisterConstartView,RegisterConstartRepository> {

        public RegisterConstartPresenter(RegisterConstartView registerConstartView) {
            super(registerConstartView);
        }

        public abstract void register(String loginUrl, HashMap<String, String> map, LoadingPage loadingPage);
    }

    /**
     * home主页P 层
     */
    abstract class HomeConstartPresenter extends Presenter<HomeConstartView,HomeConstartRepository> {
        public HomeConstartPresenter(HomeConstartView homeConstartView) {
            super(homeConstartView);
        }
        public abstract void homec(String url, LoadingPage loadingPage);
    }

    /**
     * 分类 P 层
     */
    abstract class SortConstartPresenter extends Presenter<SortConstartView,SortConstartRepository> {
        public SortConstartPresenter(SortConstartView sortConstartView) {
            super(sortConstartView);
        }
        public abstract void Sort(String url,LoadingPage loadingPage);
    }

    /**
     * 标签 P 层
     */
    abstract class TagConstartPresenter extends Presenter<TagConstartView,TagConstartRepository> {
        public TagConstartPresenter(TagConstartView tagConstartView) {
            super(tagConstartView);
        }
        public abstract void tag(String url,LoadingPage loadingPage);
    }

    abstract class ShopCarContractPresenter extends Presenter<ShopCarContractView,ShopCarContractRepository>{
        public ShopCarContractPresenter(ShopCarContractView shopCarContractView) {
            super(shopCarContractView);
        }
        //添加购物车
        public abstract void addShopCarData(String url, JSONObject jsonObject);
        //检查库存情况
        public abstract void checkOneProductInventory(String url, HashMap<String,String> map, ITest iTest);
        //更该产品选择情况
        public abstract void updateProductSelected(String url, ShopcarBean.ResultBean shopCar, int position);
        // 全选服务端购物车产品或者全不选
        public abstract void selectAllProduct(String url,boolean allSelect);

        public abstract void updateProductNum(String url,String num,ShopcarBean.ResultBean shopCar,int position);

        public abstract void removeManyProduct(String url);

        public abstract void checkInventory(String url, ITest iTest);

        public abstract void updatePhone(String url,String phone);

        public abstract void updateAddress(String url,String address);
    }
}
