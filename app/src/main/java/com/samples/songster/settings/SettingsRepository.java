package com.samples.songster.settings;

/**
 * Created by chrisbraunschweiler1 on 03/03/16.
 */
public interface SettingsRepository {
    void start();

    void pause();

    void saveUserSettings(UserModel userModel);

    void loadUserSettings(SettingsRepositoryListener listener);

    interface SettingsRepositoryListener {
        void onLoadedUserSettings(UserModel userModel);
    }
}
