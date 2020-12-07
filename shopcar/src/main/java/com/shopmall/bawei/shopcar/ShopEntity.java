package com.shopmall.bawei.shopcar;

public class ShopEntity {
    private String name;
    private String path;
    private String money;

    public ShopEntity(String name, String path, String money) {
        this.name = name;
        this.path = path;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
