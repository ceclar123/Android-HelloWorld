package org.bond.hello.provider;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015-7-13.
 */
public class DBHelper extends SQLiteOpenHelper {
    /**
     * 数据库名称
     */
    private static final String DATABASE_NAME = "HelloWorld.db";

    /**
     * 数据库版本
     */
    private static final int DATABASE_VERSION = 5;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) throws SQLException {
        //创建表格
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Students.TABLE_NAME + "(" + Students.Student._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Students.Student.NMAE + " VARCHAR NOT NULL," +
                Students.Student.GENDER + " VARCHAR NOT NULL," +
                Students.Student.AGE + " INTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLException {
        //删除并创建表格
        db.execSQL("DROP TABLE IF EXISTS " + Students.TABLE_NAME + ";");
        onCreate(db);
    }
}
