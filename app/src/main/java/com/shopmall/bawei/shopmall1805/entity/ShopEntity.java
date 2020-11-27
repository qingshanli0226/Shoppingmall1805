package com.shopmall.bawei.shopmall1805.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ShopEntity {
    @Id
    private long id;
    private String name;
    private String path;
    private String money;
    @Generated(hash = 651872520)
    public ShopEntity(long id, String name, String path, String money) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.money = money;
    }
    @Generated(hash = 253351364)
    public ShopEntity() {
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
