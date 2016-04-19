package com.samples.songster.dal.purchase;

import com.samples.songster.dal.search.dto.SongDto;
import com.samples.songster.dal.login.UserDto;

/**
 * Created by chrisbraunschweiler1 on 09/03/16.
 */
public interface PurchaseRepository {
    void checkout(SongDto song, PurchaseRepositoryListener listener);

    void authorizePurchase(UserDto loggedInUser, SongDto songDto, PurchaseRepositoryListener listener);

    void login(String username, String password, SongDto songDto, PurchaseRepositoryListener listener);

    void purchase(SongDto songDto, PurchaseRepositoryListener listener);
}
