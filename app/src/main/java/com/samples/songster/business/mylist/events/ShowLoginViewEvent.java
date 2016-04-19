package com.samples.songster.business.mylist.events;

import com.samples.songster.dal.search.dto.SongDto;
import com.samples.songster.crosscut.Event;

/**
 * Created by chrisbraunschweiler1 on 09/03/16.
 */
public class ShowLoginViewEvent extends Event<SongDto> {
    public ShowLoginViewEvent(SongDto payload) {
        super(payload);
    }
}
