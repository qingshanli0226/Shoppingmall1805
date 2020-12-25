package framework.messagegreendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public
class ShopMessageGreenBean {
    @Id(autoincrement = true)
    Long id;

    int type;
    String tlite;
    String body;

    String time;
    boolean isRead;

    String reserveZiOne;
    String reserveZiTow;
    @Generated(hash = 1188294980)
    public ShopMessageGreenBean(Long id, int type, String tlite, String body,
            String time, boolean isRead, String reserveZiOne, String reserveZiTow) {
        this.id = id;
        this.type = type;
        this.tlite = tlite;
        this.body = body;
        this.time = time;
        this.isRead = isRead;
        this.reserveZiOne = reserveZiOne;
        this.reserveZiTow = reserveZiTow;
    }
    @Generated(hash = 1814083260)
    public ShopMessageGreenBean() {
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
    public String getTlite() {
        return this.tlite;
    }
    public void setTlite(String tlite) {
        this.tlite = tlite;
    }
    public String getBody() {
        return this.body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public boolean getIsRead() {
        return this.isRead;
    }
    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
    public String getReserveZiOne() {
        return this.reserveZiOne;
    }
    public void setReserveZiOne(String reserveZiOne) {
        this.reserveZiOne = reserveZiOne;
    }
    public String getReserveZiTow() {
        return this.reserveZiTow;
    }
    public void setReserveZiTow(String reserveZiTow) {
        this.reserveZiTow = reserveZiTow;
    }
   
    
}
