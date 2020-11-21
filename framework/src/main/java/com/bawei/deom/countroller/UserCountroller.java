package com.bawei.deom.countroller;

import com.bawei.deom.BaseAroute;
import com.bawei.deom.IView;

import java.util.List;

public class UserCountroller {
    public interface UserView extends IView {
      void   RecommendedView();
    }
    public abstract static class UserShow extends BaseAroute<UserView>{
       public abstract  void RecommendedShow();

        ;
    }
}
