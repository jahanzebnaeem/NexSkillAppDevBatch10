package com.example.android.localdbsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

public class DBManager {
    private SQLiteDatabase sqlDB;

    static final String DBName = "Students";
    static final String TableName = "Logins";
    static final String ColID = "ID";
    static final String ColUserName = "UserName";
    static final String ColPassWord = "Password";
    static final int DBVersion = 1;

    // Create table Logins(ID integer primary key autoincrement, UserName text, Password text)
    static final String CreateTable = "CREATE TABLE IF NOT EXISTS " + TableName +
            " (" + ColID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ColUserName +
            " TEXT, " + ColPassWord + " TEXT);";

    static class DatabaseHelperUser extends SQLiteOpenHelper {
        Context context;

        DatabaseHelperUser (Context context) {
            super(context, DBName, null, DBVersion);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateTable);
            Toast.makeText(context, "Table is created", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop table IF EXISTS " + TableName);
            onCreate(db);
        }
    }

    public DBManager(Context context) {
        DatabaseHelperUser db = new DatabaseHelperUser(context);
        sqlDB = db.getWritableDatabase();
    }

    public long Insert(ContentValues values) {
        long ID = sqlDB.insert(TableName, "", values);
        // could insert id is user id, or fail id is equal 0
        return ID;
    }

    // select username, password from logins where ID = 1
    public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder){
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(TableName);

        Cursor cursor = sqLiteQueryBuilder.query(sqlDB, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }
}
