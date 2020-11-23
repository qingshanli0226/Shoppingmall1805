package com.shopmall.bawei.shopmall1805.app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.common.ConfigUrl;
import com.shopmall.bawei.shopmall1805.common.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyHomeAdapter extends BaseMultiItemQuickAdapter<HomeBean,BaseViewHolder>{

    private List<String> list=new ArrayList<>();
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyHomeAdapter(List<HomeBean> data) {
        super(data);
          addItemType(0, R.layout.layout_banner);
          addItemType(1,R.layout.layout_wangge);
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeBean item) {
        int itemViewType = helper.getItemViewType();
        switch (itemViewType){
            case 0:
                Log.i("TAG", "convert:1 ");
                Banner banner =  helper.getView(R.id.item_banner);
                List<HomeBean.BannerInfoBean> banner_info = item.getBanner_info();
                for (int i = 0; i < banner_info.size(); i++) {
                    list.add(banner_info.get(i).getImage());
                }
                banner.setImages(list);
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(ConfigUrl.BASE_URl_IMAGE+path).into(imageView);
                    }
                });
                banner.start();
                break;
            case 1:
                Log.i("TAG", "convert: ");
//                for (int i = 0; i < item.getChannel_info().size(); i++) {
//                    Glide.with(mContext).load(ConfigUrl.BASE_URl_IMAGE+item.getChannel_info().get(i).getImage())
//                            .into((ImageView) helper.getView(R.id.item_wangge));
//                }
                break;
        }
    }
}
