package com.samples.songster.business.purchase;

import com.samples.songster.dal.search.dto.SongDto;

/**
 * Created by chrisbraunschweiler1 on 09/03/16.
 */
public interface PurchaseUseCaseListener {
    void showLoginView(SongDto songDto);

    void showPurchaseSuccsessMessage(SongDto song);
}
