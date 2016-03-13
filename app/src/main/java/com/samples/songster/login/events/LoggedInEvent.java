package com.samples.songster.login.events;

import com.samples.songster.login.UserDto;
import com.samples.songster.settings.events.Event;

/**
 * Created by chrisbraunschweiler1 on 13/03/16.
 */
public class LoggedInEvent extends Event<UserDto> {
    public LoggedInEvent(UserDto payload) {
        super(payload);
    }
}
