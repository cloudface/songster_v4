package com.samples.songster.login;

/**
 * Created by chrisbraunschweiler1 on 23/11/15.
 */
public class MockUserDataRepository implements UserDataRepository {
    @Override
    public boolean isLoggedIn() {
        return false;
    }

    @Override
    public void login(String username, String password, UserDataRepositoryListener listener) {
        try {
            //Create some latency
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        listener.onLoginSuccess(userDto);
    }

    @Override
    public UserDto getLoggedInUser() {
        UserDto userDto = new UserDto();
        userDto.setUsername("BertBernankee");
        userDto.setPassword("bertisgreat123");
        return userDto;
    }
}
