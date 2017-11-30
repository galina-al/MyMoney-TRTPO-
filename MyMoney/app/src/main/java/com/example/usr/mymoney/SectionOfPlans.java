package com.example.usr.mymoney;

/**
 * Created by usr on 01.12.2017.
 */

public class SectionOfPlans {
    protected String name;
    protected int imageId;
    protected String amount;

    public SectionOfPlans(String name, int imageId, String amount) {
        this.name = name;
        this.imageId = imageId;
        this.amount = amount;
    }
}
