package com.shopmall.bawei.shopmall1805.constart;

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
    interface LoginConstartView extends Iview {

    }

    /**
     * M 层
     */
    interface LoginConstartModel extends IModel{

    }

    /**
     * 仓库层
     */
    abstract class LoginConstartRepository extends Repository<LoginConstartModel>{

    }

    /**
     * P 层
     */
    abstract class LoginConstartPresenter extends Presenter<LoginConstartView,LoginConstartRepository>{


        public LoginConstartPresenter(LoginConstartView loginConstartView) {
            super(loginConstartView);
        }
    }

}
