package com.shopmall.bawei.shopmall1805;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GreenDaoBean {
    private String productId;
    private String productName;
    private String productNum;
    private String url;
    private String productPrice;
    private boolean productSelected;

    @Generated(hash = 2015832813)
    public GreenDaoBean(String productId, String productName, String productNum,
            String url, String productPrice, boolean productSelected) {
        this.productId = productId;
        this.productName = productName;
        this.productNum = productNum;
        this.url = url;
        this.productPrice = productPrice;
        this.productSelected = productSelected;
    }
    @Generated(hash = 826843181)
    public GreenDaoBean() {

    }
    public String getProductId() {
        return this.productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return this.productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductNum() {
        return this.productNum;
    }
    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getProductPrice() {
        return this.productPrice;
    }
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
    public boolean getProductSelected() {
        return this.productSelected;
    }
    public void setProductSelected(boolean productSelected) {
        this.productSelected = productSelected;
    }
}
