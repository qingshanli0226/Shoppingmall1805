package framework;

import mode.ClothesBean;
import mode.HomeBean;
import mode.LableBean;
import mode.LoginBean;
import mode.RegisterBean;

public
interface JsonDataCallBace {
    void clothesBean(ClothesBean e);
    void javabean(LableBean e);
    void homeBean(HomeBean e);
    void registerBean(RegisterBean e);
    void loginBean(LoginBean e);
    void Error(String error);
}
