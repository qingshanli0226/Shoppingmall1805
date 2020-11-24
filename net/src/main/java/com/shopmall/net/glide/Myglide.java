package com.shopmall.net.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.shopmall.bawei.net.R;

public class Myglide {

    private static Myglide myglide=null;

    public static Myglide getMyglide(){
          if (myglide==null){
              myglide=new Myglide();
          }
        return myglide;
    }

    public void circenglide(Context context,ImageView imageView, Object object){
        Glide.with(context)
                .load(object)
                .placeholder(R.mipmap.new_img_loading_2)
                .transform(new CenterCrop(),new CircleCrop())
                .into(imageView);
    }

    public void centercenglide(Context context, ImageView imageView,Object object){
        Glide.with(context)
                .load(object)
                .placeholder(R.mipmap.new_img_loading_2)
                .transform(new CenterCrop())
                .into(imageView);
    }
}
