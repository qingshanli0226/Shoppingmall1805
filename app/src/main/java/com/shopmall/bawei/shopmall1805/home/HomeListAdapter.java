package com.shopmall.bawei.shopmall1805.home;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bw.net.bean.HomeFragmentBean;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.adapter.MyChannelAdapter;


import java.util.List;

public class HomeListAdapter extends BaseMultiItemQuickAdapter<HomeFragmentBean.ResultBean, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeListAdapter(List<HomeFragmentBean.ResultBean> data) {
        super(data);
        addItemType(0, R.layout.item_channel);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeFragmentBean.ResultBean item) {
       switch (helper.getItemViewType()){
           case 0 :
               MyChannelAdapter myChannelAdapter = new MyChannelAdapter(R.layout.item_channel_data,item.getChannel_info());
               RecyclerView recyclerView = helper.getView(R.id.rv_channel);
               recyclerView.setAdapter(myChannelAdapter);
               recyclerView.setLayoutManager(new GridLayoutManager(mContext,5));

               break;
       }
    }


}
