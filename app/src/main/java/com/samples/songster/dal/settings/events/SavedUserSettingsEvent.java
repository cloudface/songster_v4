package com.samples.songster.dal.settings.events;

import com.samples.songster.dal.settings.SettingsRepository;

/**
 * Created by chrisbraunschweiler1 on 04/03/16.
 */
public class SavedUserSettingsEvent {
    private SettingsRepository.SettingsRepositoryListener mListener;

    public SavedUserSettingsEvent(SettingsRepository.SettingsRepositoryListener listener) {

        this.mListener = listener;
    }

    public SettingsRepository.SettingsRepositoryListener getListener() {
        return mListener;
    }
}
