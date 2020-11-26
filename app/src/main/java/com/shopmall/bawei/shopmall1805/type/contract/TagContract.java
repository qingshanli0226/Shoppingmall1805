package com.shopmall.bawei.shopmall1805.type.contract;

import com.bw.net.bean.TagBean;
import com.shopmall.bawei.shopmall1805.base.BasePresenter;
import com.shopmall.bawei.shopmall1805.base.IView;

public class TagContract {

    public interface TagView extends IView {
        void onOk(TagBean tagBean);
    }

    public abstract static class ITagPresenter extends BasePresenter<TagView> {
        public abstract void getTag();
    }

}
