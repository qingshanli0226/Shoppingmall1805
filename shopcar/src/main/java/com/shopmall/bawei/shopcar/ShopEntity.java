package com.shopmall.bawei.shopcar;

public class ShopEntity {
    private Boolean isChecked;
    private Boolean isVisibility;
    private String name;
    private String path;
    private String money;

    public ShopEntity(String name, String path, String money) {
        this.name = name;
        this.path = path;
        this.money = money;
    }

    public Boolean getVisibility() {
        return isVisibility;
    }

    public void setVisibility(Boolean visibility) {
        isVisibility = visibility;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
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
