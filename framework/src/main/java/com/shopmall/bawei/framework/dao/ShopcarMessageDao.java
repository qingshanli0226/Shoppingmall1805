package com.shopmall.bawei.framework.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SHOPCAR_MESSAGE".
*/
public class ShopcarMessageDao extends AbstractDao<ShopcarMessage, Long> {

    public static final String TABLENAME = "SHOPCAR_MESSAGE";

    /**
     * Properties of entity ShopcarMessage.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Type = new Property(1, int.class, "type", false, "TYPE");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Body = new Property(3, String.class, "body", false, "BODY");
        public final static Property Time = new Property(4, long.class, "time", false, "TIME");
        public final static Property IsRead = new Property(5, boolean.class, "isRead", false, "IS_READ");
        public final static Property ReserveOne = new Property(6, String.class, "reserveOne", false, "RESERVE_ONE");
        public final static Property ReserveTwo = new Property(7, String.class, "reserveTwo", false, "RESERVE_TWO");
    }


    public ShopcarMessageDao(DaoConfig config) {
        super(config);
    }
    
    public ShopcarMessageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SHOPCAR_MESSAGE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TYPE\" INTEGER NOT NULL ," + // 1: type
                "\"TITLE\" TEXT," + // 2: title
                "\"BODY\" TEXT," + // 3: body
                "\"TIME\" INTEGER NOT NULL ," + // 4: time
                "\"IS_READ\" INTEGER NOT NULL ," + // 5: isRead
                "\"RESERVE_ONE\" TEXT," + // 6: reserveOne
                "\"RESERVE_TWO\" TEXT);"); // 7: reserveTwo
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SHOPCAR_MESSAGE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ShopcarMessage entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getType());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String body = entity.getBody();
        if (body != null) {
            stmt.bindString(4, body);
        }
        stmt.bindLong(5, entity.getTime());
        stmt.bindLong(6, entity.getIsRead() ? 1L: 0L);
 
        String reserveOne = entity.getReserveOne();
        if (reserveOne != null) {
            stmt.bindString(7, reserveOne);
        }
 
        String reserveTwo = entity.getReserveTwo();
        if (reserveTwo != null) {
            stmt.bindString(8, reserveTwo);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ShopcarMessage entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getType());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String body = entity.getBody();
        if (body != null) {
            stmt.bindString(4, body);
        }
        stmt.bindLong(5, entity.getTime());
        stmt.bindLong(6, entity.getIsRead() ? 1L: 0L);
 
        String reserveOne = entity.getReserveOne();
        if (reserveOne != null) {
            stmt.bindString(7, reserveOne);
        }
 
        String reserveTwo = entity.getReserveTwo();
        if (reserveTwo != null) {
            stmt.bindString(8, reserveTwo);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ShopcarMessage readEntity(Cursor cursor, int offset) {
        ShopcarMessage entity = new ShopcarMessage( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // type
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // body
            cursor.getLong(offset + 4), // time
            cursor.getShort(offset + 5) != 0, // isRead
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // reserveOne
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // reserveTwo
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ShopcarMessage entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setType(cursor.getInt(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setBody(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTime(cursor.getLong(offset + 4));
        entity.setIsRead(cursor.getShort(offset + 5) != 0);
        entity.setReserveOne(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setReserveTwo(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ShopcarMessage entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ShopcarMessage entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ShopcarMessage entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
