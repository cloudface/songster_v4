package com.samples.songster.presentation.login.events;

import com.samples.songster.crosscut.Event;

/**
 * Created by chrisbraunschweiler1 on 16/03/16.
 */
public class LoginFailedEvent extends Event<String> {
    public LoginFailedEvent(String payload) {
        super(payload);
    }
}
