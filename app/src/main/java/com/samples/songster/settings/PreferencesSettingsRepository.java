package com.samples.songster.settings;

import com.samples.songster.settings.events.FailedToSaveUserSettingsEvent;
import com.samples.songster.settings.events.LoadUserSettingsEvent;
import com.samples.songster.settings.events.LoadedUserSettingsEvent;
import com.samples.songster.settings.events.SaveUserSettingsEvent;
import com.samples.songster.settings.events.SavedUserSettingsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Random;

/**
 * Created by chrisbraunschweiler1 on 03/03/16.
 */
public class PreferencesSettingsRepository implements SettingsRepository {

    private static final EventBus EVENT_BUS = EventBus.getDefault();
    private final Random mRandom;

    public PreferencesSettingsRepository() {
        mRandom = new Random();
    }

    @Override
    public void start() {
        EVENT_BUS.register(this);
    }

    @Override
    public void pause() {
        EVENT_BUS.unregister(this);
    }

    @Override
    public void saveUserSettings(UserModel userModel, SettingsRepositoryListener listener) {
        EVENT_BUS.post(new SaveUserSettingsEvent(userModel, listener));
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onSaveUserSettings(SaveUserSettingsEvent event) {
        try {
            //Create some latency
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //50% chance of error. Just for fun.
        int randomNumber = mRandom.nextInt(10);
        if (randomNumber % 2 == 0) {
            EVENT_BUS.post(new FailedToSaveUserSettingsEvent(event.getListener()));
        } else {
            EVENT_BUS.post(new SavedUserSettingsEvent(event.getListener()));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailedToSaveUserSettings(FailedToSaveUserSettingsEvent event){
        event.getListener().onFailedToSaveUserSettings();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSavedUserSettings(SavedUserSettingsEvent event) {
        event.getListener().onSavedUserSettings();
    }

    @Override
    public void loadUserSettings(SettingsRepositoryListener listener) {
        EVENT_BUS.post(new LoadUserSettingsEvent(listener));
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onLoadUserSettings(LoadUserSettingsEvent event) {
        try {
            //Create some latency
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Create mock user model. Retrieve it from preferences later.
        UserModel userModel = new UserModel();
        userModel.setFirstName("Charles");
        userModel.setLastName("Barkley");
        userModel.setEmailAddress("smartbark@cocos.net");
        userModel.setUsername("smartbark");
        EVENT_BUS.post(new LoadedUserSettingsEvent(userModel, event.getListener()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadedUserSettings(LoadedUserSettingsEvent event) {
        event.getListener().onLoadedUserSettings(event.getPayload());
    }
}
