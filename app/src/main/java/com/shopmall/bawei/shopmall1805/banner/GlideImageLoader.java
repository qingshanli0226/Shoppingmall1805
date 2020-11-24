package com.shopmall.bawei.shopmall1805.banner;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.shopmall1805.R;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(R.drawable.new_img_loading_2)
                .centerCrop()
                .into(imageView);
    }
}
