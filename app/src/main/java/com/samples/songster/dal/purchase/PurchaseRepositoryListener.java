package com.samples.songster.dal.purchase;

import com.samples.songster.dal.search.dto.AuthorizationDto;
import com.samples.songster.dal.search.dto.CheckoutDto;
import com.samples.songster.dal.search.dto.SongDto;
import com.samples.songster.dal.login.UserDto;

/**
 * Created by chrisbraunschweiler1 on 09/03/16.
 */
public interface PurchaseRepositoryListener {
    void onCheckoutSuccess(CheckoutDto checkoutDto, SongDto songDto);

    void onAuthorizationSuccess(AuthorizationDto authorizationDto, SongDto songDto);

    void onLoginSuccess(UserDto userDto, SongDto songDto);

    void onPurchaseSuccess(SongDto song);
}
