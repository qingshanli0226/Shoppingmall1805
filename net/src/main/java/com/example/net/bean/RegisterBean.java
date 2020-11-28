package com.example.net.bean;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterBean {


    /**
     * code : 200
     * message : 请求成功
     * result : 注册成功
     */

    private String code;
    private String message;
    private String result;

    public static RegisterBean objectFromData(String str) {

        return new Gson().fromJson(str, RegisterBean.class);
    }

    public static RegisterBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), RegisterBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
