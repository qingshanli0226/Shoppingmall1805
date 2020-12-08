package com.shopmall.bawei.net.mode;

public class LoginBean {

    @Override
    public String toString() {
        return "LoginBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email=" + email +
                ", phone=" + phone +
                ", point=" + point +
                ", address=" + address +
                ", money=" + money +
                ", avatar='" + avatar + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    /**
     * id : lkkji1234
     * name : lkkji1234
     * password : 123456
     * email : null
     * phone : null
     * point : null
     * address : null
     * money : null
     * avatar : /img/1438946011155.jpg
     * token : 7165a1b7-63be-46ea-9b1c-086be9f22c5dAND1606689169588
     */


    private String id;
    private String name;
    private String password;
    private Object email;
    private Object phone;
    private Object point;
    private Object address;
    private Object money;
    private String avatar;
    private String token;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getPoint() {
        return point;
    }

    public void setPoint(Object point) {
        this.point = point;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getMoney() {
        return money;
    }

    public void setMoney(Object money) {
        this.money = money;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
