package com.samples.songster.purchase;

import com.samples.songster.search.repository.UserDataRepository;
import com.samples.songster.search.repository.dto.AuthorizationDto;
import com.samples.songster.search.repository.dto.CheckoutDto;
import com.samples.songster.search.repository.dto.SongDto;
import com.samples.songster.search.repository.dto.UserDto;

/**
 * Created by chrisbraunschweiler1 on 09/03/16.
 */
public class PurchaseUseCase implements PurchaseRepositoryListener {

    private PurchaseRepository mPurchaseRepository;
    private UserDataRepository mUserDataRepository;
    private PurchaseUseCaseListener mListener;

    public PurchaseUseCase(PurchaseRepository purchaseRepository, UserDataRepository userRepository, PurchaseUseCaseListener listener){
        mPurchaseRepository = purchaseRepository;
        mUserDataRepository = userRepository;
        mListener = listener;
    }

    public void purchaseSong(SongDto song) {
        mPurchaseRepository.checkout(song, this);
    }

    public void login(String username, String password, SongDto songDto) {
        mPurchaseRepository.login(username, password, songDto, this);
    }

    @Override
    public void onCheckoutSuccess(CheckoutDto checkoutDto, SongDto songDto) {
        if(checkoutDto.isLoginRequired()){
            if(mUserDataRepository.isLoggedIn()){
                UserDto loggedInUser = mUserDataRepository.getLoggedInUser();
                mPurchaseRepository.authorizePurchase(loggedInUser, songDto, this);
            } else {
                mListener.showLoginView(songDto);
            }
        } else {
            mPurchaseRepository.purchase(songDto, this);
        }
    }

    @Override
    public void onAuthorizationSuccess(AuthorizationDto authorizationDto, SongDto songDto) {
        if(authorizationDto.isPurchaseAuthorized()){
            mPurchaseRepository.purchase(songDto, this);
        } else {
            //TODO show an error dialog
        }
    }

    @Override
    public void onLoginSuccess(UserDto userDto, SongDto songDto) {
        mPurchaseRepository.authorizePurchase(userDto, songDto, this);
    }

    @Override
    public void onPurchaseSuccess(SongDto song) {
        mListener.showPurchaseSuccsessMessage(song);
    }
}
