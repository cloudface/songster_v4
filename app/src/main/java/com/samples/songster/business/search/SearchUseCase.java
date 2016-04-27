package com.samples.songster.business.search;

import com.samples.songster.dal.search.SearchRepository;
import com.samples.songster.dal.login.UserDataRepository;
import com.samples.songster.dal.search.dto.AuthorizationDto;
import com.samples.songster.dal.search.dto.CheckoutDto;
import com.samples.songster.dal.search.dto.SearchResultDto;
import com.samples.songster.dal.search.dto.SongDto;
import com.samples.songster.dal.login.UserDto;

/**
 * Created by chrisbraunschweiler1 on 23/11/15.
 */
public class SearchUseCase implements UseCase, SearchRepository.SearchListener {

    private SearchRepository mSearchRepository;
    private UserDataRepository mUserDataRepository;
    private UseCaseListener mListener;

    public SearchUseCase(SearchRepository searchRepository, UserDataRepository userDataRepository, UseCaseListener listener){
        mSearchRepository = searchRepository;
        mUserDataRepository = userDataRepository;
        mListener = listener;
    }

    @Override
    public void purchaseSong(SongDto song) {
        mSearchRepository.checkout(song, this);
    }

    @Override
    public void login(String username, String password, SongDto songDto) {
        mSearchRepository.login(username, password, songDto, this);
    }

    @Override
    public void search(String searchString) {
        mSearchRepository.search(searchString, this);
    }

    @Override
    public void addSongToMyList(SongDto selectedSong) {
        mSearchRepository.addSongToMyList(selectedSong, this);
    }

    @Override
    public void onResume() {
        mSearchRepository.start();
    }

    @Override
    public void onPause() {
        mSearchRepository.pause();
    }

    @Override
    public void onSearchSuccess(SearchResultDto result) {
        mListener.onSearchSuccess(result);
    }

    @Override
    public void onAddSongSuccess(SongDto addedSong) {
        mListener.onAddSongSuccess(addedSong);
    }

    @Override
    public void onCheckoutSuccess(CheckoutDto checkoutDto, SongDto songDto) {
        if(checkoutDto.isLoginRequired()){
            if(mUserDataRepository.isLoggedIn()){
                UserDto loggedInUser = mUserDataRepository.getLoggedInUser();
                mSearchRepository.authorizePurchase(loggedInUser, songDto, this);
            } else {
                mListener.showLoginView(songDto);
            }
        } else {
            mSearchRepository.purchase(songDto, this);
        }
    }

    @Override
    public void onAuthorizationSuccess(AuthorizationDto authorizationDto, SongDto songDto) {
        if(authorizationDto.isPurchaseAuthorized()){
            mSearchRepository.purchase(songDto, this);
        } else {
            //TODO show an error dialog
        }
    }

    @Override
    public void onLoginSuccess(UserDto userDto, SongDto songDto) {
        mSearchRepository.authorizePurchase(userDto, songDto, this);
    }

    @Override
    public void onPurchaseSuccess(SongDto song) {
        mListener.showPurchaseSuccsessMessage(song);
    }
}
