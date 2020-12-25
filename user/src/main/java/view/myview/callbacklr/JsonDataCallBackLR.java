package view.myview.callbacklr;

import java.util.List;

import framework.JsonDataCallBace;
import mode.BaseBean;
import mode.ClothesBean;
import mode.HomeBean;
import mode.LableBean;
import mode.LoginBean;
import mode.RegisterBean;
import mode.ShopcarBean;

public
class JsonDataCallBackLR implements JsonDataCallBace {
    @Override
    public void clothesBean(ClothesBean e) {

    }

    @Override
    public void javabean(LableBean e) {

    }

    @Override
    public void homeBean(HomeBean e) {

    }

    @Override
    public void registerBean(RegisterBean e) {

    }

    @Override
    public void loginBean(LoginBean e) {

    }

    @Override
    public void shopBean(BaseBean<List<ShopcarBean>> shopcarBean) {

    }

    @Override
    public void Error(String error) {

    }
}
