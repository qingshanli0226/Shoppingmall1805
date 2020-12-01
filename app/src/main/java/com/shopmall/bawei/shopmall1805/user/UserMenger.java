package com.shopmall.bawei.shopmall1805.user;

public class UserMenger {
    String token;
    public static UserMenger menger=new UserMenger();
    public static UserMenger getInstance(){
        return menger;
    }
    public UserMenger() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static UserMenger getMenger() {
        return menger;
    }

    public static void setMenger(UserMenger menger) {
        UserMenger.menger = menger;
    }
}
