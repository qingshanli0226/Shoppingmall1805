package com.shopmall.bawei.shopmall1805;

import android.content.Context;

import java.util.List;

public class MyGreenManager {

    private static MyGreenManager myGreenManager;
    public MyGreenManager() {
    }


    public static MyGreenManager getMyGreenManager(){
        if (myGreenManager == null){
            myGreenManager = new MyGreenManager();
        }
        return myGreenManager;
    }

    private GreenDaoBeanDao greenDaoBeanDao;

    public GreenDaoBeanDao getGreenDaoBeanDao() {
        return greenDaoBeanDao;
    }

    public void setGreenDaoBeanDao(GreenDaoBeanDao greenDaoBeanDao) {
        this.greenDaoBeanDao = greenDaoBeanDao;
    }

    public long putData(GreenDaoBean greenDaoBean){
        return greenDaoBeanDao.insert(greenDaoBean);
    }

    public void deleteData(GreenDaoBean greenDaoBean){
        Void key = greenDaoBeanDao.getKey(greenDaoBean);
        greenDaoBeanDao.deleteByKey(key);
    }

    public void deleteAll(){
        greenDaoBeanDao.deleteAll();
    }

    public List<GreenDaoBean> getListAll(){
        return greenDaoBeanDao.loadAll();
    }
}
