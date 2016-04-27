package com.samples.songster.dal.mylist.realm;

import io.realm.RealmObject;

/**
 * Created by chrisbraunschweiler1 on 27/04/16.
 */
public class RealmSong extends RealmObject {
    private String artist;
    private String name;
    private String album;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
