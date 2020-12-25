package com.example.framwork;

import com.example.net.NetBusinessException;
import com.example.net.bean.ErrorBean;

public interface   IView {
    void onError(String code,String message);
    void showLoadings();//网络显示
    void hideLoading(boolean isSuccess, ErrorBean message);//隐藏加载
}
