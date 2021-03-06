package com.samples.songster.business.mylist;

import com.samples.songster.dal.login.UserDto;
import com.samples.songster.business.mylist.events.PurchaseSongEvent;
import com.samples.songster.business.mylist.events.ShowLoginViewEvent;
import com.samples.songster.business.mylist.events.ShowPurchaseSuccessMessageEvent;
import com.samples.songster.dal.purchase.PurchaseRepository;
import com.samples.songster.business.purchase.PurchaseUseCase;
import com.samples.songster.business.purchase.PurchaseUseCaseListener;
import com.samples.songster.dal.login.UserDataRepository;
import com.samples.songster.dal.search.dto.SongDto;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by chrisbraunschweiler1 on 09/03/16.
 */
public class MyListUseCase implements UseCase, PurchaseUseCaseListener {

    private static final EventBus EVENT_BUS = EventBus.getDefault();
    private final PurchaseUseCase mPurchaseUseCase;
    private MyListUseCaseListener mListener;

    public MyListUseCase(PurchaseRepository purchaseRepository, UserDataRepository userDataRepository, MyListUseCaseListener listener) {
        mListener = listener;
        mPurchaseUseCase = new PurchaseUseCase(purchaseRepository, userDataRepository, this);
    }

    @Override
    public void start() {
        EVENT_BUS.register(this);
    }

    @Override
    public void pause() {
        EVENT_BUS.unregister(this);
    }

    @Override
    public void purchaseSong(SongDto song) {
        EVENT_BUS.post(new PurchaseSongEvent(song));
    }

    @Override
    public void onLoggedIn(UserDto userDto, SongDto songToPurchase) {
        mPurchaseUseCase.authorizePurchase(userDto, songToPurchase);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onPurchaseSong(PurchaseSongEvent event){
        try {
            //Create some latency
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mPurchaseUseCase.purchaseSong(event.getPayload());
    }

    @Override
    public void showLoginView(SongDto songDto) {
        EVENT_BUS.post(new ShowLoginViewEvent(songDto));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowLoginView(ShowLoginViewEvent event){
        mListener.showLoginView(event.getPayload());
    }

    @Override
    public void showPurchaseSuccsessMessage(SongDto song) {
        EVENT_BUS.post(new ShowPurchaseSuccessMessageEvent(song));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowPurchaseSuccessMessage(ShowPurchaseSuccessMessageEvent event){
        mListener.showPurchaseSuccsessMessage(event.getPayload());
    }
}
