package com.samples.songster.presentation.mylist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.samples.songster.BR;
import com.samples.songster.dal.login.UserDto;
import com.samples.songster.dal.mylist.MyListRepository;
import com.samples.songster.business.mylist.MyListUseCase;
import com.samples.songster.business.mylist.UseCase;
import com.samples.songster.dal.purchase.MockPurchaseRepository;
import com.samples.songster.dal.login.MockUserDataRepository;
import com.samples.songster.dal.search.dto.SongDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisbraunschweiler1 on 04/03/16.
 */
public class MyListViewModel extends BaseObservable implements UseCase.MyListUseCaseListener, Parcelable {

    @Bindable
    private boolean mDisplayInfoMessage;

    @Bindable
    private boolean mLoading;
    private List<MyListItemModel> mMyItems;
    private MyListRepository mRepository;
    private UseCase mUseCase;
    private MyListView mView;
    private MyListItemModel mItemBeingPurchased;

    public MyListViewModel(MyListRepository repository, MyListView view){
        mRepository = repository;
        mUseCase = new MyListUseCase(new MockPurchaseRepository(), new MockUserDataRepository(), this);
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
        mUseCase.start();
        if(mMyItems == null || mMyItems.size() == 0){
            setLoading(false);
            setDisplayInfoMessage(true);
        }
    }

    public void onPause() {
        mRepository.pause();
        mUseCase.pause();
    }

    public void onClickRefresh(View view){
        setLoading(true);
        setDisplayInfoMessage(false);
        mRepository.loadMyList(new LoadedMyListHandler());
    }

    @Override
    public void showLoginView(SongDto songDto) {
        mView.showLoginView();
    }

    @Override
    public void showPurchaseSuccsessMessage(SongDto song) {
        mItemBeingPurchased.setPurchased(true);
        mItemBeingPurchased.setBeingPurchased(false);
        mItemBeingPurchased = null;
    }

    public void onLoggedIn(UserDto user) {
        SongDto songBeingPurchased = mItemBeingPurchased.getSong();
        mUseCase.onLoggedIn(user, songBeingPurchased);
    }

    public void setView(MyListView view){
        this.mView = view;
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
            if(mItemBeingPurchased == null) {
                mItemBeingPurchased = item;
                if (item.getItemType() == MyListItemModel.ItemType.RESULT) {
                    item.setBeingPurchased(true);
                    mUseCase.purchaseSong(item.getSong());
                }
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(mDisplayInfoMessage ? (byte) 1 : (byte) 0);
        dest.writeByte(mLoading ? (byte) 1 : (byte) 0);
        dest.writeTypedList(mMyItems);
        dest.writeParcelable(this.mItemBeingPurchased, flags);
    }

    protected MyListViewModel(Parcel in) {
        this.mDisplayInfoMessage = in.readByte() != 0;
        this.mLoading = in.readByte() != 0;
        this.mMyItems = in.createTypedArrayList(MyListItemModel.CREATOR);
        this.mItemBeingPurchased = in.readParcelable(MyListItemModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<MyListViewModel> CREATOR = new Parcelable.Creator<MyListViewModel>() {
        @Override
        public MyListViewModel createFromParcel(Parcel source) {
            return new MyListViewModel(source);
        }

        @Override
        public MyListViewModel[] newArray(int size) {
            return new MyListViewModel[size];
        }
    };
}
