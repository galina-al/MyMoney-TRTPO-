package com.example.usr.mymoney.Fragment.dummy;

import com.example.usr.mymoney.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static final Integer[] IMG_ID = {
            R.drawable.money_bag,
            R.drawable.business,
            R.drawable.gift,
            R.drawable.work,
            R.drawable.receive_cash
    };

    public static final String [] LIST_NAME = {
            "Зарплата",
            "Бизнес",
            "Подарки",
            "Подработка",
            "Продажа личных вещей"
    };
    //private static final int COUNT = 25;

    /*static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }*/

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        //ITEM_MAP.put(item.id, item);
    }

    /*private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }*/

    /*private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }*/

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final Integer imgId ;
        public final String content;
        public final String details;

        public DummyItem(Integer imgId, String content, String details) {
            this.content = content;
            this.details = details;
            this.imgId = imgId;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
