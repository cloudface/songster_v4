package com.samples.songster.mylist.repository.events;

import com.samples.songster.mylist.repository.MyListRepository;

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
