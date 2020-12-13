package com.shopmall.bawei.shopmall1805.classification.adapter;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common2.SkirstBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.BaseRvAdapter;

import java.util.List;



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
                List<SkirstBean.HotProductListBean> h_6 = (List<SkirstBean.HotProductListBean>)o;
                Log.i("TAG", "convert: ");
                RecyclerView hot = baseViewHoder.getView(R.id.hot_class_re);
                hot.setLayoutManager(new LinearLayoutManager(context));
                ClassificationSmallHotAdapter hotAdapter = new ClassificationSmallHotAdapter(R.layout.item_classification_hot, h_6);
                hot.setAdapter(hotAdapter);
                break;
            case 1:
                List<SkirstBean.ChildBean> h_7 = (List<SkirstBean.ChildBean>)o;
                RecyclerView often = baseViewHoder.getView(R.id.often_class_re);
                often.setLayoutManager(new LinearLayoutManager(context));
                ClassificationSmallOftenAdapter classificationSmallOften_adapter = new ClassificationSmallOftenAdapter(R.layout.item_classification_often, h_7);
                often.setAdapter(classificationSmallOften_adapter);

                break;
        }
    }

    @Override
    public int getViewType(int position) {
        return position;
    }


}
