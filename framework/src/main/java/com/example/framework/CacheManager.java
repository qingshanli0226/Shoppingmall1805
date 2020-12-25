package com.example.framework;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.net.FindSendBean;
import com.example.net.Retrofitcreators;
import com.example.net.bean.BaseBean;
import com.example.net.bean.FindPayBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.ShopcarBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CacheManager {
    private static CacheManager instance;
    /** 缓存第一步 --> 定义单例，并且将单例中使用列表缓存数据*/
    private List<ShopcarBean> shopcarBeans = new ArrayList<>();
    /** 定义一个删除的集合，将所有删除数据放在这个集合当中*/
    private List<ShopcarBean> deleteShopBeans = new ArrayList<>();
    /** 有多个页面要进行刷新，所以维护一个listerter监听列表*/
    private List<IShopcarDataCharListerter> listerters = new ArrayList<>();
    /** 定义一个待支付的集合*/
    private List<FindPayBean> findPayBeanList = new ArrayList<>();
    /** 定义一个待发货的集合*/
    private List<FindSendBean> findSendBeanList = new ArrayList<>();
    /** 定义一个支付成功的集合，将支付成功的加入到集合当中*/
    List<ShopcarBean> selectedPaySucessList = new ArrayList<>();
    /** 定义一个支付失败的集合，将支付成功的加入到集合当中*/
    List<ShopcarBean> selectedPayErroyList = new ArrayList<>();
    /** 有多个页面要进行刷新，所以维护一个listerter监听列表*/
    private List<IShopcarPayCharListerter> paylisterters = new ArrayList<>();
    private Context context;
    public CacheManager(){
    }
    public static CacheManager getInstance(){
        if (instance == null){
            instance = new CacheManager();
        }
        return instance;
    }
    public void init(Context context){
        this.context = context;
        initService();

    }

    /**
     * 初始化sercice判断登录状态
     */
    private void initService() {
        Intent intent = new Intent();
        intent.setClass(context, MyService.class);
        context.startService(intent);
        /**
         * 缓存第二步 --> 通过回调监听登录事件，一旦监听到登录成功，就立马从服务端获得数据，并且将数据赛道集合当中
         */
        ShopUsermange.getInstance().registerUserLoginChangeListenter(new ShopUsermange.IUserLoginChangeLiestener() {
            @Override
            public void onUserLogin(LoginBean loginBean) {
                getshopCardateserver();//TODO 获取购物车数据
                getfindpay();//TODO 查找待支付的订单
                getfindSend();//TODO 查找待付款的订单
            }

            @Override
            public void onUserlogout() {
            }
        });
    }

    /**
     * 从服务端获取购物车数据
     */
    public void getshopCardateserver(){
        Retrofitcreators.getiNetPresetenterWork().getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<List<ShopcarBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseBean<List<ShopcarBean>> listBaseBean) {
                        if (listBaseBean.getCode().equals("200")){
                            shopcarBeans.addAll(listBaseBean.getResult());
                            notifyShopCarDateChanged();
                        }else {
                            Toast.makeText(context, "请求购物车数据失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 从服务端获取待支付数据
     */
    public void getfindpay(){
        Retrofitcreators.getiNetPresetenterWork().getFindPay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<List<FindPayBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<List<FindPayBean>> listBaseBean) {
                        if (listBaseBean.getCode().equals("200")){
                            findPayBeanList.addAll(listBaseBean.getResult());
                            notifyShopCarpayChanged();
                        }else {
                            Toast.makeText(context, "请求待支付订单失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 让别的类可以拿到待支付集合
     * @return
     */
    public List<FindPayBean> getfindpayList(){
        return findPayBeanList;
    }
    /**
     *  将待支付的重新支付成功的数据从缓存中删除
     * @param findPayBean
     */
    public void removePay(FindPayBean findPayBean){
        findPayBeanList.remove(findPayBean);
        for (IShopcarPayCharListerter paylisterter : paylisterters) {
            paylisterter.onPayList(findPayBeanList);
        }
    }
    /**提供修改缓存数据的接口*/
    public void addPayList(FindPayBean findPayBean){
        findPayBeanList.add(findPayBean);
        //通过接口回调刷新Ui
        for (IShopcarPayCharListerter paylisterter : paylisterters) {
            paylisterter.onPayList(findPayBeanList);
        }
    }
    /**从服务端获取待发货数据*/
    public void getfindSend(){
        Retrofitcreators.getiNetPresetenterWork().getFindSend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<List<FindSendBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<List<FindSendBean>> listBaseBean) {
                        if (listBaseBean.getCode().equals("200")){
                            findSendBeanList.addAll(listBaseBean.getResult());
                        }else {
                            Toast.makeText(context, "查找待发货失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    /**让别的类可以拿到待发货集合*/
    public List<FindSendBean> getfindsendList(){
        return findSendBeanList;
    }
    /**提供修改待发货缓存数据的接口*/
    public void addSendList(FindSendBean findSendBean){
        findSendBeanList.add(findSendBean);
        //通过接口回调刷新Ui
        for (IShopcarPayCharListerter paylisterter : paylisterters) {
            paylisterter.onSendList(findSendBeanList);
        }
    }
    /**缓存第三部，提供修改缓存数据的接口*/
    public void add(ShopcarBean shopcarBean){
        shopcarBeans.add(shopcarBean);//提供接口，添加一个数据到服务器当中
        for (IShopcarDataCharListerter listerter : listerters) {
            listerter.ondataChanged(shopcarBeans);
        }
    }
    /**遍历这个listenter集合，让他逐步去通知刷新购物车数据变化*/
    public void notifyShopCarDateChanged(){
        for (IShopcarDataCharListerter listerter : listerters) {
            listerter.ondataChanged(shopcarBeans);
        }
    }
    /**遍历这个paylistenter集合，让他逐步去通知刷新购物车数据变化*/
    public void notifyShopCarpayChanged(){
        for (IShopcarPayCharListerter listerter : paylisterters) {
            listerter.onPayList(findPayBeanList);
        }
    }
    /**缓存第三部 --> 提供方法，可以让别的类获取到缓存*/
    public List<ShopcarBean> getShopcarList(){
        return shopcarBeans;
    }
    /** 当页面想监听数据的时候，就注册一个listenter*/
    public void setshopcarChangedListenter(IShopcarDataCharListerter listerter){
        if (!listerters.contains(listerter)){
            listerters.add(listerter);
        }
    }
    /**当页面想监听数据的时候，就注册一个paylistenter*/
    public void setshopcarpayListenter(IShopcarPayCharListerter listerter){
        if (!paylisterters.contains(listerter)){
            paylisterters.add(listerter);
        }
    }
    /**当页面销毁时，页面不需要再监听数据改变了，我们把它从列表中删除*/
    public void unSetShopcarDataChangerListener(IShopcarDataCharListerter listener) {
        if (listerters.contains(listener)) {
            listerters.remove(listener);
        }
    }
    /**当页面销毁时，待支付页面不需要再监听数据改变了，我们把它从列表中删除*/
    public void unSetShopcarpayChangerListener(IShopcarPayCharListerter listener) {
        if (paylisterters.contains(listener)) {
            paylisterters.remove(listener);
        }
    }
    /**更具id判断商品是否存，如果存在将这个消息返回*/
    public ShopcarBean getShopcarBan(String productId){
        for (ShopcarBean shopcarBean : shopcarBeans) {
            if (productId.equals(shopcarBean.getProductId())){
                return shopcarBean;
            }
        }
        return null;
    }
    /** 更新缓存中一个条目的商品数量*/
    public void updateProduceNum(int position,String newNum){
        ShopcarBean shopcarBean = shopcarBeans.get(position);
        shopcarBean.setProductNum(newNum);
        //通知Ui页面，商品的数量发生了改变
        for (IShopcarDataCharListerter listerter : listerters) {
            //使用接口回调刷新
            listerter.onOneChanged(position,shopcarBean);
            listerter.onManeyvhanged(getMoney());
        }
    }


    /**更新缓存中购物车的商品的数量*/
    public void updateProductNum(String productId,String newNum){
        int i = 0;
        for (ShopcarBean shopcarBean : shopcarBeans) {
            if (productId.equals(shopcarBean.getProductId())){
                //通知Ui页面,商品数量发生了改变
                shopcarBean.setProductNum(newNum);
                for (IShopcarDataCharListerter listerter : listerters) {
                    //使用接口回调
                    listerter.onOneChanged(i,shopcarBean);
                    listerter.onManeyvhanged(getMoney());
                }
                break;
            }
            i++;
        }
    }
    /**将你删除的商品从购物车集合中删除*/
    public void delteShopBeanDeleteList(){
        //首先将你删除的数据在购物车缓存集合中删除
        shopcarBeans.removeAll(deleteShopBeans);
        //再清空一下你的删除缓存集合
        deleteShopBeans.clear();

        //通知Ui页面进行数据刷新
        for (IShopcarDataCharListerter listerter : listerters) {
            listerter.ondataChanged(shopcarBeans);
            listerter.onManeyvhanged(getMoney());
            listerter.onAllselected(false);
        }
    }
    /**将选择的商品支付成功加入已经支付成功的集合*/
    public void setSelectedPaySucessBeans(List<ShopcarBean> selectedListBeans){
        //成功的将选中存入
        this.selectedPaySucessList = selectedListBeans;


    }
    /**将选择的商品支付失败加入已经支付成功的集合*/
    public void setSelectedPayEorryBeans(List<ShopcarBean> selectedListBeans){
        //失败的将选中存入
       this.selectedPaySucessList =selectedListBeans;
    }
    /**让别的类能够拿到支付成功的集合*/
    public List<ShopcarBean> getSelectedPaySucessBeans(){
        List<ShopcarBean> getselectedSucessList = new ArrayList<>();
        for (ShopcarBean shopcarBean : selectedPaySucessList) {
            getselectedSucessList.add(shopcarBean);
        }
        return getselectedSucessList;
    }
    /**让别的类能够拿到支付成功的集合*/
    public List<ShopcarBean> getSelectedPayErroyBeans(){
        return selectedPayErroyList;
    }
    /**获取已经选择的商品*/
    public List<ShopcarBean> getSelectedShopBeans() {
        List<ShopcarBean> selectedList = new ArrayList<>();
        for(ShopcarBean shopcarBean:shopcarBeans) {
            if (shopcarBean.isProductSelected()) {
                selectedList.add(shopcarBean);
            }
        }
        return selectedList;
    }
    /**将选中的商品从缓存中删除*/
    public void removeselectshopBean(){
        shopcarBeans.removeAll(getSelectedShopBeans());
        //利用接口回调刷新ui
        for (IShopcarDataCharListerter listerter : listerters) {
            listerter.ondataChanged(shopcarBeans);
            listerter.onManeyvhanged(getMoney());
            listerter.onAllselected(false);
        }
    }
    /**将你要删除的列表信息存到定义的删除集合当中*/
    public void addDeleteShopBeans(ShopcarBean shopcarBean,int position){
        deleteShopBeans.add(shopcarBean);
        //判断一下当前的商品数量和你要删除的商品数量是否一样，如果一样的话，就代表全选删除
        boolean isAllSelcted = deleteShopBeans.size() == shopcarBeans.size();
        //如果全部删除了就要子类刷新Ui
        for (IShopcarDataCharListerter listerter : listerters) {
            listerter.onOneChanged(position,shopcarBean);
            if (isAllSelcted){
                listerter.onAllselected(isAllSelcted);
            }
        }
    }
    /**删除一个删除列表的数据*/
    public void deleteOneShopBeans(ShopcarBean shopcarBean,int position){
        deleteShopBeans.remove(shopcarBean);
        //需要更新子类ui
        for (IShopcarDataCharListerter listerter : listerters) {
            listerter.onOneChanged(position,shopcarBean);
            listerter.onAllselected(false);
        }
    }
    /** 看一看删除列表是否存在这个数据*/
    public boolean checkDeleteInfoShopCarBeanList(ShopcarBean shopcarBean){
        return deleteShopBeans.contains(shopcarBean);
    }
    /**让别的类可以拿到你要删除的数据*/
    public List<ShopcarBean> getdeleteShopBeanList(){
        return deleteShopBeans;
    }
    /**因为状态只有两个，一个true，一个false，所以改成相反的即可*/
    public void updateProductSelected(int position){
        ShopcarBean shopcarBean = shopcarBeans.get(position);
        boolean newProductSelected = !shopcarBean.isProductSelected();
        shopcarBean.setProductSelected(newProductSelected);
        //数据发生改变之后，需要通知Ui页面
        for (IShopcarDataCharListerter listerter : listerters) {
            listerter.onOneChanged(position,shopcarBean);
            listerter.onManeyvhanged(getMoney());
            //判断一下如果商品都选择了，就让全选为true，不然的话让全选为false
            if (isAllSelected()){
                listerter.onAllselected(true);
            }else{
                listerter.onAllselected(false);
            }
        }
    }


    /** 计算当前选中商品的价格*/
    public String getMoney(){
        float totalPrice = 0;
        for (ShopcarBean shopcarBean : shopcarBeans) {
            if (shopcarBean.isProductSelected()){
                float productPrice = Float.parseFloat(shopcarBean.getProductPrice());
                int productNum = Integer.parseInt(shopcarBean.getProductNum());
                totalPrice = totalPrice+productPrice*productNum;
            }
        }
        return String.valueOf(totalPrice);
    }
    /**看一看商品是否选择，如果没选择返回false，如果选择了的话就返回true*/
    public boolean isAllSelected() {
        for(ShopcarBean shopcarBean:shopcarBeans) {
            if (!shopcarBean.isProductSelected()) {
                return false;
            }
        }
        return true;
    }
    /** 将所有的商品都加入到删除列表当中*/
    public void selectAllProductInEditMode(boolean isAllSelect) {
        if (isAllSelect) {
            deleteShopBeans.clear();
            deleteShopBeans.addAll(shopcarBeans);
        } else {
            deleteShopBeans.clear();
        }
        for(IShopcarDataCharListerter listener:listerters) {
            listener.onAllselected(isAllSelect);
            listener.ondataChanged(shopcarBeans);
        }
    }
    /**将所有的商品选择状态对应着全选框*/
    public void selectAllProduct(boolean isAllSelect) {
        for(ShopcarBean shopcarBean:shopcarBeans) {
            shopcarBean.setProductSelected(isAllSelect);
        }
        //通知UI更新页面
        //通知UI，数据发生了改变，需要更新UI
        for(IShopcarDataCharListerter listener:listerters) {
            listener.ondataChanged(shopcarBeans);
            listener.onManeyvhanged(getMoney());
            listener.onAllselected(isAllSelect);
        }
    }
    /** 判断删除的集合长度和商品总数量集合的长度是否一致*/
    public boolean isAllSelectInEditMode() {
        return deleteShopBeans.size() == shopcarBeans.size();
    }
    // 定义接口，当购物车数据发生改变的时候，通过接口来通知ui刷新
    public interface IShopcarDataCharListerter{
        void ondataChanged(List<ShopcarBean> shopcarBeanList);
        void onOneChanged(int position,ShopcarBean shopcarBean);
        void onManeyvhanged(String moneyValue);
        void onAllselected(boolean isAllSelect);
    }
    /** 定义接口，当待支付数据发生改变的时候，通过接口来通知ui刷新*/
    public interface IShopcarPayCharListerter{
        void onPayList(List<FindPayBean> shopcarBeanList);
        void onSendList(List<FindSendBean> findSendBeans);

    }
}
