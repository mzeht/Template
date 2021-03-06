package com.wpmac.template.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "EX_COMPANY".
*/
public class ExCompanyDao extends AbstractDao<ExCompany, Long> {

    public static final String TABLENAME = "EX_COMPANY";

    /**
     * Properties of entity ExCompany.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property No = new Property(2, String.class, "no", false, "NO");
        public final static Property Logo = new Property(3, String.class, "logo", false, "LOGO");
        public final static Property IsDefault = new Property(4, boolean.class, "isDefault", false, "IS_DEFAULT");
    }


    public ExCompanyDao(DaoConfig config) {
        super(config);
    }
    
    public ExCompanyDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"EX_COMPANY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"NO\" TEXT," + // 2: no
                "\"LOGO\" TEXT," + // 3: logo
                "\"IS_DEFAULT\" INTEGER NOT NULL );"); // 4: isDefault
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"EX_COMPANY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ExCompany entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String no = entity.getNo();
        if (no != null) {
            stmt.bindString(3, no);
        }
 
        String logo = entity.getLogo();
        if (logo != null) {
            stmt.bindString(4, logo);
        }
        stmt.bindLong(5, entity.getIsDefault() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ExCompany entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String no = entity.getNo();
        if (no != null) {
            stmt.bindString(3, no);
        }
 
        String logo = entity.getLogo();
        if (logo != null) {
            stmt.bindString(4, logo);
        }
        stmt.bindLong(5, entity.getIsDefault() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ExCompany readEntity(Cursor cursor, int offset) {
        ExCompany entity = new ExCompany( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // no
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // logo
            cursor.getShort(offset + 4) != 0 // isDefault
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ExCompany entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNo(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLogo(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIsDefault(cursor.getShort(offset + 4) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ExCompany entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ExCompany entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ExCompany entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
