package com.samples.songster.presentation.search;

import android.support.v7.widget.helper.ItemTouchHelper;

import com.samples.songster.dal.search.SearchRepository;
import com.samples.songster.dal.login.UserDataRepository;
import com.samples.songster.dal.search.dto.SearchResultDto;
import com.samples.songster.dal.search.dto.SongDto;
import com.samples.songster.business.search.SearchUseCase;
import com.samples.songster.business.search.UseCase;

/**
 * Created by chrisbraunschweiler1 on 02/11/15.
 */
public class SearchPresenter implements UseCase.UseCaseListener {

    private ViewModel mViewModel;
    private UseCase mUseCase;
    private SearchView mSearchView;

    public SearchPresenter(ViewModel viewModel, SearchRepository searchRepository, UserDataRepository userDataRepository, SearchView searchView){
        this.mViewModel = viewModel;
        mSearchView = searchView;
        mUseCase = new SearchUseCase(searchRepository, userDataRepository, this);
    }

    public void onSearch(String searchString) {
        if(searchString != null && !searchString.isEmpty()){
            mSearchView.showProgressBar();
            mSearchView.hideKeyboard();
            mUseCase.search(searchString);
        }
    }

    @Override
    public void onSearchSuccess(SearchResultDto result) {
        if(result != null && result.getSongs().size() > 0){
            mViewModel.createDisplayableResults(result);
            mSearchView.showResults();
        } else {
            mSearchView.showNoResultsMessage();
        }
    }

    @Override
    public void onAddSongSuccess(SongDto addedSong) {
        mViewModel.onAddedSong(addedSong);
        mSearchView.updateResults();
    }

    public void present() {
        if(mViewModel != null){
            if(mViewModel.getItems().size() > 0) {
                mSearchView.showResults();
            } else {
                mSearchView.showInfoMessage();
            }
        } else {
            mSearchView.showInfoMessage();
        }
    }

    public void onAddSongToMyList(int position) {
        SongDto selectedSong = mViewModel.selectSong(position);
        mUseCase.addSongToMyList(selectedSong);
        mSearchView.updateResults();
    }

    public void onSwipedItem(int position, int swipeDir) {
        if(swipeDir == ItemTouchHelper.LEFT){
            //Display some other fragment to demonstrate benefit of viewmodel being parcellable
            mSearchView.displaySongDetails();
        } else {
            SongDto swipedSong = mViewModel.selectSong(position);
            mUseCase.purchaseSong(swipedSong);
        }
    }

    @Override
    public void showLoginView(SongDto songDto) {
        mViewModel.setSongToBePurchased(songDto);
        mSearchView.showLoginView();
    }

    @Override
    public void showPurchaseSuccsessMessage(SongDto song) {
        mViewModel.onPurchasedSong(song);
        mSearchView.showPurchaseSuccessMessage(song);
    }

    public void login(String username, String password) {
        mUseCase.login(username, password, mViewModel.getSongToBePurchased());
    }
}
