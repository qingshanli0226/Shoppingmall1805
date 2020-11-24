package com.shopmall.bawei.framework.constart;

import com.shopmall.bawei.framework.callback.Home;
import com.shopmall.bawei.framework.callback.Sort;
import com.shopmall.bawei.framework.mvp.IModel;
import com.shopmall.bawei.framework.mvp.Iview;
import com.shopmall.bawei.framework.mvp.Presenter;
import com.shopmall.bawei.framework.mvp.Repository;

/**
 * 服务，管理层
 */
public interface Constart {

    /**
     * V 层
     */
    interface LoginConstartView extends Iview { }
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
     * M 层
     */
    interface LoginConstartModel extends IModel{ }

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
    abstract class LoginConstartRepository extends Repository<LoginConstartModel>{

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
    abstract class LoginConstartPresenter extends Presenter<LoginConstartView,LoginConstartRepository>{

        public LoginConstartPresenter(LoginConstartView loginConstartView) {
            super(loginConstartView);
        }


    }

    /**
     * home主页P 层
     */
    abstract class HomeConstartPresenter extends Presenter<HomeConstartView,HomeConstartRepository>{


        public HomeConstartPresenter(HomeConstartView homeConstartView) {
            super(homeConstartView);
        }

        public abstract void homec(String url);
    }


    /**
     * 分类主页P 层
     */
    abstract class SortConstartPresenter extends Presenter<SortConstartView,SortConstartRepository>{


        public SortConstartPresenter(SortConstartView sortConstartView) {
            super(sortConstartView);
        }
        public abstract void Sort(String url);
    }



}
