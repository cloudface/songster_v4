package com.samples.songster.login;

import com.samples.songster.search.repository.dto.SongDto;

/**
 * Created by chrisbraunschweiler1 on 13/03/16.
 */
public interface UserDataRepositoryListener {
    void onLoginSuccess(UserDto userDto);
}
