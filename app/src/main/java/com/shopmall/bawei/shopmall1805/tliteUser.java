package com.shopmall.bawei.shopmall1805;

import com.flyco.tablayout.listener.CustomTabEntity;

public class tliteUser implements CustomTabEntity {
    private String tliteName;
    private int intc;
    private int intb;

    public tliteUser(String tliteName, int intc, int intb) {
        this.tliteName = tliteName;
        this.intc = intc;
        this.intb = intb;
    }

    @Override
    public String getTabTitle() {
        return tliteName;
    }

    @Override
    public int getTabSelectedIcon() {
        return intc;
    }

    @Override
    public int getTabUnselectedIcon() {
        return intb;
    }
}
