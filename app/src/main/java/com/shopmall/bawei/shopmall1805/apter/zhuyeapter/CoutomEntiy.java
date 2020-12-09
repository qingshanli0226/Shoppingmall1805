package com.shopmall.bawei.shopmall1805.apter.zhuyeapter;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTabSelectedIcon(int tabSelectedIcon) {
        TabSelectedIcon = tabSelectedIcon;
    }

    public int getUnTabSelectedIcon() {
        return unTabSelectedIcon;
    }

    public void setUnTabSelectedIcon(int unTabSelectedIcon) {
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
