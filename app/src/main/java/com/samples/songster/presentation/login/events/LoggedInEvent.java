package com.samples.songster.presentation.login.events;

import com.samples.songster.dal.login.UserDto;
import com.samples.songster.crosscut.Event;

/**
 * Created by chrisbraunschweiler1 on 13/03/16.
 */
public class LoggedInEvent extends Event<UserDto> {
    public LoggedInEvent(UserDto payload) {
        super(payload);
    }
}
