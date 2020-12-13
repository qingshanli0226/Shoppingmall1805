package com.example.framwork;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GreenCarBean {
    private String cover_price;
    private String figure;
    private String name;
    private String product_id;
    @Generated(hash = 1785047395)
    public GreenCarBean(String cover_price, String figure, String name,
            String product_id) {
        this.cover_price = cover_price;
        this.figure = figure;
        this.name = name;
        this.product_id = product_id;
    }
    @Generated(hash = 913245011)
    public GreenCarBean() {
    }
    public String getCover_price() {
        return this.cover_price;
    }
    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }
    public String getFigure() {
        return this.figure;
    }
    public void setFigure(String figure) {
        this.figure = figure;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getProduct_id() {
        return this.product_id;
    }
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
