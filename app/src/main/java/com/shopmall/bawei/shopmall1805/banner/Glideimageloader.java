package com.shopmall.bawei.shopmall1805.banner;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.shopmall.bawei.shopmall1805.R;
import com.youth.banner.loader.ImageLoader;

/**
 * 加载图片
 */
public class Glideimageloader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(R.mipmap.new_img_loading_2)
                .transform(new CenterCrop())
                .into(imageView);
    }
}
