package framework.mvpc;


import android.util.Log;

import net.FoodService;
import net.RxjavaRetortUlis;

import framework.Contact;
import framework.mvpc.ExtractObserver.TypeHome;
import framework.mvpc.ExtractObserver.TypeLable;
import framework.mvpc.ExtractObserver.TypecloBean;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mode.ClothesBean;
import mode.HomeBean;
import mode.javabean;

public
class jsonModel implements Contact.centerUserImodel {



    @Override
    public void getshopcal(int count) {
        Log.i("====","count"+count);
        FoodService foodService  = RxjavaRetortUlis.getInstance().create(FoodService.class);
        Observable<ClothesBean> clothesBeanJson = null;
        Observable<javabean> javaBeanJson = null;
        Observable<HomeBean> homeBeanJson = null;
        if (count==0){
            clothesBeanJson = foodService.getSkirt();
        }else if (count==1){
            clothesBeanJson = foodService.getjacket();
        }else if (count==2){
            clothesBeanJson = foodService.getTrouser();
        }else if (count==3){
            clothesBeanJson = foodService.getCoat();
        }else if (count==4){
            clothesBeanJson = foodService.getAccessries();
        }else if (count == 5 ){
            javaBeanJson = foodService.getLabel();//标签
        }else if (count == 6 ){
            homeBeanJson = foodService.getHome();//首页
        }
        if (clothesBeanJson!=null){
            clothesBeanJson.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new TypecloBean(){
                        @Override
                        public void onNext(ClothesBean clothesBean) {
                            jsonPresenter.clothesBeanObserver.onNext(clothesBean);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        }
        if (javaBeanJson!=null){
            javaBeanJson.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new TypeLable(){
                        @Override
                        public void onNext(javabean javabean) {
                            jsonPresenter.javabeanObserver.onNext(javabean);
                        }
                        @Override
                        public void onError(Throwable e) {
                        }
                    });
        }
        if (homeBeanJson!=null){
            homeBeanJson.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new TypeHome(){
                        @Override
                        public void onNext(HomeBean homeBean) {
                            Log.i("====","这儿请求到的数据"+homeBean.toString());
                            jsonPresenter.homeBeanObserver.onNext(homeBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                        }
                    });
        }

    }

}
