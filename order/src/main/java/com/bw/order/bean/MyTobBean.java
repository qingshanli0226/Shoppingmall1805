package com.bw.order.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MyTobBean implements CustomTabEntity {

    private String title;
    private int selectedIcon;
    private int unSelectedIcon;

    public MyTobBean(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
