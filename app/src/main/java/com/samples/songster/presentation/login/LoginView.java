package com.samples.songster.presentation.login;

import com.samples.songster.dal.login.UserDto;

/**
 * Created by chrisbraunschweiler1 on 13/03/16.
 */
public interface LoginView {
    void onLoggedInSuccessfully(UserDto userDto);
}
