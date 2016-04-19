package com.samples.songster.dal.purchase;

import com.samples.songster.dal.search.dto.AuthorizationDto;
import com.samples.songster.dal.search.dto.CheckoutDto;
import com.samples.songster.dal.search.dto.SongDto;
import com.samples.songster.dal.login.UserDto;

/**
 * Created by chrisbraunschweiler1 on 09/03/16.
 */
public class MockPurchaseRepository implements PurchaseRepository{

    @Override
    public void checkout(SongDto song, PurchaseRepositoryListener listener){
        CheckoutDto checkoutDto = new CheckoutDto();
        checkoutDto.setLoginRequired(true);
        listener.onCheckoutSuccess(checkoutDto, song);
    }

    @Override
    public void authorizePurchase(UserDto loggedInUser, SongDto songDto, PurchaseRepositoryListener listener){
        AuthorizationDto authorizationDto = new AuthorizationDto();
        authorizationDto.setPurchaseAuthorized(true);
        listener.onAuthorizationSuccess(authorizationDto, songDto);
    }

    @Override
    public void login(String username, String password, SongDto songDto, PurchaseRepositoryListener listener){
        UserDto user = new UserDto();
        user.setUsername(username);
        user.setPassword(password);
        listener.onLoginSuccess(user, songDto);
    }

    @Override
    public void purchase(SongDto songDto, PurchaseRepositoryListener listener){
        listener.onPurchaseSuccess(songDto);
    }
}
