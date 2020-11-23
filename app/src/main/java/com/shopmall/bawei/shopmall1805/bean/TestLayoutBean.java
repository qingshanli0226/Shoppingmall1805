package com.shopmall.bawei.shopmall1805.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class TestLayoutBean implements MultiItemEntity {

    //轮播图
    public static final int EDIT = 0;
    //分类
    public static final int CHECK = 1;
    //广告
    public static final int SELECT = 2;
    //优惠
    public static final int YOUHUI = 3;
    //新品
    public static final int XINPIN = 4;
    //推荐
    public static final int TUIJIAN = 5;//广告
    //item类型
    private int fieldType;


    public TestLayoutBean(int fieldType) {
        //将传入的type赋值
        this.fieldType = fieldType;
    }
    @Override
    public int getItemType() {
        return fieldType;
    }

}
