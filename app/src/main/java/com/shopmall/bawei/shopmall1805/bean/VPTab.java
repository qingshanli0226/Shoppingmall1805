package com.shopmall.bawei.shopmall1805.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class VPTab implements CustomTabEntity {
    private String title;
    private int select;
    private int unselect;

    public VPTab(String title, int select, int unselect) {
        this.title = title;
        this.select = select;
        this.unselect = unselect;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return select;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unselect;
    }
}
