package com.samples.songster.dal.mylist.events;

import com.samples.songster.dal.mylist.MyListRepository;

/**
 * Created by chrisbraunschweiler1 on 08/03/16.
 */
public class LoadMyListEvent {
    private MyListRepository.MyListRepositoryListener mListener;

    public LoadMyListEvent(MyListRepository.MyListRepositoryListener listener) {
        this.mListener = listener;
    }

    public MyListRepository.MyListRepositoryListener getListener() {
        return mListener;
    }
}
