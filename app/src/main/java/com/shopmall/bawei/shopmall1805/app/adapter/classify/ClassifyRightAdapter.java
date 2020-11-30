package com.shopmall.bawei.shopmall1805.app.adapter.classify;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.app.adapter.BaseRvAdapter;
import com.shopmall.bawei.shopmall1805.common.ClothesBean;

import java.util.List;

public class ClassifyRightAdapter extends BaseRvAdapter {
    private Context context;
    public ClassifyRightAdapter(Context context) {
        this.context = context;
    }
    @Override
    protected int getLayoutId(int viewType) {
        switch (viewType){
            case 0:
                return R.layout.classify_small_right_up;
            case 1:
                return R.layout.classify_small_right_down;
        }
        return R.layout.classify_small_right_up;
    }
    @Override
    public void convert(BaseViewHolder holder, int viewType, Object o) {
        switch (viewType){
            case 0:
                List<ClothesBean.ResultBean.HotProductListBean> o1= (List<ClothesBean.ResultBean.HotProductListBean>) o;
                RecyclerView view = holder.getView(R.id.fenlei_up_rv);
                view.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
                ClassifyHotAdapter fenleiUpAdapter=new ClassifyHotAdapter(R.layout.classify_small_right_up_item,  o1);
                view.setAdapter(fenleiUpAdapter);
                break;
            case 1:
                List<ClothesBean.ResultBean.ChildBean> o2= (List<ClothesBean.ResultBean.ChildBean>) o;
                RecyclerView view1 = holder.getView(R.id.fenlei_down_rv);
                view1.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                ClassifyChildAdapter fenleiUpAdapter1=new ClassifyChildAdapter(R.layout.classify_small_right_down_item,  o2);
                view1.setAdapter(fenleiUpAdapter1);
                break;
        }
    }
    @Override
    public int getViewType(int position) {
        return position;
    }
}
