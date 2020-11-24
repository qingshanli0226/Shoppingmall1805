package com.shopmall.bawei.shopmall1805.banner;

import com.youth.banner.Transformer;

import java.util.List;

public class Bannerl {

    private static Bannerl bannerl = null;

    public static Bannerl getBannerl(){
        if (bannerl == null){
            bannerl = new Bannerl();
        }
        return bannerl;
    }

    public void bann(com.youth.banner.Banner bannerF1, List<String> image){
        bannerF1.setImageLoader(new GlideImageLoader());
        bannerF1.setImages(image);
        bannerF1.setBannerAnimation(Transformer.BackgroundToForeground);
        bannerF1.start();
    }
}
