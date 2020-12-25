package com.shopmall.bawei.framework.callback;

import com.shopmall.bean.OrderPaybean;

public interface Iorderpayitemlistener {
      void orderpayitem(OrderPaybean.ResultBean order,String money,int postion);
}
