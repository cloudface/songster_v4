package com.samples.songster.dal.mylist.events;

import com.samples.songster.dal.mylist.MyListRepository;
import com.samples.songster.dal.search.dto.SongDto;
import com.samples.songster.crosscut.Event;

import java.util.List;

/**
 * Created by chrisbraunschweiler1 on 08/03/16.
 */
public class LoadedMyListEvent extends Event<List<SongDto>> {
    private MyListRepository.MyListRepositoryListener mListener;

    public LoadedMyListEvent(List<SongDto> payload, MyListRepository.MyListRepositoryListener listener) {
        super(payload);
        this.mListener = listener;
    }

    public MyListRepository.MyListRepositoryListener getListener() {
        return mListener;
    }
}
