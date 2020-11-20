package com.shopmall.bawei.framework.mvp;

/**
 * 仓库层
 */
public abstract class Repository<M extends IModel> {
      protected M mModel;

     protected abstract void createModel();

      public Repository(){
           createModel();
      }
}
