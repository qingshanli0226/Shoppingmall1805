package com.shopmall.bean;

import java.io.Serializable;

public class DetailsData implements Serializable {
    private String id;
     private String name;
     private String pice;
     private String image;


    public DetailsData(String id, String name, String pice, String image) {
        this.id = id;
        this.name = name;
        this.pice = pice;
        this.image = image;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPice() {
        return pice;
    }

    public void setPice(String pice) {
        this.pice = pice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
