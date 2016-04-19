package com.samples.songster.dal.settings;

import com.samples.songster.presentation.settings.UserModel;

/**
 * Created by chrisbraunschweiler1 on 03/03/16.
 */
public interface SettingsRepository {
    void start();

    void pause();

    void saveUserSettings(UserModel userModel, SettingsRepositoryListener listener);

    void loadUserSettings(SettingsRepositoryListener listener);

    interface SettingsRepositoryListener {
        void onLoadedUserSettings(UserModel userModel);

        void onSavedUserSettings();

        void onFailedToSaveUserSettings();
    }

    abstract class SettingsRepositoryListenerAdapter implements SettingsRepositoryListener {

        @Override
        public void onLoadedUserSettings(UserModel userModel) {

        }

        @Override
        public void onSavedUserSettings() {

        }

        @Override
        public void onFailedToSaveUserSettings() {

        }
    }
}
