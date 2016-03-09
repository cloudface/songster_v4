package com.samples.songster.mylist.usecase;

import com.samples.songster.search.repository.dto.SongDto;

/**
 * Created by chrisbraunschweiler1 on 09/03/16.
 */
public interface UseCase {
    void start();

    void pause();

    void purchaseSong(SongDto song);

    interface MyListUseCaseListener {
        void showLoginView(SongDto songDto);

        void showPurchaseSuccsessMessage(SongDto song);
    }
}
