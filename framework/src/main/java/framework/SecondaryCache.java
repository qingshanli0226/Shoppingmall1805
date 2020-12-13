package framework;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mode.ShopcarBean;

public
class SecondaryCache {
    private static  SecondaryCache secondaryCache;
    private List<ShopcarBean>  shopcarBeanList = new ArrayList<>();
    private List<ShopcarBean> deleteShopcarBeanList = new ArrayList<>();

    private List<ISecondaryCacheListener> iSecondaryCacheListeners = new ArrayList<>();

    private Context context;

    private ExecutorService executorService  = Executors.newCachedThreadPool();

    public static SecondaryCache getInstance(){
        if (secondaryCache==null){
            synchronized (SecondaryCache.class){
                if (secondaryCache==null){
                    secondaryCache = new SecondaryCache();
                }
            }
        }
        return secondaryCache;
    }

    /**
     * 构造设置成私有 则允许构造单次 :单例
     * 如果构造不是private 则构造中可被无限次调用
     */

    private SecondaryCache() {

    }

    /**
     * application 可注册该方法
     * 传递上下文参数  作用于自动登录
     * @param context1
     */
    public void init(Context context1){
        this.context  =context1;
    }
    /**
     *  刷新数据，刷新数据时，其他方法只需要 调用该方法，该方法会循环一次
     *  存储页面的坚挺，调用接口中的数据刷新 刷新数据
     */

    public void notiFyShopCarDataChanged(){
        for (SecondaryCache.ISecondaryCacheListener cacheListener : iSecondaryCacheListeners){
            cacheListener.onDataChanged(shopcarBeanList);
        }
    }

    /**
     * 接下来，须要一个添加监听页面的方法，该方法是要把每一个须要监听的页面添加进去
     * 存储成为一个集合  这个集合就是可以做通知其他页面数据改变的动作
     */
    public void setiSecondaryCacheListeners(ISecondaryCacheListener listeners){
        if (!iSecondaryCacheListeners.contains(listeners)){
            iSecondaryCacheListeners.add(listeners);
        }
    }

    /**
     * 接下来，须要一个删除监听页面的方法，当监听页面不需要时，须要将这个页面的监听删除
     * 这是删除须要的
     */
    public void unSetShopcarDataChangerListener(ISecondaryCacheListener listener){
        if (iSecondaryCacheListeners.contains(listener)){
            iSecondaryCacheListeners.remove(listener);
        }
    }

    /**
     * 加入购物车，用于购物车数据页面的展示
     * 加入购物车后，须创循环，此循环遍历监听页面
     * 调用接口的数据刷新动作，将添入新数据更新到各个页面中
     */
    public void addShopcar(ShopcarBean shopcarBean){
        shopcarBeanList.add(shopcarBean);
        for (ISecondaryCacheListener cacheListener : iSecondaryCacheListeners){
            cacheListener.onDataChanged(shopcarBeanList);
        }
    }

    /**
     * 做一调出缓存中集合方
     */
    public List<ShopcarBean> getShopcarBeanList(){
        return shopcarBeanList;
    }

    /**
     * 接口
     */
    public interface ISecondaryCacheListener{
        void onDataChanged(List<ShopcarBean> shopcarBeanList);//数据刷新
        void onOneDataChanged(int position,ShopcarBean shopcarBean);//数据刷新
        void onMoneyChanged(String moneyVilue);//返回金钱价格
        void onAllSelected(boolean isAllSelect);//是否全选
    }

}
