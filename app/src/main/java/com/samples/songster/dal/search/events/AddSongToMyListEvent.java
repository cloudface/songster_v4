package com.samples.songster.dal.search.events;

import com.samples.songster.crosscut.Event;
import com.samples.songster.dal.search.SearchRepository;
import com.samples.songster.dal.search.dto.SongDto;

/**
 * Created by chrisbraunschweiler1 on 27/04/16.
 */
public class AddSongToMyListEvent extends Event<SongDto> {
    private SearchRepository.SearchListener mListener;

    public AddSongToMyListEvent(SongDto payload, SearchRepository.SearchListener listener) {
        super(payload);
        this.mListener = listener;
    }

    public SearchRepository.SearchListener getListener() {
        return mListener;
    }
}
