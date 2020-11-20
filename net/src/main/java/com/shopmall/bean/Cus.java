package com.shopmall.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class Cus  implements CustomTabEntity {

    private String titile;
    private int image1;
    private int iamge2;

    public Cus(String titile, int image1, int iamge2) {
        this.titile = titile;
        this.image1 = image1;
        this.iamge2 = iamge2;
    }

    @Override
    public String getTabTitle() {
        return titile;
    }

    @Override
    public int getTabSelectedIcon() {
        return iamge2;
    }

    @Override
    public int getTabUnselectedIcon() {
        return image1;
    }
}
