package com.samples.songster.dal.login;

/**
 * Created by chrisbraunschweiler1 on 13/03/16.
 */
public interface UserDataRepositoryListener {
    void onLoginSuccess(UserDto userDto);

    void onLoginFailed(String message);
}
