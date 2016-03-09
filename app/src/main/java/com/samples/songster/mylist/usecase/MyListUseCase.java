package com.samples.songster.mylist.usecase;

import com.samples.songster.purchase.PurchaseRepository;
import com.samples.songster.purchase.PurchaseUseCase;
import com.samples.songster.purchase.PurchaseUseCaseListener;
import com.samples.songster.search.repository.UserDataRepository;
import com.samples.songster.search.repository.dto.SongDto;

/**
 * Created by chrisbraunschweiler1 on 09/03/16.
 */
public class MyListUseCase implements UseCase, PurchaseUseCaseListener {

    private final PurchaseUseCase mPurchaseUseCase;
    private MyListUseCaseListener mListener;

    public MyListUseCase(PurchaseRepository purchaseRepository, UserDataRepository userDataRepository, MyListUseCaseListener listener) {
        mListener = listener;
        mPurchaseUseCase = new PurchaseUseCase(purchaseRepository, userDataRepository, this);
    }

    @Override
    public void purchaseSong(SongDto song) {
        mPurchaseUseCase.purchaseSong(song);
    }

    @Override
    public void showLoginView(SongDto songDto) {
        mListener.showLoginView(songDto);
    }

    @Override
    public void showPurchaseSuccsessMessage(SongDto song) {
        mListener.showPurchaseSuccsessMessage(song);
    }
}
