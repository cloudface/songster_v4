package com.samples.songster.purchase;

import com.samples.songster.search.repository.dto.SongDto;

/**
 * Created by chrisbraunschweiler1 on 09/03/16.
 */
public interface PurchaseUseCaseListener {
    void showLoginView(SongDto songDto);

    void showPurchaseSuccsessMessage(SongDto song);
}
