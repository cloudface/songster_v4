package com.samples.songster.mylist.repository.events;

import com.samples.songster.mylist.repository.MyListRepository;
import com.samples.songster.search.repository.dto.SongDto;
import com.samples.songster.settings.events.Event;

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
