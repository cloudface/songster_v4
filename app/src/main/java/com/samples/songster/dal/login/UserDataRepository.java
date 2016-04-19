package com.samples.songster.dal.login;

/**
 * Created by chrisbraunschweiler1 on 23/11/15.
 */
public interface UserDataRepository {
    boolean isLoggedIn();

    void login(String username, String password, UserDataRepositoryListener listener);

    UserDto getLoggedInUser();
}
