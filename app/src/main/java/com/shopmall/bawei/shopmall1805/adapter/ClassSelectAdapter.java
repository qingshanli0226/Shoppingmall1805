package com.shopmall.bawei.shopmall1805.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.shopmall.bawei.shopmall1805.R;
import java.util.List;
import baseurl.SkirstBean;


public class ClassSelectAdapter extends BaseRvAdapter {
    private Context context;
    public ClassSelectAdapter(Context context) {
        this.context = context;
    }
    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType) {
            case 0:

                return R.layout.hot_layout;
            case 1:

                return R.layout.often_layout;
        }
            return R.layout.hot_layout;

    }

    @Override
    protected void convert(BaseViewHoder baseViewHoder, int viewType, Object o) {
        switch (viewType) {
            case 0:


                List<SkirstBean.ResultBean.HotProductListBean> h_6 = (List<SkirstBean.ResultBean.HotProductListBean>)o;
                RecyclerView hot = baseViewHoder.getView(R.id.hot_class_re);
                Hot_Adapter hotAdapter = new Hot_Adapter(R.layout.hot_class_item_layout, h_6);
                hot.setAdapter(hotAdapter);
                hot.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case 1:

                break;
        }
    }

    @Override
    public int getViewType(int position) {
        return position;
    }


}
