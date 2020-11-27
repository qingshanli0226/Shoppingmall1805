package com.shopmall.bawei.shopcar;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ShopCarBean {

    @Id
    private long id;
    private String name;
    private String path;
    private String money;
    @Generated(hash = 677068367)
    public ShopCarBean(long id, String name, String path, String money) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.money = money;
    }
    @Generated(hash = 804662094)
    public ShopCarBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getMoney() {
        return this.money;
    }
    public void setMoney(String money) {
        this.money = money;
    }

}
