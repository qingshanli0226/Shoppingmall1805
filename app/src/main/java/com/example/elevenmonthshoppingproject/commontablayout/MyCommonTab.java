package com.example.elevenmonthshoppingproject.commontablayout;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MyCommonTab implements CustomTabEntity {
    public String title;
    public int selectpic;
    public  int unselectpic;

    public MyCommonTab(String title, int selectpic, int unselectpic) {
        this.title = title;
        this.selectpic = selectpic;
        this.unselectpic = unselectpic;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectpic;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unselectpic;
    }
}
