package com.shopmall.bawei.shopmall1805.Adper.homeadper;

import com.shopmall.bawei.shopmall1805.R;

import framework.BaseRvAdper;

public
class ClassifyAdper extends BaseRvAdper<Object> {
    private final int HEADLINE = 0;
    private final int INCOMMONE = 1;
    private final int HOTRECOMMEND = 2;
    @Override
    protected int getlayourId(int position) {
        switch (position){
            case HEADLINE: return R.layout.home_view_banner;
            case INCOMMONE: return R.layout.home_view_channel;
            case HOTRECOMMEND: return R.layout.home_view_act;
        }
        return R.layout.home_view_banner;
    }

    @Override
    protected void convert(Object itemData, BaseviewHoder holder, int position) {

    }

    @Override
    protected int getViewType(int position) {
        switch (position){
            case 0:return HEADLINE;
            case 1:return INCOMMONE;
            case 2:return HOTRECOMMEND;
        }
        return HEADLINE;
    }
}
