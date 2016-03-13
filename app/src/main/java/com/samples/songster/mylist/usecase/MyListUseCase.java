package com.samples.songster.mylist.usecase;

import com.samples.songster.mylist.usecase.events.PurchaseSongEvent;
import com.samples.songster.mylist.usecase.events.ShowLoginViewEvent;
import com.samples.songster.mylist.usecase.events.ShowPurchaseSuccessMessageEvent;
import com.samples.songster.purchase.PurchaseRepository;
import com.samples.songster.purchase.PurchaseUseCase;
import com.samples.songster.purchase.PurchaseUseCaseListener;
import com.samples.songster.login.UserDataRepository;
import com.samples.songster.search.repository.dto.SongDto;

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
