package com.example.usr.mymoney.DataBase;

/**
 * Created by usr on 10.11.2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.usr.mymoney.FinanceObject;
import com.example.usr.mymoney.Section;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "my_tag";

    public static final String TABLE_INCOME = "income";

    public static final String KEY_INCOME_ID = "_id1";
    public static final String KEY_INCOME_NAME = "name";
    public static final String KEY_INCOME_AMOUNT = "amount";
    public static final String KEY_INCOME_DATE = "date";

    public static final String TABLE_SPENDING = "spending";

    public static final String KEY_SPENDING_ID = "_id2";
    public static final String KEY_SPENDING_NAME = "name";
    public static final String KEY_SPENDING_AMOUNT = "amount";
    public static final String KEY_SPENDING_DATE = "date";

    public static final String TABLE_PLANING = "planing";

    public static final String KEY_PLANING_ID = "_id3";
    public static final String KEY_PLANING_NAME = "name";
    public static final String KEY_PLANING_AMOUNT = "amount";
    public static final String KEY_PLANING_DATE = "date";

    public static final String TABLE_SECTION_INCOME = "section_income";

    public static final String KEY_SECTION_INCOME_ID = "_id";
    public static final String KEY_SECTION_INCOME_NAME = "section_name";
    public static final String KEY_SECTION_INCOME_IMG_ID = "section_img_id";
    public static final String KEY_SECTION_INCOME_AMOUNT = "section_amount";

    public static final String TABLE_SECTION_SPENDING = "section_spending";

    public static final String KEY_SECTION_SPENDING_ID = "_id";
    public static final String KEY_SECTION_SPENDING_NAME = "section_name";
    public static final String KEY_SECTION_SPENDING_IMG_ID = "section_img_id";
    public static final String KEY_SECTION_SPENDING_AMOUNT = "section_amount";

    public static final String TABLE_SECTION_PLANING = "section_planing";

    public static final String KEY_SECTION_PLANING_ID = "_id";
    public static final String KEY_SECTION_PLANING_NAME = "section_name";
    public static final String KEY_SECTION_PLANING_IMG_ID = "section_img_id";
    public static final String KEY_SECTION_PLANING_AMOUNT = "section_amount";

    private static final String DATABASE_NAME = "mymoneyDB";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_INCOME + "("
                + KEY_INCOME_ID + " integer primary key autoincrement, "
                + KEY_INCOME_NAME + " text, "
                + KEY_INCOME_AMOUNT + " text, "
                + KEY_INCOME_DATE + " text" + ");");

        db.execSQL("create table " + TABLE_SPENDING + "("
                + KEY_SPENDING_ID + " integer primary key autoincrement, "
                + KEY_SPENDING_NAME + " text, "
                + KEY_SPENDING_AMOUNT + " text, "
                + KEY_SPENDING_DATE + " text" + ");");

        db.execSQL("create table " + TABLE_PLANING + "("
                + KEY_PLANING_ID + " integer primary key autoincrement, "
                + KEY_PLANING_NAME + " text, "
                + KEY_PLANING_AMOUNT + " text, "
                + KEY_PLANING_DATE + " text" + ");");

        db.execSQL("create table " + TABLE_SECTION_INCOME + "("
                + KEY_SECTION_INCOME_ID + " integer primary key autoincrement, "
                + KEY_SECTION_INCOME_NAME + " text, "
                + KEY_SECTION_INCOME_IMG_ID + " text, "
                + KEY_SECTION_INCOME_AMOUNT + " text" + ");");

        db.execSQL("create table " + TABLE_SECTION_SPENDING + "("
                + KEY_SECTION_SPENDING_ID + " integer primary key autoincrement, "
                + KEY_SECTION_SPENDING_NAME + " text, "
                + KEY_SECTION_SPENDING_IMG_ID + " text, "
                + KEY_SECTION_SPENDING_AMOUNT + " text" + ");");

        db.execSQL("create table " + TABLE_SECTION_PLANING + "("
                + KEY_SECTION_PLANING_ID + " integer primary key autoincrement, "
                + KEY_SECTION_PLANING_NAME + " text, "
                + KEY_SECTION_PLANING_IMG_ID + " text, "
                + KEY_SECTION_PLANING_AMOUNT + " text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPENDING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTION_INCOME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTION_SPENDING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTION_PLANING);
        this.onCreate(db);
    }

    public void addToIncome(FinanceObject financeObject) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_INCOME_DATE, financeObject.getDate());
        values.put(KEY_INCOME_AMOUNT, financeObject.getAmount());
        values.put(KEY_INCOME_NAME, financeObject.getNameObj());

        db.insert(TABLE_INCOME, null, values);
        db.close();
    }

    public void addToSpending(FinanceObject financeObject) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_SPENDING_NAME, financeObject.getNameObj());
        values.put(KEY_SPENDING_AMOUNT, financeObject.getAmount());
        values.put(KEY_SPENDING_DATE, financeObject.getDate());

        db.insert(TABLE_SPENDING, null, values);
        db.close();
    }

    /*public void addToPlaning(FinanceObject financeObject) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_PLANING_NAME, financeObject.getNameObj());
        values.put(KEY_PLANING_AMOUNT, financeObject.getAmount());
        values.put(KEY_PLANING_DATE, financeObject.getDate());

        db.insert(TABLE_PLANING, null, values);
        db.close();
    }*/

    public List<String> getAllDate(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> allDate = new ArrayList<>();
        Cursor cursor;
        switch (i) {
            case 1:
                cursor = db.query(TABLE_SPENDING, new String[]{KEY_SPENDING_DATE},
                        null, null, KEY_SPENDING_DATE, null, KEY_SPENDING_DATE);
                if (cursor.moveToLast()) {
                    do {
                        allDate.add(cursor.getString(0));
                    } while (cursor.moveToPrevious());
                }
                break;
            case 2:
                cursor = db.query(TABLE_INCOME, new String[]{KEY_INCOME_DATE},
                        null, null, KEY_INCOME_DATE, null, KEY_INCOME_DATE);
                if (cursor.moveToLast()) {
                    do {
                        allDate.add(cursor.getString(0));
                    } while (cursor.moveToPrevious());
                }
                break;
        }
        db.close();
        return allDate;
    }

    public List<Section> getTotalAmount(String date, int pageNumber) {

        List<Section> sectionList = new ArrayList<Section>();
        String[] columns = null;
        String groupByInc = null;
        String havingInc = null;
        String groupBySpend = null;
        String havingSpend = null;

        SQLiteDatabase db = this.getWritableDatabase();
        switch (pageNumber) {
            case 1:
                columns = new String[]{KEY_SPENDING_NAME, "SUM(amount) AS amount"};
                groupBySpend = KEY_SPENDING_NAME + ", " + KEY_SPENDING_DATE;
                havingSpend = KEY_SPENDING_DATE + " = " + date;
                Cursor cursorSpending = db.query(TABLE_SPENDING, columns, null, null, groupBySpend, havingSpend, null);
                if (cursorSpending.moveToFirst()) {
                    do {
                        Section section = new Section();
                        section.setNameSection(cursorSpending.getString(0));
                        section.setAmount(cursorSpending.getString(1));

                        sectionList.add(section);
                    } while (cursorSpending.moveToNext());
                }
                break;
            case 2:
                columns = new String[]{KEY_INCOME_NAME, "SUM(amount) AS amount"};
                groupByInc = KEY_INCOME_NAME + ", " + KEY_INCOME_DATE;
                havingInc = KEY_INCOME_DATE + " = " + date;
                Cursor cursorIncome = db.query(TABLE_INCOME, columns, null, null, groupByInc, havingInc, null);
                if (cursorIncome.moveToFirst()) {
                    do {
                        Section section = new Section();
                        section.setNameSection(cursorIncome.getString(0));
                        section.setAmount(cursorIncome.getString(1));

                        sectionList.add(section);
                    } while (cursorIncome.moveToNext());
                }
                break;
        }
        db.close();
        return sectionList;
    }

    public void deleteIncomeObject(FinanceObject financeObject) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INCOME, KEY_INCOME_NAME + " = ? AND "
                        + KEY_INCOME_AMOUNT + " = ? AND " + KEY_INCOME_DATE + " = ? ",
                new String[]{financeObject.getNameObj(), String.valueOf(financeObject.getAmount()),
                        financeObject.getDate()});
        db.close();
    }

    public void deleteSpendingObject(FinanceObject financeObject) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SPENDING, KEY_SPENDING_NAME + " = ? AND "
                        + KEY_SPENDING_AMOUNT + " = ? AND " + KEY_SPENDING_DATE + " = ? ",
                new String[]{financeObject.getNameObj(), String.valueOf(financeObject.getAmount()),
                        financeObject.getDate()});
        db.close();
    }

    public void deletePlaningObject(FinanceObject financeObject) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLANING, KEY_PLANING_NAME + " = ? AND "
                        + KEY_PLANING_AMOUNT + " = ? AND " + KEY_PLANING_DATE + " = ? ",
                new String[]{financeObject.getNameObj(), String.valueOf(financeObject.getAmount()),
                        financeObject.getDate()});
        db.close();
    }

    public List<Section> getAllSectionIncome() {
        List<Section> sectionList = new ArrayList<Section>();
        String query = "select * from " + TABLE_SECTION_INCOME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Section section = new Section();
                section.setSectionId(cursor.getInt(0));
                section.setNameSection(cursor.getString(1));
                section.setImageId(cursor.getInt(2));
                section.setAmount(cursor.getString(3));

                sectionList.add(section);
            } while (cursor.moveToNext());
        }
        return sectionList;
    }

    public List<Section> getAllSectionSpending() {
        List<Section> sectionList = new ArrayList<Section>();
        String query = "select * from " + TABLE_SECTION_SPENDING;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Section section = new Section();
                section.setSectionId(cursor.getInt(0));
                section.setNameSection(cursor.getString(1));
                section.setImageId(cursor.getInt(2));
                section.setAmount(cursor.getString(3));

                sectionList.add(section);
            } while (cursor.moveToNext());
        }
        return sectionList;
    }

    public List<Section> getAllSectionPlaning() {
        List<Section> sectionList = new ArrayList<Section>();
        String query = "select * from " + TABLE_SECTION_PLANING;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Section section = new Section();
                section.setSectionId(cursor.getInt(0));
                section.setNameSection(cursor.getString(1));
                section.setImageId(cursor.getInt(2));
                section.setAmount(cursor.getString(3));

                sectionList.add(section);
            } while (cursor.moveToNext());
        }
        return sectionList;
    }

    public void addIncomeSection(Section section) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_SECTION_INCOME_NAME, section.getNameSection());
        values.put(KEY_SECTION_INCOME_IMG_ID, section.getImageId());
        values.put(KEY_SECTION_INCOME_AMOUNT, section.getAmount());

        db.insert(TABLE_SECTION_INCOME, null, values);
        db.close();
    }

    public void addSpendingSection(Section section) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_SECTION_SPENDING_NAME, section.getNameSection());
        values.put(KEY_SECTION_SPENDING_IMG_ID, section.getImageId());
        values.put(KEY_SECTION_SPENDING_AMOUNT, section.getAmount());

        db.insert(TABLE_SECTION_SPENDING, null, values);
        db.close();
    }

    public void addPlaningSection(Section section) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_SECTION_PLANING_NAME, section.getNameSection());
        values.put(KEY_SECTION_PLANING_IMG_ID, section.getImageId());
        values.put(KEY_SECTION_PLANING_AMOUNT, section.getAmount());

        db.insert(TABLE_SECTION_PLANING, null, values);
        db.close();
    }

    public void deleteIncomeSection(String nameSection) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INCOME, KEY_INCOME_NAME + " = ? ", new String[]{nameSection});
        db.delete(TABLE_SECTION_INCOME, KEY_SECTION_INCOME_NAME + " = ? ", new String[]{nameSection});
        db.close();
    }

    public void deleteSpendingSection(String nameSection) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SPENDING, KEY_SPENDING_NAME + " = ? ", new String[]{nameSection});
        db.delete(TABLE_SECTION_SPENDING, KEY_SECTION_SPENDING_NAME + " = ? ", new String[]{nameSection});
        db.close();
    }

    public void deletePlaningSection(String nameSection) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLANING, KEY_PLANING_NAME + " = ? ", new String[]{nameSection});
        db.delete(TABLE_SECTION_PLANING, KEY_SECTION_PLANING_NAME + " = ? ", new String[]{nameSection});
        db.close();
    }

    public void updateIncomeSection(String oldName, String newName) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_SECTION_INCOME_NAME, newName);
        db.update(TABLE_SECTION_INCOME, values, KEY_SECTION_INCOME_NAME + " = ? ", new String[]{oldName});
        values.clear();
        values.put(KEY_INCOME_NAME, newName);
        db.update(TABLE_INCOME, values, KEY_INCOME_NAME + " = ? ", new String[]{oldName});
        db.close();

    }

    public void updateSpendingSection(String oldName, String newName) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_SECTION_SPENDING_NAME, newName);
        db.update(TABLE_SECTION_SPENDING, values, KEY_SECTION_SPENDING_NAME + " = ? ", new String[]{oldName});
        values.clear();
        values.put(KEY_SPENDING_NAME, newName);
        db.update(TABLE_SPENDING, values, KEY_SPENDING_NAME + " = ? ", new String[]{oldName});
        db.close();

    }

    public void updatePlaningSection(String oldName, Section newSection) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_SECTION_PLANING_NAME, newSection.getNameSection());
        values.put(KEY_SECTION_PLANING_AMOUNT, newSection.getAmount());
        db.update(TABLE_SECTION_PLANING, values, KEY_SECTION_PLANING_ID + "=" + newSection.getSectionId(), null);

        values.clear();
        values.put(KEY_PLANING_NAME, newSection.getNameSection());
        db.update(TABLE_PLANING, values, KEY_PLANING_NAME + " = ? ", new String[]{oldName});
        db.close();

    }

    public void updateIncomeObject(FinanceObject financeObject) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.close();

    }

    public void updateSpendingObject(FinanceObject financeObject) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.close();
    }

    public void updatePlaningObject(FinanceObject financeObject) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.close();
    }
}