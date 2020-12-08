package mvp;

import android.content.Context;
import android.content.IntentFilter;

import com.example.common2.ShopcarBean;
import com.example.common2.UrlHelp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheManager {
    //当用户登录成功后,CacheManger会调用服务端接口请求购物车拿数据.拿到购物车数据后,给shopcarBeanList赋值
    private List<ShopcarBean> shopcarBeanList = new ArrayList<>();
    private List<ShopcarBean> deleteShopcarBeanList = new ArrayList<>();

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

    private void initReceiver() {


    }


    public interface IShopcarDataChangeListener{
        void onDataChanged(List<ShopcarBean> shopcarBeanList);
        void onOneDataChanged(int position,ShopcarBean shopcarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }



}
