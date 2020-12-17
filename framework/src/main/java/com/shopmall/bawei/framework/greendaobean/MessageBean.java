package com.shopmall.bawei.framework.greendaobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class MessageBean {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String msg;
    private String url;
    private String prompt;
    private boolean read;
    private String date;
    @Generated(hash = 1131926498)
    public MessageBean(Long id, String title, String msg, String url, String prompt,
            boolean read, String date) {
        this.id = id;
        this.title = title;
        this.msg = msg;
        this.url = url;
        this.prompt = prompt;
        this.read = read;
        this.date = date;
    }
    @Generated(hash = 1588632019)
    public MessageBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMsg() {
        return this.msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getPrompt() {
        return this.prompt;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    public boolean getRead() {
        return this.read;
    }
    public void setRead(boolean read) {
        this.read = read;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    
}
