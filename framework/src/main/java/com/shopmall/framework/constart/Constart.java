package com.shopmall.framework.constart;

import com.shopmall.framework.callback.Home;
import com.shopmall.framework.callback.Sort;
import com.shopmall.framework.mvp.IModel;
import com.shopmall.framework.mvp.IPresenter;
import com.shopmall.framework.mvp.IView;
import com.shopmall.framework.mvp.Repository;

/**
 * 服务，管理层
 */
public interface Constart {

    /**
     * V 层
     */
    interface LoginConstartView extends IView { }
    /**
     * home主页 V 层
     */
    interface HomeConstartView extends IView {
         void Success(Object... objects);
         void Error(String s);
    }

    /**
     * 分类主页 V 层
     */
    interface SortConstartView extends IView {
        void Success(Object... objects);
        void Error(String s);
    }

    /**
     * 标签主页 V 层
     */
    interface TagConstartView extends IView {
        void Success(Object... objects);
        void Error(String s);
    }

    /**
     * M 层
     */
    interface LoginConstartModel extends IModel { }

    /**
     * home主页M 层
     */
    interface HomeConstartModel extends IModel{
           void homec(String url, Home home);
    }

    /**
     * 分类主页M 层
     */
    interface SortConstartModel extends IModel{
          void Sort(String url, Sort sort);
    }

    /**
     * 仓库层
     */
    abstract class LoginConstartRepository extends Repository<LoginConstartModel> {

    }

    /**
     * home主页仓库 层
     */
    abstract class HomeConstartRepository extends Repository<HomeConstartModel>{
        public abstract void homec(String url, Home home);
    }

    /**
     * 分类主页仓库 层
     */
    abstract class SortConstartRepository extends Repository<SortConstartModel>{
       public abstract void Sort(String url, Sort sort);
    }

    /**
     * P 层
     */
    abstract class LoginConstartPresenter extends IPresenter<LoginConstartView,LoginConstartRepository> {

        public LoginConstartPresenter(LoginConstartView loginConstartView) {
            super(loginConstartView);
        }
    }

    /**
     * home主页P 层
     */
    abstract class HomeConstartPresenter extends IPresenter<HomeConstartView,HomeConstartRepository>{

        public HomeConstartPresenter(HomeConstartView homeConstartView) {
            super(homeConstartView);
        }

        public abstract void homec(String url);
    }

    /**
     * 分类主页P 层
     */
    abstract class SortConstartPresenter extends IPresenter<SortConstartView,SortConstartRepository>{

        public SortConstartPresenter(SortConstartView sortConstartView) {
            super(sortConstartView);
        }
        public abstract void Sort(String url);

    }

}
