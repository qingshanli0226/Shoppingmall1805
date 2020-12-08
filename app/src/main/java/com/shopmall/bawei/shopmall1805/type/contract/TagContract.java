package com.shopmall.bawei.shopmall1805.type.contract;

import com.bw.framework.BasePresenter;
import com.bw.framework.IView;
import com.bw.net.bean.TagBean;


public class TagContract {

    public interface TagView extends IView {
        void onOk(TagBean tagBean);
    }

    public abstract static class ITagPresenter extends BasePresenter<TagView> {
        public abstract void getTag();
    }

}
