package com.shopmall.framework.constart;

import com.shopmall.framework.callback.Home;
import com.shopmall.framework.callback.ILogin;
import com.shopmall.framework.callback.IRegister;
import com.shopmall.framework.callback.Sort;
import com.shopmall.framework.callback.Tag;
import com.shopmall.framework.mvp.IModel;
import com.shopmall.framework.mvp.IView;
import com.shopmall.framework.mvp.Presenter;
import com.shopmall.framework.mvp.Repository;
import com.shopmall.framework.view.LoadingPage;

import java.util.HashMap;

/**
 * 服务，管理层
 */
public interface Constart {
    /**
     * V 层
     */
    interface LoginConstartView extends IView {
        void Success(Object... objects);
        void Error(String s);
    }

    interface RegisterConstartView extends IView {
        void Success(Object... objects);
        void Error(String s);
    }

    /**
     * home主页 V 层
     */
    interface HomeConstartView extends IView {
         void Success(Object... objects);
         void Error(String s);
    }

    /**
     * 分类 V 层
     */
    interface SortConstartView extends IView {
        void Success(Object... objects);
        void Error(String s);
    }

    /**
     * 标签 V 层
     */
    interface TagConstartView extends IView {
        void Success(Object... objects);
        void Error(String s);
    }

    /**
     * M 层
     */
    interface LoginConstartModel extends IModel {
        void login(String url,HashMap<String, String> map, LoadingPage loadingPage, ILogin iLogin);
    }

    interface RegisterConstartModel extends IModel {
        void register(String url,HashMap<String, String> map, LoadingPage loadingPage, IRegister iRegister);
    }

    /**
     * home主页M 层
     */
    interface HomeConstartModel extends IModel{
           void homec(String url, LoadingPage loadingPage, Home home);
    }

    /**
     * 分类 M 层
     */
    interface SortConstartModel extends IModel{
          void Sort(String url,LoadingPage loadingPage,  Sort sort);
    }

    /**
     * 标签 M 层
     */
    interface TagConstartModel extends IModel{
        void Tag(String url,LoadingPage loadingPage, Tag tag);
    }

    /**
     * 仓库层
     */
    abstract class LoginConstartRepository extends Repository<LoginConstartModel> {
        public abstract void login(String url,HashMap<String, String> map, LoadingPage loadingPage, ILogin iLogin);
    }

    abstract class RegisterConstartRepository extends Repository<RegisterConstartModel> {
        public abstract void register(String url,HashMap<String, String> map, LoadingPage loadingPage, IRegister iRegister);
    }

    /**
     * home主页仓库 层
     */
    abstract class HomeConstartRepository extends Repository<HomeConstartModel>{
        public abstract void homec(String url,LoadingPage loadingPage,  Home home);
    }

    /**
     * 分类 仓库 层
     */
    abstract class SortConstartRepository extends Repository<SortConstartModel>{
       public abstract void Sort(String url,LoadingPage loadingPage, Sort sort);
    }

    /**
     * 标签 仓库 层
     */
    abstract class TagConstartRepository extends Repository<TagConstartModel>{
        public abstract void Tag(String url,LoadingPage loadingPage, Tag tag);
    }

    /**
     * P 层
     */
    abstract class LoginConstartPresenter extends Presenter<LoginConstartView,LoginConstartRepository> {
        public LoginConstartPresenter(LoginConstartView loginConstartView) {
            super(loginConstartView);
        }

        public abstract void login(String loginUrl, HashMap<String, String> map, LoadingPage loadingPage);
    }

    abstract class RegisterConstartPresenter extends Presenter<RegisterConstartView,RegisterConstartRepository> {

        public RegisterConstartPresenter(RegisterConstartView registerConstartView) {
            super(registerConstartView);
        }

        public abstract void register(String loginUrl, HashMap<String, String> map, LoadingPage loadingPage);
    }

    /**
     * home主页P 层
     */
    abstract class HomeConstartPresenter extends Presenter<HomeConstartView,HomeConstartRepository> {
        public HomeConstartPresenter(HomeConstartView homeConstartView) {
            super(homeConstartView);
        }
        public abstract void homec(String url, LoadingPage loadingPage);
    }

    /**
     * 分类 P 层
     */
    abstract class SortConstartPresenter extends Presenter<SortConstartView,SortConstartRepository> {
        public SortConstartPresenter(SortConstartView sortConstartView) {
            super(sortConstartView);
        }
        public abstract void Sort(String url,LoadingPage loadingPage);
    }

    /**
     * 标签 P 层
     */
    abstract class TagConstartPresenter extends Presenter<TagConstartView,TagConstartRepository> {
        public TagConstartPresenter(TagConstartView tagConstartView) {
            super(tagConstartView);
        }
        public abstract void tag(String url,LoadingPage loadingPage);
    }

}
