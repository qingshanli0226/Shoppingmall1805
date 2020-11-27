package framework;

import mode.ClothesBean;
import mode.HomeBean;
import mode.LableBean;

public
interface JsonDataCallBace {
    void clothesBean(ClothesBean e);
    void javabean(LableBean e);
    void homeBean(HomeBean e);
    void Error(String error);
}
