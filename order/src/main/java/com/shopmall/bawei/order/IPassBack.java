package com.shopmall.bawei.order;

import java.util.ArrayList;
import java.util.List;

import mode.BaseBean;

public
class IPassBack {
    private List<IDataBack> iDataBacks = new ArrayList<>();
    private static IPassBack iPassBack = null;
    private BaseBean baseBean = new BaseBean();
    public static IPassBack getInstance(){
        if (iPassBack==null){
            synchronized (IPassBack.class){
                if (iPassBack==null){
                    iPassBack = new IPassBack();
                }
            }
        }
        return iPassBack;
    }
    public void setiDataBacks(IDataBack iDataBack){
       if (iDataBacks.contains(iDataBack)){
           iDataBacks.add(iDataBack);
       }
    }
    public void addData(BaseBean baseBean){
        this.baseBean = baseBean;
    }
    public void Updata(){
        for (IDataBack iDataBack :iDataBacks){
            iDataBack.onJsonData(baseBean);
        }
    }
    public interface IDataBack{
        void onJsonData(BaseBean baseBean);
    }
}
