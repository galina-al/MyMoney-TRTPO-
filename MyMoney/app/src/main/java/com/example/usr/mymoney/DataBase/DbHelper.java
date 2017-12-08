package com.example.usr.mymoney.DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.usr.mymoney.Entity.FinanceObject;
import com.example.usr.mymoney.Entity.Section;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    //region all Const
    public static final String TABLE_CURRENT_COUNT = "current_count";
    public static final String KEY_CURRENT_COUNT_ID = "id";
    public static final String KEY_CURRENT_COUNT = "current_count";

    public static final String TABLE_INCOME = "income";

    public static final String KEY_INCOME_ID = "_id1";
    public static final String KEY_INCOME_IMG_ID = "imgId";
    public static final String KEY_INCOME_NAME = "name";
    public static final String KEY_INCOME_AMOUNT = "amount";
    public static final String KEY_INCOME_DATE = "date";
    public static final String KEY_INCOME_DAY = "day";

    public static final String TABLE_SPENDING = "spending";

    public static final String KEY_SPENDING_ID = "_id2";
    public static final String KEY_SPENDING_IMG_ID = "imgId";
    public static final String KEY_SPENDING_NAME = "name";
    public static final String KEY_SPENDING_AMOUNT = "amount";
    public static final String KEY_SPENDING_DATE = "date";
    public static final String KEY_SPENDING_DAY = "day";


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
    //endregion

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_CURRENT_COUNT + "("
                + KEY_CURRENT_COUNT_ID + " integer primary key autoincrement, "
                + KEY_CURRENT_COUNT + " text" + ");");

        db.execSQL("create table " + TABLE_INCOME + "("
                + KEY_INCOME_ID + " integer primary key autoincrement, "
                + KEY_INCOME_IMG_ID + " text, "
                + KEY_INCOME_NAME + " text, "
                + KEY_INCOME_AMOUNT + " text, "
                + KEY_INCOME_DATE + " text, "
                + KEY_INCOME_DAY + " text" + ");");

        db.execSQL("create table " + TABLE_SPENDING + "("
                + KEY_SPENDING_ID + " integer primary key autoincrement, "
                + KEY_SPENDING_IMG_ID + " text, "
                + KEY_SPENDING_NAME + " text, "
                + KEY_SPENDING_AMOUNT + " text, "
                + KEY_SPENDING_DATE + " text, "
                + KEY_SPENDING_DAY + " text" + ");");

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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURRENT_COUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPENDING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTION_INCOME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTION_SPENDING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTION_PLANING);
        this.onCreate(db);
    }

    //region add to CurrentCount/Income/Spending and I/S/P Section
    public void addToCurrCount(double currentCount) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_CURRENT_COUNT, currentCount);

        db.insert(TABLE_CURRENT_COUNT, null, values);
        db.close();
    }

    public void addToIncome(FinanceObject financeObject) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_INCOME_IMG_ID, financeObject.getImgId());
        values.put(KEY_INCOME_NAME, financeObject.getNameObj());
        values.put(KEY_INCOME_AMOUNT, financeObject.getAmount());
        values.put(KEY_INCOME_DATE, financeObject.getDate());
        values.put(KEY_INCOME_DAY, financeObject.getDay());

        db.insert(TABLE_INCOME, null, values);
        db.close();
    }

    public void addToSpending(FinanceObject financeObject) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_SPENDING_IMG_ID, financeObject.getImgId());
        values.put(KEY_SPENDING_NAME, financeObject.getNameObj());
        values.put(KEY_SPENDING_AMOUNT, financeObject.getAmount());
        values.put(KEY_SPENDING_DATE, financeObject.getDate());
        values.put(KEY_SPENDING_DAY, financeObject.getDay());

        db.insert(TABLE_SPENDING, null, values);
        db.close();
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
    //endregion

    //region get: AllDate/TotalAmount/AllSectionIncome/AllSectionSpending/AllSectionPlaning

    public double getTotalIncome() {
        SQLiteDatabase db = this.getWritableDatabase();
        double totalIncome = 0;
        Cursor cursor = db.query(TABLE_INCOME, new String[]{"SUM(amount) AS amount"}, null, null, null, null, null);
        if (cursor.moveToLast()) {
            totalIncome = cursor.getDouble(0);
        }
        return totalIncome;
    }

    public double getTotalSpending() {
        SQLiteDatabase db = this.getWritableDatabase();
        double totalSpending = 0;
        Cursor cursor = db.query(TABLE_SPENDING, new String[]{"SUM(amount) AS amount"}, null, null, null, null, null);
        if (cursor.moveToLast()) {
            totalSpending = cursor.getDouble(0);
        }
        return totalSpending;
    }

    public List<FinanceObject> getOneSection(String nameObj, String date, int pageNumber) {

        List<FinanceObject> objectList = new ArrayList<FinanceObject>();
        String[] columns = null;
        String selectionSpend = null;
        String[] selectionArgsSpend = null;
        String selectionInc = null;
        String[] selectionArgsInc = null;

        SQLiteDatabase db = this.getWritableDatabase();
        switch (pageNumber) {
            case 1:
                columns = new String[]{KEY_SPENDING_ID, KEY_SPENDING_IMG_ID, KEY_SPENDING_NAME, KEY_SPENDING_AMOUNT, KEY_SPENDING_DAY};
                selectionSpend = KEY_SPENDING_NAME + " = ? AND " + KEY_SPENDING_DATE + " = ? ";
                selectionArgsSpend = new String[]{nameObj, date};
                Cursor cursorSpending = db.query(TABLE_SPENDING, columns, selectionSpend, selectionArgsSpend,
                        null, null, null);
                createFinList(cursorSpending, objectList);
                break;
            case 2:
                columns = new String[]{KEY_INCOME_ID, KEY_INCOME_IMG_ID, KEY_INCOME_NAME, KEY_INCOME_AMOUNT, KEY_INCOME_DAY};
                selectionInc = KEY_INCOME_NAME + " = ? AND " + KEY_INCOME_DATE + " = ? ";
                selectionArgsInc = new String[]{nameObj, date};
                Cursor cursorIncome = db.query(TABLE_INCOME, columns, selectionInc, selectionArgsInc,
                        null, null, null);
                createFinList(cursorIncome, objectList);
                break;
        }
        db.close();
        return objectList;
    }

    public List<FinanceObject> createFinList(Cursor cursor, List<FinanceObject> objectList) {
        if (cursor.moveToFirst()) {
            do {
                FinanceObject financeObject = new FinanceObject();
                financeObject.setObjId(cursor.getInt(0));
                financeObject.setImgId(cursor.getInt(1));
                financeObject.setNameObj(cursor.getString(2));
                financeObject.setAmount(cursor.getDouble(3));
                financeObject.setDay(cursor.getString(4));
                objectList.add(financeObject);
            } while (cursor.moveToNext());
        }
        return objectList;
    }

    public String getCurrentCount() {
        SQLiteDatabase db = this.getWritableDatabase();
        String currCount = "";
        Cursor cursor = db.query(TABLE_CURRENT_COUNT, new String[]{KEY_CURRENT_COUNT},
                KEY_CURRENT_COUNT_ID + " = ? ", new String[]{String.valueOf(1)},
                null, null, null);
        if (cursor.moveToLast()) {
            do {
                currCount = cursor.getString(0);
            } while (cursor.moveToPrevious());
        } else
            currCount = "";
        return currCount;
    }

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
                columns = new String[]{KEY_SPENDING_IMG_ID, KEY_SPENDING_NAME, "SUM(amount) AS amount"};
                groupBySpend = KEY_SPENDING_NAME + ", " + KEY_SPENDING_DATE;
                havingSpend = KEY_SPENDING_DATE + " = " + date;
                Cursor cursorSpending = db.query(TABLE_SPENDING, columns, null, null, groupBySpend, havingSpend, KEY_SPENDING_AMOUNT);
                if (cursorSpending.moveToLast()) {
                    do {
                        Section section = new Section();
                        section.setImageId(cursorSpending.getInt(0));
                        section.setNameSection(cursorSpending.getString(1));
                        section.setAmount(cursorSpending.getString(2));

                        sectionList.add(section);
                    } while (cursorSpending.moveToPrevious());
                }
                break;
            case 2:
                columns = new String[]{KEY_INCOME_IMG_ID, KEY_INCOME_NAME, "SUM(amount) AS amount"};
                groupByInc = KEY_INCOME_NAME + ", " + KEY_INCOME_DATE;
                havingInc = KEY_INCOME_DATE + " = " + date;
                Cursor cursorIncome = db.query(TABLE_INCOME, columns, null, null, groupByInc, havingInc, KEY_INCOME_AMOUNT);
                if (cursorIncome.moveToLast()) {
                    do {
                        Section section = new Section();
                        section.setImageId(cursorIncome.getInt(0));
                        section.setNameSection(cursorIncome.getString(1));
                        section.setAmount(cursorIncome.getString(2));

                        sectionList.add(section);
                    } while (cursorIncome.moveToPrevious());
                }
                break;
        }
        db.close();
        return sectionList;
    }

    public int getImgSectionByName(String name, int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        int imgId = 0;
        Cursor cursor;
        switch (i) {
            case 1:
                cursor = db.query(TABLE_SECTION_SPENDING, new String[]{KEY_SECTION_SPENDING_IMG_ID},
                        KEY_SECTION_SPENDING_NAME + " = ? ", new String[]{name}, null, null, null);
                if (cursor.moveToFirst()) {
                    imgId = cursor.getInt(0);
                }
                break;
            case 2:
                cursor = db.query(TABLE_SECTION_INCOME, new String[]{KEY_SECTION_INCOME_IMG_ID},
                        KEY_SECTION_INCOME_NAME + " = ? ", new String[]{name}, null, null, null);
                if (cursor.moveToFirst()) {
                    imgId = cursor.getInt(0);
                }
                break;
        }
        db.close();
        return imgId;
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

    public List<String> getAllNameSection(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> allName = new ArrayList<>();
        Cursor cursor;
        switch (i) {
            case 1:
                cursor = db.query(TABLE_SECTION_SPENDING, new String[]{KEY_SECTION_SPENDING_NAME},
                        null, null, null, null, null);
                if (cursor.moveToLast()) {
                    do {
                        allName.add(cursor.getString(0));
                    } while (cursor.moveToPrevious());
                }
                break;
            case 2:
                cursor = db.query(TABLE_SECTION_INCOME, new String[]{KEY_SECTION_INCOME_NAME},
                        null, null, null, null, null);
                if (cursor.moveToLast()) {
                    do {
                        allName.add(cursor.getString(0));
                    } while (cursor.moveToPrevious());
                }
                break;
        }
        db.close();
        return allName;
    }
    //endregion

    //region delete I/S/P Section and I/S/P Object
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

    public void deleteIncomeObject(FinanceObject financeObject, int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INCOME, KEY_INCOME_NAME + " = ? AND "
                        + KEY_INCOME_AMOUNT + " = ? AND " + KEY_INCOME_ID + " = ? ",
                new String[]{financeObject.getNameObj(), String.valueOf(financeObject.getAmount()),
                        String.valueOf(id)});
        db.close();
    }

    public void deleteSpendingObject(FinanceObject financeObject, int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SPENDING, KEY_SPENDING_NAME + " = ? AND "
                        + KEY_SPENDING_AMOUNT + " = ? AND " + KEY_SPENDING_ID + " = ? ",
                new String[]{financeObject.getNameObj(), String.valueOf(financeObject.getAmount()),
                        String.valueOf(id)});
        db.close();
    }

    //endregion

    //region update: CurrentCount  I/S/P Section and I/S/P Object
    public void updateCurrentCount(double currentCount) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_CURRENT_COUNT, currentCount);
        db.update(TABLE_CURRENT_COUNT, values, KEY_CURRENT_COUNT_ID + " = ? ", new String[]{String.valueOf(1)});
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

    public void updateIncomeObject(FinanceObject oldObject, FinanceObject newObject) {

        /*SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_INCOME_NAME, oldObject.getNameObj());
        values.put(KEY_INCOME_AMOUNT, oldObject.getAmount());
        db.update(TABLE_INCOME, values, KEY_INCOME_ID + "=" + oldObject.getObjId(), null);

        db.close();*/
        deleteIncomeObject(oldObject, oldObject.getObjId());
        addToIncome(newObject);

    }

    public void updateSpendingObject(FinanceObject oldObject, FinanceObject newObject) {

        /*SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_SPENDING_NAME, oldObject.getNameObj());
        values.put(KEY_SPENDING_AMOUNT, oldObject.getAmount());
        db.update(TABLE_SPENDING, values, KEY_SPENDING_ID + "=" + oldObject.getObjId(), null);

        db.close();*/
        deleteSpendingObject(oldObject, oldObject.getObjId());
        addToSpending(newObject);

    }
    //endregion

    public void plusDB(double amount) {
        double currentCount = Double.valueOf(getCurrentCount());
        currentCount += amount;
        updateCurrentCount(currentCount);
    }

    public void minusDB(double amount) {
        double currentCount = Double.valueOf(getCurrentCount());
        currentCount -= amount;
        updateCurrentCount(currentCount);
    }
}