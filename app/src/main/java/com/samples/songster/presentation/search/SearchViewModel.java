package com.samples.songster.presentation.search;

import android.os.Parcel;
import android.os.Parcelable;

import com.samples.songster.dal.search.dto.SearchResultDto;
import com.samples.songster.dal.search.dto.SongDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisbraunschweiler1 on 04/11/15.
 */
public class SearchViewModel implements ViewModel, Parcelable {

    private List<SearchItemModel> mItems;
    private SongDto mSongToBePurchased;

    public SearchViewModel() {
        mItems = new ArrayList<>();
    }

    @Override
    public void createDisplayableResults(SearchResultDto result) {
        mItems.clear();
        addHeaderItem();
        addSearchResults(result);
    }

    private void addHeaderItem() {
        SearchItemModel headerItem = new SearchItemModel();
        headerItem.setType(SearchItemModel.ItemType.HEADER);
        mItems.add(headerItem);
    }

    private void addSearchResults(SearchResultDto result) {
        for (SongDto songDto : result.getSongs()) {
            SearchItemModel resultItem = new SearchItemModel();
            resultItem.setType(SearchItemModel.ItemType.RESULT);
            resultItem.setSong(songDto);
            mItems.add(resultItem);
        }
    }

    public List<SearchItemModel> getItems() {
        return mItems;
    }

    @Override
    public SongDto selectSong(int position) {
        SongDto selectedSong = null;
        int index = 0;
        for (SearchItemModel searchItem : mItems) {
            if (index == position) {
                if (searchItem.getSong() != null) {
                    selectedSong = searchItem.getSong();
                    searchItem.setBeingAdded(true);
                }
            }
            index++;
        }
        return selectedSong;
    }

    @Override
    public SongDto getSongToBePurchased() {
        return mSongToBePurchased;
    }

    @Override
    public void setSongToBePurchased(SongDto songDto) {
        mSongToBePurchased = songDto;
    }

    @Override
    public void onAddedSong(SongDto addedSong) {
        for(SearchItemModel item : mItems){
            if(item.getSong() != null && item.getSong().equals(addedSong)){
                item.setAdded(true);
                item.setBeingAdded(false);
            }
        }
    }

    @Override
    public void onPurchasedSong(SongDto song) {
        //TODO Instead of marking the song as added, we could mark it as having been 'purchased' instead
        for(SearchItemModel item : mItems){
            if(item.getSong() != null && item.getSong().equals(song)){
                item.setAdded(true);
                item.setBeingAdded(false);
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mItems);
        dest.writeParcelable(this.mSongToBePurchased, 0);
    }

    protected SearchViewModel(Parcel in) {
        this.mItems = in.createTypedArrayList(SearchItemModel.CREATOR);
        this.mSongToBePurchased = in.readParcelable(SongDto.class.getClassLoader());
    }

    public static final Parcelable.Creator<SearchViewModel> CREATOR = new Parcelable.Creator<SearchViewModel>() {
        public SearchViewModel createFromParcel(Parcel source) {
            return new SearchViewModel(source);
        }

        public SearchViewModel[] newArray(int size) {
            return new SearchViewModel[size];
        }
    };
}

class SearchItemModel implements Parcelable {

    private SongDto mSong;
    private ItemType mType;
    private boolean mAdded;
    private boolean mBeingAdded;

    public SongDto getSong() {
        return mSong;
    }

    public void setSong(SongDto song) {
        this.mSong = song;
    }

    public ItemType getType() {
        return mType;
    }

    public void setType(ItemType type) {
        this.mType = type;
    }

    public boolean isAdded() {
        return mAdded;
    }

    public void setAdded(boolean added) {
        this.mAdded = added;
    }

    public void setBeingAdded(boolean beingAdded) {
        this.mBeingAdded = beingAdded;
    }

    public boolean isBeingAdded() {
        return mBeingAdded;
    }

    public enum ItemType {
        HEADER,
        RESULT
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mSong, 0);
        dest.writeInt(this.mType == null ? -1 : this.mType.ordinal());
        dest.writeByte(mAdded ? (byte) 1 : (byte) 0);
        dest.writeByte(mBeingAdded ? (byte) 1 : (byte) 0);
    }

    public SearchItemModel() {
    }

    protected SearchItemModel(Parcel in) {
        this.mSong = in.readParcelable(SongDto.class.getClassLoader());
        int tmpMType = in.readInt();
        this.mType = tmpMType == -1 ? null : ItemType.values()[tmpMType];
        this.mAdded = in.readByte() != 0;
        this.mBeingAdded = in.readByte() != 0;
    }

    public static final Parcelable.Creator<SearchItemModel> CREATOR = new Parcelable.Creator<SearchItemModel>() {
        public SearchItemModel createFromParcel(Parcel source) {
            return new SearchItemModel(source);
        }

        public SearchItemModel[] newArray(int size) {
            return new SearchItemModel[size];
        }
    };
}
