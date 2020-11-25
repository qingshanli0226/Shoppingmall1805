package com.shopmall.bawei.shopmall1805.type.contract;

import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.net.mode.TagBean;

public class TagContract {
    public interface ITagView extends IView {
        void onTag(TagBean tagBean);
    }
    public static abstract class ITagPresenter extends BasePresenter<ITagView>{
        public abstract void tag();
    }
}
