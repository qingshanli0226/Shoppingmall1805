package framework;

import mode.ClothesBean;
import mode.HomeBean;
import mode.javabean;

public
interface User2 {
    void successHome(HomeBean e);
    void successClassifs(ClothesBean e);
    void successLable(javabean e);
    void Error(String error);
}
