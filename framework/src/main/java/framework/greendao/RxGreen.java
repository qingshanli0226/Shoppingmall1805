package framework.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public
class RxGreen {

    private   String dbname ;
    private static RxGreen rxGreen = null;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public RxGreen(String name, Context context) {
        this.dbname = name;
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context,dbname,null);
    }
    
    public static  RxGreen getInstance(Context context1,String name){
        if (rxGreen  == null){
            synchronized (RxGreen.class){
                if (rxGreen  == null){
                    rxGreen = new RxGreen(name,context1);
                }
            }
        }
        return rxGreen;
    }
    
    private SQLiteDatabase getWritableDataBase(){
        if (openHelper==null){
            openHelper = new DaoMaster.DevOpenHelper(context,dbname,null);
        }
        SQLiteDatabase writableDatabase = openHelper.getWritableDatabase();

        return writableDatabase;
    }

    public void  insertUser(usernv usernv){
        new DaoMaster(getWritableDataBase())
                .newSession()
                .getUsernvDao()
                .insert(usernv);
    }

    public void delete(usernv usernv){
        new DaoMaster(getWritableDataBase())
                .newSession()
                .getUsernvDao()
                .delete(usernv);
    }

    public  void updataUser(usernv usernv){
        new DaoMaster(getWritableDataBase())
                .newSession()
                .getUsernvDao()
                .update(usernv);
    }

    public List<usernv> SeekAllUser(){
        QueryBuilder<usernv> usernvQueryBuilder = new DaoMaster(getWritableDataBase())
                .newSession()
                .getUsernvDao()
                .queryBuilder();
        List<usernv> list = usernvQueryBuilder.list();
        return list;
    }
    
}
