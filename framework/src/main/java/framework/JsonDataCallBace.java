package framework;

import java.util.List;

import mode.BaseBean;
import mode.ClothesBean;
import mode.HomeBean;
import mode.LableBean;
import mode.LoginBean;
import mode.RegisterBean;
import mode.ShopcarBean;
import view.loadinPage.UnpaidBean;

public
interface JsonDataCallBace {
    void clothesBean(ClothesBean e);
    void javabean(LableBean e);
    void homeBean(HomeBean e);
    void registerBean(RegisterBean e);
    void loginBean(LoginBean e);
    void shopBean(BaseBean<List<ShopcarBean>>  shopcarBean );
    void Error(String error);
}
