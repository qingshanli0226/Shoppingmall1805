package framework.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public
class usernv {
    @Id
    private long id;
    private String name;
    private  String price;
    private String url;
    private int ge;
    @Generated(hash = 1512047358)
    public usernv(long id, String name, String price, String url, int ge) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.url = url;
        this.ge = ge;
    }
    @Generated(hash = 1862983422)
    public usernv() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getGe() {
        return this.ge;
    }
    public void setGe(int ge) {
        this.ge = ge;
    }

}
