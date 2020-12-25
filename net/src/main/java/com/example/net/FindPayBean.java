package com.example.net;

public class FindPayBean {
    /**
     * subject : buy
     * body : ????
     * totalPrice : 0.0
     * time : 1607513971422
     * status : null
     * tradeNo : 1209193931-6021
     * orderInfo : a                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.0%22%2C%22subject%22%3A%22buy%22%2C%22body%22%3A%22%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%221209193931-6021%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2020-12-09+19%3A39%3A31&version=1.0&sign=GCkNmKjW97Cnf0u1pIQe573JleZl8hQdlAp%2B8%2BLAM9w9Vm3Pg4msWmG%2FBnb8ACpD9vp8KuUkP44XV9bN2ReyKITTR59IaWXdgS8BpZAI2D1rnBc9mZqkVUl%2F%2FmDuUsSUiOjxASjcGG88BxFi55hNWVOI9WZmn2qtF4dFh3z6LLpAparQoRuJnZYe4f9tkiEkYRKY72nk%2BPtbzmYY%2F3p7d8TCrv%2BO0wG2XXN6vbMic5Ei403bBQGlecOFRBofD42%2F50ZBbfiSQD4wTnINeE3px4lJ9q%2FkRgEYlvw58JQ8sZNibJDM8393HduU2sRz3tv2rHty4%2Ffzn05ktJ0XNCOP8Q%3D%3D
     */

    private String subject;
    private String body;
    private String totalPrice;
    private String time;
    private Object status;
    private String tradeNo;
    private String orderInfo;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }
}
