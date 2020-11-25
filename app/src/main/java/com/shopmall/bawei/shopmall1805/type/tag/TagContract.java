package com.shopmall.bawei.shopmall1805.type.tag;

import com.example.framework.base.BasePresenter;
import com.example.framework.mvp.IView;
import com.example.net.bean.TagBean;

public interface TagContract {
    interface TagView extends IView{
        void onOk(TagBean bean);
        void onError(String msg);
    }
    public abstract class TagPresenter extends BasePresenter<TagView>{
        abstract void showTag();
    }
}
