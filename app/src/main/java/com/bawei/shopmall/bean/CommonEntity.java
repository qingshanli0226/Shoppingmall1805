package com.bawei.shopmall.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

public class CommonEntity implements CustomTabEntity {
    private String title;
    private int selectIcon;
    private int unSelectIcon;

    public CommonEntity(String title, int selectIcon, int unSelectIcon) {
        this.title = title;
        this.selectIcon = selectIcon;
        this.unSelectIcon = unSelectIcon;
    }

    @Override
    public String getTabTitle() {
        return null;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
