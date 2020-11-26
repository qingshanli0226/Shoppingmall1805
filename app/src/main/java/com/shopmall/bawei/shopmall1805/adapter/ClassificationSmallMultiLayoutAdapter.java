package com.shopmall.bawei.shopmall1805.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.shopmall.bawei.shopmall1805.R;
import java.util.List;
import baseurl.SkirstBean;


public class ClassificationSmallMultiLayoutAdapter extends BaseRvAdapter {
    private Context context;
    public ClassificationSmallMultiLayoutAdapter(Context context) {
        this.context = context;
    }
    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType) {
            case 0:

                return R.layout.classification_hot;
            case 1:

                return R.layout.classification_often;
        }
            return R.layout.classification_hot;

    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, Object o) {
        switch (viewType) {
            case 0:


                List<SkirstBean.ResultBean.HotProductListBean> h_6 = (List<SkirstBean.ResultBean.HotProductListBean>)o;
                RecyclerView hot = baseViewHoder.getView(R.id.hot_class_re);
                ClassificationSmallHotAdapter hotAdapter = new ClassificationSmallHotAdapter(R.layout.item_classification_hot, h_6);
                hot.setAdapter(hotAdapter);
                hot.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case 1:
                List<SkirstBean.ResultBean.ChildBean> h_7 = (List<SkirstBean.ResultBean.ChildBean>)o;
                RecyclerView often = baseViewHoder.getView(R.id.often_class_re);
                ClassificationSmallOftenAdapter classificationSmallOften_adapter = new ClassificationSmallOftenAdapter(R.layout.item_classification_often, h_7);
                often.setAdapter(classificationSmallOften_adapter);
                often.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

                break;
        }
    }

    @Override
    public int getViewType(int position) {
        return position;
    }


}
