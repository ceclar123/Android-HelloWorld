package org.bond.hello.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by Administrator on 2015-7-13.
 */
public class StudentContentProvider extends ContentProvider {
    private DBHelper dbHelper = null;

    //自定义匹配码
    public static final int STUDENTS = 1;
    public static final int STUDENT = 2;
    public static final Uri STUDENTS_URI = Uri.parse("content://" + Students.AUTHORITY + "/student");

    //UriMatcher类用来匹配Uri，使用match()方法匹配路径时返回匹配码
    private static final UriMatcher uriMatcher;

    static {
        //常量UriMatcher.NO_MATCH表示不匹配任何路径的返回码
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Students.AUTHORITY, "student", STUDENTS);
        uriMatcher.addURI(Students.AUTHORITY, "student/#", STUDENT);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(this.getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = 0;
        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                id = db.insert("student", "name", values);// 返回的是记录的行号，主键为int，实际上就是主键值
                return ContentUris.withAppendedId(uri, id);
            case STUDENT:
                id = db.insert("student", "name", values);
                String path = uri.toString();
                return Uri.parse(path.substring(0, path.lastIndexOf("/")) + id); // 替换掉id
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                count = db.delete("student", selection, selectionArgs);
                break;
            case STUDENT:
                // 下面的方法用于从URI中解析出id，对这样的路径content://com.ljq.provider.studentprovider/student/10
                // 进行解析，返回值为10
                long studentId = ContentUris.parseId(uri);
                String where = "id=" + studentId;// 删除指定id的记录
                where += !TextUtils.isEmpty(selection) ? " and (" + selection + ")" : "";// 把其它条件附加上
                count = db.delete("student", where, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        db.close();
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                count = db.update("student", values, selection, selectionArgs);
                break;
            case STUDENT:
                // 下面的方法用于从URI中解析出id，对这样的路径content://xxx.xxx/student/10
                // 进行解析，返回值为10
                long studentId = ContentUris.parseId(uri);
                String where = "id=" + studentId;// 获取指定id的记录
                where += !TextUtils.isEmpty(selection) ? " and (" + selection + ")" : "";// 把其它条件附加上
                count = db.update("student", values, where, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        db.close();
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                return Students.CONTENT_TYPE;
            case STUDENT:
                return Students.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                return db.query("student", projection, selection, selectionArgs, null, null, sortOrder);
            case STUDENT:
                // 下面的方法用于从URI中解析出id，对这样的路径content://xxx.xxx.xxx/student/10
                // 进行解析，返回值为10
                long studentId = ContentUris.parseId(uri);
                String where = "id=" + studentId;// 获取指定id的记录
                where += !TextUtils.isEmpty(selection) ? " and (" + selection + ")" : "";// 把其它条件附加上
                return db.query("student", projection, where, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }
}
