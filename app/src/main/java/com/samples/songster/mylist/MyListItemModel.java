package com.samples.songster.mylist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.samples.songster.BR;
import com.samples.songster.search.repository.dto.SongDto;

/**
 * Created by chrisbraunschweiler1 on 07/03/16.
 */
public class MyListItemModel extends BaseObservable implements Parcelable {
    private String mTitle;
    private SongDto mSong;
    private ItemType mItemType;

    @Bindable
    private boolean mBeingPurchased;

    @Bindable
    private boolean purchased;

    private MyListItemModelListener mListener;

    public MyListItemModel(MyListItemModelListener listener){
        mListener = listener;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public ItemType getItemType() {
        return mItemType;
    }

    public void setItemType(ItemType itemType) {
        this.mItemType = itemType;
    }

    public SongDto getSong() {
        return mSong;
    }

    public void setSong(SongDto song) {
        this.mSong = song;
    }

    public boolean isBeingPurchased() {
        return mBeingPurchased;
    }

    public void setBeingPurchased(boolean beingPurchased) {
        this.mBeingPurchased = beingPurchased;
        notifyPropertyChanged(BR.beingPurchased);
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
        notifyPropertyChanged(BR.purchased);
    }

    public enum ItemType {
        HEADER,
        RESULT
    }

    public void onClickBuy(View view){
        mListener.onBuyItem(this);
    }

    public interface MyListItemModelListener {
        void onBuyItem(MyListItemModel item);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeParcelable(this.mSong, flags);
        dest.writeInt(this.mItemType == null ? -1 : this.mItemType.ordinal());
        dest.writeByte(mBeingPurchased ? (byte) 1 : (byte) 0);
        dest.writeByte(purchased ? (byte) 1 : (byte) 0);
    }

    protected MyListItemModel(Parcel in) {
        this.mTitle = in.readString();
        this.mSong = in.readParcelable(SongDto.class.getClassLoader());
        int tmpMItemType = in.readInt();
        this.mItemType = tmpMItemType == -1 ? null : ItemType.values()[tmpMItemType];
        this.mBeingPurchased = in.readByte() != 0;
        this.purchased = in.readByte() != 0;
    }

    public static final Parcelable.Creator<MyListItemModel> CREATOR = new Parcelable.Creator<MyListItemModel>() {
        @Override
        public MyListItemModel createFromParcel(Parcel source) {
            return new MyListItemModel(source);
        }

        @Override
        public MyListItemModel[] newArray(int size) {
            return new MyListItemModel[size];
        }
    };
}
