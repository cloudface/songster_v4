package com.samples.songster.mylist;

/**
 * Created by chrisbraunschweiler1 on 07/03/16.
 */
public class MyListItemModel {
    private String someText;
    private ItemType itemType;

    public String getSomeText() {
        return someText;
    }

    public void setSomeText(String someText) {
        this.someText = someText;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public enum ItemType {
        HEADER,
        RESULT
    }
}
