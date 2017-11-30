package com.example.usr.mymoney.DataBase;

/**
 * Created by usr on 10.11.2017.
 */
/*import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "my_tag";

    public static final String TABLE_NAME = "friends";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    private static final String DATABASE_NAME = "friendsDB";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_NAME + " text,"
                + KEY_EMAIL + " text" + ");");

        ContentValues cv = new ContentValues();

        cv.put(KEY_NAME, "Igor");
        cv.put(KEY_EMAIL, "email1@email.com");
        db.insert(TABLE_NAME, null, cv);

        cv.put(KEY_NAME, "Dima");
        cv.put(KEY_EMAIL, "email2@email.com");
        db.insert(TABLE_NAME, null, cv);

        cv.put(KEY_NAME, "Alex");
        cv.put(KEY_EMAIL, "email3@email.com");
        db.insert(TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

}*/