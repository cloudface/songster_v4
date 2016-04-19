package com.samples.songster.business.mylist.events;

import com.samples.songster.dal.search.dto.SongDto;
import com.samples.songster.crosscut.Event;

/**
 * Created by chrisbraunschweiler1 on 09/03/16.
 */
public class ShowPurchaseSuccessMessageEvent extends Event<SongDto> {
    public ShowPurchaseSuccessMessageEvent(SongDto payload) {
        super(payload);
    }
}
