package com.example.usr.mymoney;

/**
 * Created by usr on 01.12.2017.
 */

public class FinanceObject {

    public String nameObj;
    public double amount;
    //Date
    public String date;

    public FinanceObject(String nameObj, double amount, String date) {
        this.nameObj = nameObj;
        this.amount = amount;
        this.date = date;
    }

    public void setNameObj(String nameObj) {
        this.nameObj = nameObj;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameObj() {
        return nameObj;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}
