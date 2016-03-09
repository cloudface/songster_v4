package com.samples.songster.purchase;

import com.samples.songster.search.repository.dto.AuthorizationDto;
import com.samples.songster.search.repository.dto.CheckoutDto;
import com.samples.songster.search.repository.dto.SongDto;
import com.samples.songster.search.repository.dto.UserDto;

/**
 * Created by chrisbraunschweiler1 on 09/03/16.
 */
public interface PurchaseRepositoryListener {
    void onCheckoutSuccess(CheckoutDto checkoutDto, SongDto songDto);

    void onAuthorizationSuccess(AuthorizationDto authorizationDto, SongDto songDto);

    void onLoginSuccess(UserDto userDto, SongDto songDto);

    void onPurchaseSuccess(SongDto song);
}
