package com.shopmall.bawei.shopmall1805.home;

import java.io.Serializable;

public class Bean implements Serializable {
    private String productId;
    private String productName;
    private String productNum;
    private String url;
    private String productPrice;
    private boolean productSelected;

    public Bean() {
    }

    public Bean(String productId, String productName, String productNum, String url, String productPrice, boolean productSelected) {
        this.productId = productId;
        this.productName = productName;
        this.productNum = productNum;
        this.url = url;
        this.productPrice = productPrice;
        this.productSelected = productSelected;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isProductSelected() {
        return productSelected;
    }

    public void setProductSelected(boolean productSelected) {
        this.productSelected = productSelected;
    }
}
