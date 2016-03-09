package com.samples.songster.mylist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.samples.songster.BR;
import com.samples.songster.mylist.repository.MyListRepository;
import com.samples.songster.mylist.usecase.MyListUseCase;
import com.samples.songster.mylist.usecase.UseCase;
import com.samples.songster.purchase.MockPurchaseRepository;
import com.samples.songster.search.repository.UserDataInMemoryRepository;
import com.samples.songster.search.repository.dto.SongDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisbraunschweiler1 on 04/03/16.
 */
public class MyListViewModel extends BaseObservable implements UseCase.MyListUseCaseListener {

    @Bindable
    private boolean mDisplayInfoMessage;

    @Bindable
    private boolean mLoading;
    private List<MyListItemModel> mMyItems;
    private MyListRepository mRepository;
    private UseCase mUseCase;
    private MyListView mView;

    public MyListViewModel(MyListRepository repository, MyListView view){
        mRepository = repository;
        mUseCase = new MyListUseCase(new MockPurchaseRepository(), new UserDataInMemoryRepository(), this);
        mView = view;
        mMyItems = new ArrayList<>();
    }

    public List<MyListItemModel> getMyItems() {
        return mMyItems;
    }

    public void setMyItems(List<MyListItemModel> myItems) {
        this.mMyItems = myItems;
    }

    public boolean isLoading() {
        return mLoading;
    }

    public void setLoading(boolean loading) {
        this.mLoading = loading;
        notifyPropertyChanged(BR.loading);
    }

    public boolean isDisplayInfoMessage() {
        return mDisplayInfoMessage;
    }

    public void setDisplayInfoMessage(boolean displayInfoMessage) {
        this.mDisplayInfoMessage = displayInfoMessage;
        notifyPropertyChanged(BR.displayInfoMessage);
    }

    public void onResume(){
        mRepository.start();
        setLoading(false);
        setDisplayInfoMessage(true);
    }

    public void onPause() {
        mRepository.pause();
    }

    public void onClickRefresh(View view){
        setLoading(true);
        setDisplayInfoMessage(false);
        mRepository.loadMyList(new LoadedMyListHandler());
    }

    @Override
    public void showLoginView(SongDto songDto) {

    }

    @Override
    public void showPurchaseSuccsessMessage(SongDto song) {
        for(MyListItemModel item : mMyItems){
            if(item.getSong() != null && item.getSong().equals(song)){
                item.setPurchased(true);
                item.setBeingPurchased(false);
            }
        }
    }

    public class LoadedMyListHandler implements MyListRepository.MyListRepositoryListener, MyListItemModel.MyListItemModelListener {

        @Override
        public void onLoadedMyList(List<SongDto> songs) {
            setLoading(false);

            //Build item models using the retrieved songs
            //Add a header item
            MyListItemModel headerItem = new MyListItemModel(this);
            headerItem.setItemType(MyListItemModel.ItemType.HEADER);
            headerItem.setTitle("My Songs");
            mMyItems.add(headerItem);

            //Add items for each song
            for(SongDto song : songs){
                MyListItemModel songItem = new MyListItemModel(this);
                songItem.setSong(song);
                songItem.setItemType(MyListItemModel.ItemType.RESULT);
                mMyItems.add(songItem);
            }
            mView.updateRecyclerView();
        }

        @Override
        public void onBuyItem(MyListItemModel item) {
            //TODO start purchase workflow
            if(item.getItemType() == MyListItemModel.ItemType.RESULT){
                item.setBeingPurchased(true);
                mUseCase.purchaseSong(item.getSong());
            }
        }
    }
}
