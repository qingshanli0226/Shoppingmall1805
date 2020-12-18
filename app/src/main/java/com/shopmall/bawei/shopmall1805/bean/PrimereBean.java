package com.shopmall.bawei.shopmall1805.bean;

import java.io.Serializable;

public class PrimereBean implements Serializable {
    String id;
    String name;
    String price;
    String pic;

    public PrimereBean(String id, String name, String price, String pic) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "bean.PrimereBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
