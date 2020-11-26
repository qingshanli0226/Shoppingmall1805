package com.bawei.shopmall.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GoodsBean {

    private String name;
    private String cover_price;
    private String figure;
    private String product_id;
    private int number = 1;
    @Generated(hash = 1389099549)
    public GoodsBean(String name, String cover_price, String figure,
            String product_id, int number) {
        this.name = name;
        this.cover_price = cover_price;
        this.figure = figure;
        this.product_id = product_id;
        this.number = number;
    }
    @Generated(hash = 1806305570)
    public GoodsBean() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getProduct_id() {
        return this.product_id;
    }
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    public int getNumber() {
        return this.number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
