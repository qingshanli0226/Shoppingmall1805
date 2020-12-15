package mvp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.common2.AddShopCarBean;
import com.example.common2.GetShopCarBean;
import com.example.common2.LoginBean;
import com.example.common2.UrlHelp;


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
    private static List<GetShopCarBean> shopcarBeanList = new ArrayList<>();
    private List<GetShopCarBean> deleteShopcarBeanList = new ArrayList<>();

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
        Intent intent = new Intent(context,UrlHelp.class);
        context.startService(intent);//通过start启动service
          getShopCarDataFromServer();



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




    public interface IShopcarDataChangeListener{
        void onDataChanged(List<GetShopCarBean> shopcarBeanList);
        void onOneDataChanged(int position,GetShopCarBean shopcarBean);
        void onMoneyChanged(String moneyValue);
        void onAllSelected(boolean isAllSelect);
    }

    public List<GetShopCarBean> getShopcarBeanList() {
        return shopcarBeanList;
    }

    private void notifyShopcarDataChanged() {
        //遍历这listener列表，去逐个通知页面购物车产品数据变化了
        for(IShopcarDataChangeListener listener:iShopcarDataChangeListenerList) {
            listener.onDataChanged(shopcarBeanList);
        }
    }



}
