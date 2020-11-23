package framework.mvpc;

import android.util.Log;

import java.util.List;

import framework.Contact;
import framework.Userc;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import mode.HomeBean;

public class jsonPresenter extends Contact.centetUserpepostory{
    public jsonPresenter(Contact.CenterUserIview centerUserIview) {
        super(centerUserIview);
    }
    public static Observer<HomeBean> observer;
    @Override
    public void getHomeurl(final Userc userc) {
        Repostory.getHomeur();

        observer = new Observer<HomeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeBean homeBean) {
                Log.i("====","P层数据"+homeBean.toString());
                List<HomeBean.ResultBean.BannerInfoBean> banner_info = homeBean.getResult().getBanner_info();
                Log.i("====","这是p层的轮播图获取"+banner_info);
                userc.Susses(homeBean);
            }

            @Override
            public void onError(Throwable e) {
                    userc.Error(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    protected void createRepostory() {
        Log.i("====","这是Presenter层");
        Repostory = new JsonRepository();
    }
}
