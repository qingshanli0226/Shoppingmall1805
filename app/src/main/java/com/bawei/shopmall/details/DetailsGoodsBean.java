package com.bawei.shopmall.details;

import java.io.Serializable;

public class DetailsGoodsBean implements Serializable {
    private String name;
    private String coverPrice;
    private String figure;
    private String productId;
    private int number = 1;

    /**
     * 是否处于编辑状态
     */
    private boolean isEditing;
    /**
     * 是否被选中
     */
    private boolean isChildSelected;

    public DetailsGoodsBean() {
    }

    public DetailsGoodsBean(String name, String coverPrice, String figure, String productId) {
        this.name = name;
        this.coverPrice = coverPrice;
        this.figure = figure;
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverPrice() {
        return coverPrice;
    }

    public void setCoverPrice(String coverPrice) {
        this.coverPrice = coverPrice;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
    }

    public boolean isChildSelected() {
        return isChildSelected;
    }

    public void setChildSelected(boolean childSelected) {
        isChildSelected = childSelected;
    }
}
