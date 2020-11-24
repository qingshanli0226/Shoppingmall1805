package com.shopmall.bawei.shopmall1805.banner;

import com.youth.banner.Transformer;

import java.util.List;

public class Bannerlun {
    /**
     * 轮播图
     * @param bannerF1
     * @param image
     */
    private static Bannerlun bannerlun=null;

    public static Bannerlun getBannerlun(){
        if (bannerlun==null){
            bannerlun=new Bannerlun();
        }
        return bannerlun;
    }

   public void bann(com.youth.banner.Banner bannerF1, List<String> image){
       bannerF1.setImageLoader(new Glideimageloader());
       bannerF1.setImages(image);
       bannerF1.setBannerAnimation(Transformer.BackgroundToForeground);
       bannerF1.start();
   }

}
