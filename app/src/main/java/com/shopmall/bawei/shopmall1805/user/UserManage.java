package com.shopmall.bawei.shopmall1805.user;

public class UserManager {

    String token;
    public static UserManager userManager = new UserManager();
    public static UserManager getInstance(){
        return userManager;
    }

    public UserManager(){

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static UserManager getUserManager() {
        return userManager;
    }

    public static void setUserManager(UserManager userManager) {
        UserManager.userManager = userManager;
    }
}
