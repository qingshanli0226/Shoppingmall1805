package com.bw.common;

public interface IView {
   void onError(String message);
   void showLoaing();//网络加载的提示
   void hideLoading(boolean isSuccess);//关闭网络加载的提示
   void showEmpty();
}
