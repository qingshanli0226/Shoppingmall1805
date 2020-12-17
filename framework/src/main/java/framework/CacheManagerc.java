package framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import net.FoodService;
import net.RxjavaRetortUlis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mode.BaseBean;
import mode.ShopcarBean;
import view.ShopmallConstant;

public
class CacheManagerc {
    private List<ShopcarBean> shopcarBeansList = new ArrayList<>();
    private List<ShopcarBean> goShopBeansList = new ArrayList<>();
    private List<ShopcarBean> deleteShopcarBeanList = new ArrayList<>();

    //这是一个全局属性的类名
    private static CacheManagerc instace;

    //有多个页面，须要监听数据变化，写一个监听列表，列表中有修改查询刷新的方法
    private List<IShopcarDataChangeListener> iShopcarDataChangeListenerList = new ArrayList<>();

    private Context context;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public CacheManagerc() {

    }

    public static  CacheManagerc getInstance(){
        if (instace==null){
            synchronized (CacheManagerc.class){
                if (instace==null){
                    instace = new CacheManagerc();
                }
            }
        }
        return instace;
    }
    public List<ShopcarBean> getShopcarBeansList(){
        goShopBeansList.clear();
        for (ShopcarBean shopcarBean:shopcarBeansList){
            if (shopcarBean.getProductSelected()==true){
                goShopBeansList.add(shopcarBean);
            }
        }
        return goShopBeansList;
    }
    public float getOrderMoney(){
        float money = 0f;
        float money1 = 0f;
        for (ShopcarBean shopcarBean:shopcarBeansList){
            if (shopcarBean.getProductPrice()!=null&&shopcarBean.getProductNum()!=null){
                money = Float.parseFloat(shopcarBean.getProductPrice())*Float.parseFloat(shopcarBean.getProductNum());
                money1 = money1+money;
            }
        }
        return money1;
    }
    public void init(Context context){
        this.context = context;
    }
    //监听当前的登录 调用此类时即开始

    public void getShopcarDataFromServer() {
        shopcarBeansList.clear();
        FoodService foodService = RxjavaRetortUlis.getInstance().create(FoodService.class);
        foodService.getShortcartProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean<List<ShopcarBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<List<ShopcarBean>> listBaseBean) {
                        Log.i("====","购物车后台获取的数据"+listBaseBean);
                        shopcarBeansList.addAll(listBaseBean.getResult());
                        notifyShopcarDataChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void notifyShopcarDataChanged() {

        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeansList);
        }
    }
    public void add(ShopcarBean shopcarBean){
        for (ShopcarBean shopcarBeans:shopcarBeansList){
            if (!shopcarBean.getProductName().equals(shopcarBeans.getProductName())){
                shopcarBeansList.add(shopcarBean);
            }
        }
        for (IShopcarDataChangeListener dataChangeListener :iShopcarDataChangeListenerList){
            dataChangeListener.onDataChanged(shopcarBeansList);

        }
    }
    public List<ShopcarBean> getShopcarBeanList(){
        return shopcarBeansList;
    }
    //检查服务端多个产品是否充足
    public List<ShopcarBean> getSelectedPreduceBeanList(){
        List<ShopcarBean> selectedList = new ArrayList<>();
        for(ShopcarBean shopcarBean:shopcarBeansList) {
            if (shopcarBean.isProductSelected()) {
                selectedList.add(shopcarBean);
            }
        }
        return selectedList;
    }

    public void removeSelectedPrpducte(){
        shopcarBeansList.removeAll(getSelectedPreduceBeanList());

        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeansList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }
    }
    public void setiShopcarDataChangeListener(IShopcarDataChangeListener listener){
        if (!iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.add(listener);
        }
    }

    public String getMoneyValue() {
        float totalPrice = 0;

        for(ShopcarBean shopcarBean:shopcarBeansList) {
            if (shopcarBean.isProductSelected()) {
                if (shopcarBean.getProductPrice()!=null&&shopcarBean.getProductNum()!=null){
                float productPrice = Float.parseFloat(shopcarBean.getProductPrice());
                Log.i("====","product"+productPrice);
                int productNum = Integer.parseInt(shopcarBean.getProductNum());
                totalPrice = totalPrice + productPrice*productNum;
            }
            }
        }
        return String.valueOf(totalPrice);
    }
    //更新服务端购物车产品的选择
    public void updateProductSelected(int position) {
        ShopcarBean shopcarBean = shopcarBeansList.get(position);
        boolean newProductselected = !shopcarBean.isProductSelected();
        Log.i("cccc","new"+newProductselected);
        shopcarBean.setProductSelected(newProductselected);
        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
            if (isAllSelected()) {
                listener.onAllSelected(true);
            } else {
                listener.onAllSelected(false);
            }
        }
    }
    public void updateProductSelectedcopy(int position){
        ShopcarBean shopcarBean = shopcarBeansList.get(position);
        boolean newProductselected = !shopcarBean.isProductSelected();
        shopcarBean.setProductSelected(newProductselected);

    }
    public boolean isAllSelected() {
        for(ShopcarBean shopcarBean:shopcarBeansList) {
            if (!shopcarBean.isProductSelected()) {
                return false;
            }
        }
        return true;
    }
    public void updateProductNum(int position, String newNum) {
        ShopcarBean shopcarBean = shopcarBeansList.get(position);
        shopcarBean.setProductNum(newNum);


        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onMoneyChanged(getMoneyValue());
        }
    }
    //查询是否是第一次添加
    public boolean updateProductNum(String productId, String newNum) {
        int i = 0;
        boolean isFrist = false;
        for(ShopcarBean shopcarBean:shopcarBeansList) {
            if (shopcarBean.getProductId().equals(productId)) {
                isFrist = true;

                int shopCarNum = Integer.parseInt(newNum);
                int s = shopCarNum + 1;
                shopcarBean.setProductNum(String.valueOf(s));
                for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
                    listener.onOneDataChanged(i, shopcarBean);
                    listener.onMoneyChanged(getMoneyValue());
                }
                break;
            }
            i++;
        }
        return isFrist;
    }
    public void unSetShopcarDataChangerListener(CacheManager.IShopcarDataChangeListener listener) {
        if (iShopcarDataChangeListenerList.contains(listener)) {
            iShopcarDataChangeListenerList.remove(listener);
        }
    }

    public ShopcarBean getShopcarBan(String productId) {
        for(ShopcarBean shopcarBean:shopcarBeansList) {
            if (productId.equals(shopcarBean.getProductId())) {
                return shopcarBean;
            }
        }
        return null;
    }
    //全选购物车产品或者全不选
    public void selectAllProduct(boolean isAllSelect) {
        for(ShopcarBean shopcarBean:shopcarBeansList) {
            shopcarBean.setProductSelected(isAllSelect);
        }
        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeansList);
            listener.onMoneyChanged(getMoneyValue());

            listener.onAllSelected(isAllSelect);
        }

    }
    public void goChangeSelectAllState(boolean isSelect){
        for (ShopcarBean shopcarBean :shopcarBeansList){
            shopcarBean.setProductSelected(isSelect);
        }
        for (CacheManagerc.IShopcarDataChangeListener iShopcarDataChangeListener :iShopcarDataChangeListenerList){
            iShopcarDataChangeListener.onMoneyChanged(getMoneyValue());
            iShopcarDataChangeListener.onAllSelected(isSelect);
            iShopcarDataChangeListener.onDataChanged(shopcarBeansList);
            Log.i("pppp","打印此处数据"+shopcarBeansList.toString());
        }
    }
    public void addDeleteShopcarBean(ShopcarBean shopcarBean, int position) {
        deleteShopcarBeanList.add(shopcarBean);
        //编辑模式下添加进入删除队列的是
        boolean isAllSelect = deleteShopcarBeanList.size() == shopcarBeansList.size();

        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            if(isAllSelect) {
                listener.onAllSelected(isAllSelect);
            }
        }
    }

    public boolean isAllSelectInEditMode() {
        return deleteShopcarBeanList.size() == shopcarBeansList.size();
    }

    public void deleteOneShopcarBean(ShopcarBean shopcarBean, int position) {
        deleteShopcarBeanList.remove(shopcarBean);
        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onOneDataChanged(position, shopcarBean);
            listener.onAllSelected(false);
        }
    }

    public void selectAllProductInEditMode(boolean isAllSelect) {
        if (isAllSelect) {
            deleteShopcarBeanList.clear();
            deleteShopcarBeanList.addAll(shopcarBeansList);
        } else {
            deleteShopcarBeanList.clear();
        }

        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onAllSelected(isAllSelect);
            listener.onDataChanged(shopcarBeansList);

        }
        notifyShopcarDataChanged();
    }

    public boolean checkIfDataInDeleteShopcarBeanList(ShopcarBean shopcarBean) {
        return deleteShopcarBeanList.contains(shopcarBean);
    }

    public List<ShopcarBean> getDeleteShopcarBeanList() {
        return deleteShopcarBeanList;
    }
    //从服务端购物车删除一个产品
    public void processDeleteProducts() {
        shopcarBeansList.removeAll(deleteShopcarBeanList);

        deleteShopcarBeanList.clear();

        for(CacheManagerc.IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeansList);
            listener.onMoneyChanged(getMoneyValue());
            listener.onAllSelected(false);
        }

    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public interface IShopcarDataChangeListener{
        void onDataChanged(List<ShopcarBean> shopcarBeanList);
        void onOneDataChanged(int position,ShopcarBean shopcarBean);
        void onMoneyChanged(String moneyVilue);
        void onAllSelected(boolean isAllSelect);
    }




}
