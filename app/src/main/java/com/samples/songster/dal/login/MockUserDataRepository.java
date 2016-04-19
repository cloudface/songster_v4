package com.samples.songster.dal.login;

import java.util.Random;

/**
 * Created by chrisbraunschweiler1 on 23/11/15.
 */
public class MockUserDataRepository implements UserDataRepository {
    private final Random mRandom;

    public MockUserDataRepository(){
        mRandom = new Random();
    }

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

        //Randomly decide if the login succeeded or failed
        int randomNumber = mRandom.nextInt(10);
        if (randomNumber % 2 == 0) {
            listener.onLoginFailed("Incorrect username/password");
        } else {
            UserDto userDto = new UserDto();
            userDto.setUsername(username);
            userDto.setPassword(password);
            listener.onLoginSuccess(userDto);
        }
    }

    @Override
    public UserDto getLoggedInUser() {
        UserDto userDto = new UserDto();
        userDto.setUsername("BertBernankee");
        userDto.setPassword("bertisgreat123");
        return userDto;
    }
}
