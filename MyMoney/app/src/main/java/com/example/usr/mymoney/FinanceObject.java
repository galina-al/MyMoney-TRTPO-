package com.example.usr.mymoney;

public class FinanceObject {

    public String nameObj;
    public Double amount;
    public String date;
    public String day;

    public FinanceObject() {
    }

    public FinanceObject(String nameObj, Double amount, String date, String day) {
        this.nameObj = nameObj;
        this.amount = amount;
        this.date = date;
        this.day = day;
    }

    public void setNameObj(String nameObj) {
        this.nameObj = nameObj;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }


    public String getNameObj() {
        return nameObj;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}
