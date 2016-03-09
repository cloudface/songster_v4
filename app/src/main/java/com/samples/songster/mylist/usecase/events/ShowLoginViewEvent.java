package com.samples.songster.mylist.usecase.events;

import com.samples.songster.search.repository.dto.SongDto;
import com.samples.songster.settings.events.Event;

/**
 * Created by chrisbraunschweiler1 on 09/03/16.
 */
public class ShowLoginViewEvent extends Event<SongDto> {
    public ShowLoginViewEvent(SongDto payload) {
        super(payload);
    }
}
