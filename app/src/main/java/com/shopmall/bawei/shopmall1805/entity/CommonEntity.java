package com.shopmall.bawei.shopmall1805.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

public class CommonEntity implements CustomTabEntity {

    private String title;
    private int i;
    private int j;

    public CommonEntity(String title, int i, int j) {
        this.title = title;
        this.i = i;
        this.j = j;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return i;
    }

    @Override
    public int getTabUnselectedIcon() {
        return j;
    }
}
