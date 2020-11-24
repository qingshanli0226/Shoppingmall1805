package com.shopmall.framework.mvp;

public abstract class Repository <Model extends IModel> {
    protected Model mModel;

    protected abstract void createModel();

    public Repository(){
        createModel();
    }

    protected void releaseModel(){
        if (mModel != null){
            mModel.destroy();
            mModel = null;
        }
    }
}
