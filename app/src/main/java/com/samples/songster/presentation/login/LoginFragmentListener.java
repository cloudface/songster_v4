package com.samples.songster.presentation.login;

import com.samples.songster.dal.login.UserDto;

/**
 * Created by chrisbraunschweiler1 on 16/03/16.
 */
public interface LoginFragmentListener {
    void onLoggedIn(UserDto user);
}
