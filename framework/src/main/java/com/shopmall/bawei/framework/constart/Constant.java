package com.shopmall.bawei.framework.constart;

import com.shopmall.bawei.framework.callback.Home;
import com.shopmall.bawei.framework.callback.ILogin;
import com.shopmall.bawei.framework.callback.IRegist;
import com.shopmall.bawei.framework.callback.IShopcar;
import com.shopmall.bawei.framework.callback.Itest;
import com.shopmall.bawei.framework.callback.Sort;
import com.shopmall.bawei.framework.callback.Tag;
import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.framework.mvp.IModel;
import com.shopmall.bawei.framework.mvp.Iview;
import com.shopmall.bawei.framework.mvp.Presenter;
import com.shopmall.bawei.framework.mvp.Repository;
import com.shopmall.bean.OrderBean;
import com.shopmall.bean.ShopcarBean;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * 服务，管理层
 */
public interface Constant {

    /**
     * Login V 层
     */
    interface LoginConstartView extends Iview {
        void Success(Object...objects);
        void Error(String s);
    }

    /**
     * regist V 层
     */
    interface RegistConstartView extends Iview {
        void Success(Object...objects);
        void Error(String s);
    }
    /**
     * home主页 V 层
     */
    interface HomeConstartView extends Iview {
         void Success(Object...objects);
         void Error(String s);
    }

    /**
     * 分类主页 V 层
     */
    interface SortConstartView extends Iview {
        void Success(Object...objects);
        void Error(String s);
    }


    /**
     * 标签主页 V 层
     */
    interface TagConstartView extends Iview {
        void Success(Object...objects);
        void Error(String s);
    }

    /**
     * 购物车主页 V 层
     */
    interface ShopcarConstartView extends Iview {
        void Success(Object...objects);
        void Error(String s);
    }





    /**
     * Login M 层
     */
    interface LoginConstartModel extends IModel{
            void login(String url, HashMap<String,String> map,LogingPage logingPage, ILogin iLogin);
    }



    /**
     * regist M 层
     */
    interface RegistConstartModel extends IModel{
          void regist(String url, HashMap<String,String> map,LogingPage logingPage, IRegist iRegist);
    }

    /**
     * home主页M 层
     */
    interface HomeConstartModel extends IModel{
           void homec(String url, LogingPage logingPage, Home home);
    }

    /**
     * 分类主页M 层
     */
    interface SortConstartModel extends IModel{
          void Sort(String url,LogingPage logingPage, Sort sort);
    }


    /**
     * 标签主页M 层
     */
    interface TagConstartModel extends IModel{
        void Tagdata(String url,LogingPage logingPage, Tag sort);
    }

    /**
     * 购物车主页M 层
     */
    interface ShopcarConstartModel extends IModel{
        //添加购物车
        void addshopcarData(String url, JSONObject jsonObject, IShopcar iShopcar);
        //检查库存情况
       void checkOneProductInventory(String url, HashMap<String,String> map, IShopcar iShopcar);
        //更该产品选择情况
        void updateProductSelected(String url,ShopcarBean.ResultBean shopcar,IShopcar iShopcar);
        // 全选服务端购物车产品或者全不选
         void selectAllProduct(String url,boolean allselect,IShopcar iShopcar);
        // 从服务端购物车删除多个产品
        void removeManyProduct(String url,IShopcar iShopcar);
        // 更新服务端购物车产品的数量的接口
        public abstract void updateProductNum(String url,int newnum,ShopcarBean.ResultBean shopcar,IShopcar iShopcar);
        // 检查服务端多个产品是否库存充足
        void checkInventory(String url,IShopcar iShopcar);
        // 更新用户绑定的电话
        void updatePhone(String url,String phone,IShopcar iShopcar);
        // 更新地址的接口
        void updateAddress(String url,String address,IShopcar iShopcar);
        // 向服务端下订单接口
        void getOrderInfo(String url, List<ShopcarBean.ResultBean> shop,IShopcar iShopcar);
        // 从服务端购物车删除多个产品
        void orderremoveManyProduct(String url,IShopcar iShopcar);
        // 请求服务端，是否支付成功
        void confirmServerPayResult(String url,boolean isShop, OrderBean orderBean,IShopcar iShopcar);
        // 查找待支付的订单
        void findForPay(String url,IShopcar iShopcar);
    }


    /**
     * Login 仓库层
     */
    abstract class LoginConstartRepository extends Repository<LoginConstartModel>{
        public abstract void login(String url, HashMap<String,String> map,LogingPage logingPage, ILogin iLogin);
    }


    /**
     *regist 仓库层
     */
    abstract class RegistConstartRepository extends Repository<RegistConstartModel>{
       public abstract void regist(String url, HashMap<String,String> map,LogingPage logingPage, IRegist iRegist);
    }

    /**
     * home主页仓库 层
     */
    abstract class HomeConstartRepository extends Repository<HomeConstartModel>{
        public abstract void homec(String url,LogingPage logingPage, Home home);
    }


    /**
     * 分类仓库 层
     */
    abstract class SortConstartRepository extends Repository<SortConstartModel>{
       public abstract void Sort(String url,LogingPage logingPage, Sort sort);
    }


    /**
     * 标签仓库 层
     */
    abstract class TagConstartRepository extends Repository<TagConstartModel>{
        public abstract void Tag(String url,LogingPage logingPage, Tag sort);
    }



    /**
     * 购物车仓库 层
     */
    abstract class ShopcarConstartRepository extends Repository<ShopcarConstartModel>{
        //添加购物车
        public abstract void addshopcarData(String url, JSONObject jsonObject, IShopcar iShopcar);
        //检查库存情况
        public abstract void checkOneProductInventory(String url, HashMap<String,String> map, IShopcar iShopcar);
        //更该产品选择情况
        public abstract void updateProductSelected(String url,ShopcarBean.ResultBean shopcar,IShopcar iShopcar);
        // 全选服务端购物车产品或者全不选
        public abstract void selectAllProduct(String url,boolean allselect,IShopcar iShopcar);
        // 从服务端购物车删除多个产品
        public abstract void removeManyProduct(String url,IShopcar iShopcar);
        // 更新服务端购物车产品的数量的接口
        public abstract void updateProductNum(String url,int newnum,ShopcarBean.ResultBean shopcar,IShopcar iShopcar);
        // 检查服务端多个产品是否库存充足
        public abstract void checkInventory(String url,IShopcar iShopcar);
        // 更新用户绑定的电话
        public abstract void updatePhone(String url,String phone,IShopcar iShopcar);
        // 更新地址的接口
        public abstract void updateAddress(String url,String address,IShopcar iShopcar);
        // 向服务端下订单接口
        public abstract void getOrderInfo(String url, List<ShopcarBean.ResultBean> shop,IShopcar iShopcar);
        // 从服务端购物车删除多个产品
        public abstract void orderremoveManyProduct(String url,IShopcar iShopcar);
        // 请求服务端，是否支付成功
       public abstract void confirmServerPayResult(String url,boolean isShop, OrderBean orderBean,IShopcar iShopcar);
        // 查找待支付的订单
        public abstract void findForPay(String url, IShopcar iShopcar);
    }





    /**
     * Login P 层
     */
    abstract class LoginConstartPresenter extends Presenter<LoginConstartView,LoginConstartRepository>{

        public LoginConstartPresenter(LoginConstartView loginConstartView) {
            super(loginConstartView);
        }

       public abstract void login(String url, HashMap<String,String> map,LogingPage logingPage);
    }



    /**
     * regist P 层
     */
    abstract class RegistConstartPresenter extends Presenter<RegistConstartView,RegistConstartRepository>{


        public RegistConstartPresenter(RegistConstartView registConstartView) {
            super(registConstartView);
        }
        public abstract void regist(String url, HashMap<String,String> map,LogingPage logingPage);
    }

    /**
     * home主页P 层
     */
    abstract class HomeConstartPresenter extends Presenter<HomeConstartView,HomeConstartRepository>{


        public HomeConstartPresenter(HomeConstartView homeConstartView) {
            super(homeConstartView);
        }

        public abstract void homec(String url,LogingPage logingPage);
    }


    /**
     * 分类主页P 层
     */
    abstract class SortConstartPresenter extends Presenter<SortConstartView,SortConstartRepository>{


        public SortConstartPresenter(SortConstartView sortConstartView) {
            super(sortConstartView);
        }
        public abstract void Sort(String url,LogingPage logingPage);
    }


    /**
     * 标签P 层
     */
    abstract class TagConstartPresenter extends Presenter<TagConstartView,TagConstartRepository> {


        public TagConstartPresenter(TagConstartView tagConstartView) {
            super(tagConstartView);
        }
        public abstract void Tag(String url,LogingPage logingPage);

    }


    /**
     * 购物车P 层
     */
    abstract class ShopcarConstartPresenter extends Presenter<ShopcarConstartView,ShopcarConstartRepository> {


        public ShopcarConstartPresenter(ShopcarConstartView shopcarConstartView) {
            super(shopcarConstartView);
        }
        //添加购物车
        public abstract void addshopcarData(String url, JSONObject jsonObject);
        //检查库存情况
        public abstract void checkOneProductInventory(String url, HashMap<String,String> map, final Itest itest);
        //更该产品选择情况
        public abstract void updateProductSelected(String url,ShopcarBean.ResultBean shopcar, int positon);
        // 全选服务端购物车产品或者全不选
        public abstract void selectAllProduct(String url,boolean allselect);
        // 从服务端购物车删除多个产品
        public abstract void removeManyProduct(String url);
        // 更新服务端购物车产品的数量的接口
        public abstract void updateProductNum(String url,int newnum,ShopcarBean.ResultBean shopcar,int positon);
        // 检查服务端多个产品是否库存充足
        public abstract void checkInventory(String url,Itest itest);
        // 更新用户绑定的电话
        public abstract void updatePhone(String url,String phone);
        // 更新地址的接口
        public abstract void updateAddress(String url,String address);
        // 向服务端下订单接口
        public abstract void getOrderInfo(String url, List<ShopcarBean.ResultBean> shop);
        // 从服务端购物车删除多个产品
        public abstract void orderremoveManyProduct(String url);
        // 请求服务端，是否支付成功
        public abstract void confirmServerPayResult(String url,boolean isShop, OrderBean orderBean);
        // 查找待支付的订单
        public abstract void findForPay(String url);
    }


}
