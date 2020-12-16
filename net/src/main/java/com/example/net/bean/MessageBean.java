package com.example.net.bean;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageBean {

    /**
     * code : 200
     * message : 请求成功
     * result : beijing haidian
     */

    private String code;
    private String message;




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


}
