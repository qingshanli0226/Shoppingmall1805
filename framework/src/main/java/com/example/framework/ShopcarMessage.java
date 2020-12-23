package com.example.framework;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ShopcarMessage {

    @Id(autoincrement = true)
    Long id;
    int type;
    String title;
    String body;
    long time;
    boolean isRead;
    @Generated(hash = 1118226086)
    public ShopcarMessage(Long id, int type, String title, String body, long time,
            boolean isRead) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.body = body;
        this.time = time;
        this.isRead = isRead;
    }
    @Generated(hash = 523320383)
    public ShopcarMessage() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return this.body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public boolean getIsRead() {
        return this.isRead;
    }
    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
}
