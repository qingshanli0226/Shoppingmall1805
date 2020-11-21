package com.shopmall.bawei.shopmall1805.apter;

import com.flyco.tablayout.listener.CustomTabEntity;

public class CoutomEntiy implements CustomTabEntity {
    private String title;
    private int TabSelectedIcon;
    private  int unTabSelectedIcon;

    public CoutomEntiy(String title, int tabSelectedIcon, int unTabSelectedIcon) {
        this.title = title;
        TabSelectedIcon = tabSelectedIcon;
        this.unTabSelectedIcon = unTabSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return TabSelectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unTabSelectedIcon;
    }
}
