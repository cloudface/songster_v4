package com.samples.songster.mylist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.samples.songster.BR;
import com.samples.songster.search.repository.dto.SongDto;

/**
 * Created by chrisbraunschweiler1 on 07/03/16.
 */
public class MyListItemModel extends BaseObservable{
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
}
