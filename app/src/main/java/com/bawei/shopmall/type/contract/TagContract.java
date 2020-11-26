package com.bawei.shopmall.type.contract;

import com.bawei.framework.BasePresenter;
import com.bawei.framework.IView;
import com.bawei.net.TagBean;

public class TagContract {
    public interface ITagView extends IView {
        void onTag(TagBean tagBean);
    }
    public static abstract class ITagPresenter extends BasePresenter<ITagView> {
        public abstract void tag();
    }
}
