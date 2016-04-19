package com.samples.songster.dal.search.dto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chrisbraunschweiler1 on 02/11/15.
 */
public class SongDto implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.artist);
        dest.writeString(this.name);
        dest.writeString(this.album);
    }

    public SongDto() {
    }

    protected SongDto(Parcel in) {
        this.artist = in.readString();
        this.name = in.readString();
        this.album = in.readString();
    }

    public static final Parcelable.Creator<SongDto> CREATOR = new Parcelable.Creator<SongDto>() {
        public SongDto createFromParcel(Parcel source) {
            return new SongDto(source);
        }

        public SongDto[] newArray(int size) {
            return new SongDto[size];
        }
    };
}
