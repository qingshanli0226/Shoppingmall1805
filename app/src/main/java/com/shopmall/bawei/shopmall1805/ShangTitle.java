package com.shopmall.bawei.shopmall1805;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ShangTitle {

    int type;
    String title;
    String body;
    long time;
    boolean isRead;
    String reserveOne;//预先保留两个字段
    String reserveTwo;
    @Generated(hash = 1118227197)
    public ShangTitle(int type, String title, String body, long time,
            boolean isRead, String reserveOne, String reserveTwo) {
        this.type = type;
        this.title = title;
        this.body = body;
        this.time = time;
        this.isRead = isRead;
        this.reserveOne = reserveOne;
        this.reserveTwo = reserveTwo;
    }
    @Generated(hash = 437954575)
    public ShangTitle() {
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
    public String getReserveOne() {
        return this.reserveOne;
    }
    public void setReserveOne(String reserveOne) {
        this.reserveOne = reserveOne;
    }
    public String getReserveTwo() {
        return this.reserveTwo;
    }
    public void setReserveTwo(String reserveTwo) {
        this.reserveTwo = reserveTwo;
    }

}
