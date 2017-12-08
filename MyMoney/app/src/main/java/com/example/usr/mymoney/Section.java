package com.example.usr.mymoney;

/**
 * Created by usr on 30.11.2017.
 */

public class Section {
    public int sectionId;
    public String nameSection;
    public int imageId;
    public String amount;
    public String percent;

    public Section(int sectionId, String name, int imageId, String amount, String percent) {
        this.sectionId = sectionId;
        this.nameSection = name;
        this.imageId = imageId;
        this.amount = amount;
        this.percent = percent;
    }

    public Section() {

    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getNameSection() {
        return nameSection;
    }

    public void setNameSection(String name) {
        this.nameSection = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
