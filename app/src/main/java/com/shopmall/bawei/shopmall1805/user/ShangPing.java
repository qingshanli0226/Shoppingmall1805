package com.shopmall.bawei.shopmall1805.user;

import java.io.Serializable;

public class ShangPing implements Serializable {
    private String pic;
    private String name;
    private  String price;

    public ShangPing(String pic, String name, String price) {
        this.pic = pic;
        this.name = name;
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    @Override
    public String toString() {
        return "ShangPing{" +
                "pic='" + pic + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
