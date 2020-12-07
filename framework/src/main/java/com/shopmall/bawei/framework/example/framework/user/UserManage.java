package com.shopmall.bawei.shopmall1805.user;

import android.content.SharedPreferences;

public class UserManage {

    String token;
    public static UserManage userManager = new UserManage();

    private SharedPreferences sharedPreferences;

    public static UserManage getInstance(){
        return userManager;
    }

    public UserManage(){

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static UserManage getUserManager() {
        return userManager;
    }

    public static void setUserManager(UserManage userManager) {
        UserManage.userManager = userManager;
    }
}
