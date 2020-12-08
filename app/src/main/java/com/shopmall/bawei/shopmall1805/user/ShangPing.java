package com.shopmall.bawei.shopmall1805.user;

import java.io.Serializable;

public class ShangPing implements Serializable {
    private String productId;
    private  String productPrice;
    private String url;
    private String productName;

    public ShangPing(String productId, String productPrice, String url, String productName) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.url = url;
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
