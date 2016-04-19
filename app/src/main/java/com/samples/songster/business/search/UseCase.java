package com.samples.songster.business.search;

import com.samples.songster.dal.search.dto.SearchResultDto;
import com.samples.songster.dal.search.dto.SongDto;

/**
 * Created by chrisbraunschweiler1 on 23/11/15.
 */
public interface UseCase {
    void purchaseSong(SongDto song);

    void login(String username, String password, SongDto songDto);

    void search(String searchString);

    void addSongToMyList(SongDto selectedSong);

    interface UseCaseListener {

        void showLoginView(SongDto songDto);

        void showPurchaseSuccsessMessage(SongDto song);

        void onSearchSuccess(SearchResultDto result);

        void onAddSongSuccess(SongDto addedSong);
    }
}
