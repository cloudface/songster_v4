package com.samples.songster.business.mylist.events;

import com.samples.songster.dal.search.dto.SongDto;
import com.samples.songster.crosscut.Event;

/**
 * Created by chrisbraunschweiler1 on 09/03/16.
 */
public class PurchaseSongEvent extends Event<SongDto> {
    public PurchaseSongEvent(SongDto payload) {
        super(payload);
    }
}
