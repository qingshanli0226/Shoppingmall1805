package com.bw.common;

public interface IView {
   void onError(String message);
   void showsLoaing();//网络加载的提示
   void hidesLoading(boolean isSuccess);//关闭网络加载的提示
   void showEmpty();
}
