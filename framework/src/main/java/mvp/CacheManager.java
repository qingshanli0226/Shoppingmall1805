package mvp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.common2.AddShopCarBean;
import com.example.common2.GetShopCarBean;
import com.example.common2.LoginBean;
import com.example.common2.UrlHelp;
import com.shopmall.bawei.framework.R;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import http.BaseBean;
import http.MyHttp;
import http.ShopmallObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import mvp.view.ShopUserManager;

public class CacheManager {
    //当用户登录成功后,CacheManger会调用服务端接口请求购物车拿数据.拿到购物车数据后,给shopcarBeanList赋值
    private  List<GetShopCarBean> shopcarBeanList = new ArrayList<>();
    private List<GetShopCarBean> deleteShopcarBeanList = new ArrayList<>();
    private List<Bitmap> bitmapList = new ArrayList<>();

    private static CacheManager instance;

    //有多个页面监听数据的变化,所以维护一个监听listener的列表
    private List<IShopcarDataChangeListener> iShopcarDataChangeListenerList = new ArrayList<>();

    private Context context;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private CacheManager(){

    }

    public static CacheManager getInstance(){
        if (instance==null){
            instance=new CacheManager();
        }
        return instance;
    }

    public void init(Context context){
        this.context = context;
        initReceiver();
    }
    //初始化service,完成自动登录
    private void initReceiver() {
        Intent intent = new Intent(context,UrlHelp.class);
        context.startService(intent);//通过start启动service
        //缓存第2步:通过回调监听登录事件,一旦监听到登录成功,立马从服务端获取数据,并且将数据塞到列表缓存中,初始化缓存
        ShopUserManager.getInstance().registerUserLoginChangeListener(new ShopUserManager.IUserLoginChangedListener() {
            @Override
            public void onUserLogin(LoginBean loginBean) {
                getShopCarDataFromServer();//获取购物车数据
            }
        });

    }

    public void getShopCarDataFromServer(){
        MyHttp.getShopmallApiService().getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ShopmallObserver<BaseBean<List<GetShopCarBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<GetShopCarBean>> getShopCarBeans) {
                        List<GetShopCarBean> result = getShopCarBeans.getResult();
                        Log.i("TAG", "onNext: "+result.size());
                        shopcarBeanList.addAll(result);
                        notifyShopcarDataChanged();
                    }

                    @Override
                    public void onRequestError(String errorCode, String errorMessage) {
                        Toast.makeText(context, "请求购物车数据错误"+errorCode+errorMessage,Toast.LENGTH_SHORT).show();
                    }
                });
    }


        //缓存第三步:提供修改缓存数据接口
    public void add (GetShopCarBean shopCarBean){
        shopcarBeanList.add(shopCarBean);//提供接口,添加一个商品到购物车公共数据里
        for (IShopcarDataChangeListener listener:iShopcarDataChangeListenerList){
            listener.onDataChanged(shopcarBeanList);
        }
    }

    public void updateProductNum(int position ,String newNum){
        GetShopCarBean shopCarBean = shopcarBeanList.get(position);
        shopCarBean.setProductNum(newNum);
        for (IShopcarDataChangeListener listener : iShopcarDataChangeListenerList){
            listener.onOneDataChanged(position,shopCarBean);
            listener.onMoneyChanged(getMoneyValue());

        }
    }
    public String getMoneyValue(){
        float totalPrice = 0;
        for (GetShopCarBean getShopCarBean : shopcarBeanList){
            if (getShopCarBean.isProductSelected()){
                float productPrice = Float.parseFloat(getShopCarBean.getProductPrice());
                int productNum = Integer.parseInt(getShopCarBean.getProductNum());
                totalPrice = totalPrice+productPrice*productNum;
            }
        }

        return String.valueOf(totalPrice);
    }

    //当页面销毁时，页面不需要再监听数据改变了，我们把它从列表中删除
    public void unSetShopcarDataChangerListener(IShopcarDataChangeListener listener) {
        if (iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.remove(listener);
        }
    }
    //把它加入到删除队列中
    public void addDeleteShopcarBean(GetShopCarBean shopcarBean, int position) {
        deleteShopcarBeanList.add(shopcarBean);
        boolean isAllSelect = deleteShopcarBeanList.size() == shopcarBeanList.size();//判断下当前删除队列数据数目和购物车商品数目是否一致，一致代表是全选删除
        //需要更新UI
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            if(isAllSelect) {
                listener.onAllSelected(isAllSelect);
            }
        }
    }
    //从删除队列中删除
    public void deleteOneShopcarBean(GetShopCarBean shopcarBean, int position) {
        deleteShopcarBeanList.remove(shopcarBean);
        //需要更新UI
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onAllSelected(false);
        }
    }
    public boolean checkIfDataInDeleteShopcarBeanList(GetShopCarBean shopcarBean) {
        return deleteShopcarBeanList.contains(shopcarBean);
    }
    public interface IShopcarDataChangeListener{
        void onDataChanged(List<GetShopCarBean> shopcarBeanList);
        void onOneDataChanged(int position,GetShopCarBean shopcarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }



    private void notifyShopcarDataChanged() {
        //遍历这listener列表，去逐个通知页面购物车产品数据变化了
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
        }
    }
        //缓存第四步:提供方法让别的类可以获取缓存
    public List<GetShopCarBean> getShopcarBeanList() {
        return shopcarBeanList;
    }

    //当页面想监听数据的改变,就注册一个listener
    public void setiShopcarDataChangeListener(IShopcarDataChangeListener listener){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.adr);
        bitmapList.add(bitmap);
        bitmap=null;
        if (!iShopcarDataChangeListenerList.contains(listener)){
            iShopcarDataChangeListenerList.add(listener);
        }
    }
    public List<IShopcarDataChangeListener> getiShopcarDataChangeListenerList(){
        return iShopcarDataChangeListenerList;
    }

    public GetShopCarBean getShopCarBean(String productId){
        for (GetShopCarBean shopcarBean:shopcarBeanList){
            if (productId.equals(shopcarBean.getProductId())){
                return shopcarBean;
            }
        }
        return null;
    }
    public void setShopcarDataChangeListener(IShopcarDataChangeListener listener) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.adr);
        bitmapList.add(bitmap);
        bitmap = null;
        if (!iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.add(listener);
        }
    }
    public boolean isAllSelected() {
        for(GetShopCarBean shopcarBean:shopcarBeanList) {
            if (!shopcarBean.isProductSelected()) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllSelectInEditMode() {
        return deleteShopcarBeanList.size() == shopcarBeanList.size();
    }

    public void updateProductSelected(int position) {
        GetShopCarBean shopcarBean = shopcarBeanList.get(position);
        boolean newProductselected = !shopcarBean.isProductSelected();
        shopcarBean.setProductSelected(newProductselected);

        //通知UI，数据发生了改变，需要更新UI
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
            if (isAllSelected()) {
                listener.onAllSelected(true);
            } else {
                listener.onAllSelected(false);
            }
        }
    }

    public void selectAllProduct(boolean isAllSelect) {
        for(GetShopCarBean shopcarBean:shopcarBeanList) {
            shopcarBean.setProductSelected(isAllSelect);
        }

        //通知UI更新页面
        //通知UI，数据发生了改变，需要更新UI
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(isAllSelect);
        }

    }
    public void processDeleteProducts() {
        //首先将删除列表中的数据在购物车缓存张删除
        shopcarBeanList.removeAll(deleteShopcarBeanList);
        //把删除列表清空
        deleteShopcarBeanList.clear();

        //通知UI刷新页面
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }

    }

    //获取已经选择的商品列表
    public List<GetShopCarBean> getSelectedProductBeanList() {
        List<GetShopCarBean> selectedList = new ArrayList<>();
        for(GetShopCarBean shopcarBean:shopcarBeanList) {
            if (shopcarBean.isProductSelected()) {
                selectedList.add(shopcarBean);
            }
        }
        return selectedList;
    }

    //从缓存中删除已经支付的商品
    public void removeSelectedProducts() {
        shopcarBeanList.removeAll(getSelectedProductBeanList());

        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }
    }

    public List<GetShopCarBean> getDeleteShopcarBeanList() {
        return deleteShopcarBeanList;
    }





}
