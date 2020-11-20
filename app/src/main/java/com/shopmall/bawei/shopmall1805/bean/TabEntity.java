package com.shopmall.bawei.shopmall1805.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabEntity implements CustomTabEntity {
    private String title;
    private int select;
    private int selected;

    public TabEntity(String title, int select, int selected) {
        this.title = title;
        this.select = select;
        this.selected = selected;
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
        return selected;
    }
}
