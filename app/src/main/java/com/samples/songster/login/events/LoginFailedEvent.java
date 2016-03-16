package com.samples.songster.login.events;

import com.samples.songster.settings.events.Event;

/**
 * Created by chrisbraunschweiler1 on 16/03/16.
 */
public class LoginFailedEvent extends Event<String> {
    public LoginFailedEvent(String payload) {
        super(payload);
    }
}
