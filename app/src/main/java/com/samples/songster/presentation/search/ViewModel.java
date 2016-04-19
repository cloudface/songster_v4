package com.samples.songster.presentation.search;

import android.os.Parcelable;

import com.samples.songster.dal.search.dto.SearchResultDto;
import com.samples.songster.dal.search.dto.SongDto;

import java.util.List;

/**
 * Created by chrisbraunschweiler1 on 04/11/15.
 */
public interface ViewModel extends Parcelable{
    void createDisplayableResults(SearchResultDto result);

    List<SearchItemModel> getItems();

    SongDto selectSong(int position);

    SongDto getSongToBePurchased();

    void setSongToBePurchased(SongDto songDto);

    void onAddedSong(SongDto addedSong);

    void onPurchasedSong(SongDto song);
}
