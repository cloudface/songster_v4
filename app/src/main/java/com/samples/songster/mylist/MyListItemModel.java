package com.samples.songster.mylist;

import com.samples.songster.search.repository.dto.SongDto;

/**
 * Created by chrisbraunschweiler1 on 07/03/16.
 */
public class MyListItemModel {
    private String someText;
    private SongDto mSong;
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

    public SongDto getSong() {
        return mSong;
    }

    public void setSong(SongDto song) {
        this.mSong = song;
    }

    public enum ItemType {
        HEADER,
        RESULT
    }
}
