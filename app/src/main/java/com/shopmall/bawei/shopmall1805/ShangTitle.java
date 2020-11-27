package com.shopmall.bawei.shopmall1805;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ShangTitle {
    private String pic;
    private String name;
    private  String price;
    @Generated(hash = 1941315893)
    public ShangTitle(String pic, String name, String price) {
        this.pic = pic;
        this.name = name;
        this.price = price;
    }
    @Generated(hash = 437954575)
    public ShangTitle() {
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}
