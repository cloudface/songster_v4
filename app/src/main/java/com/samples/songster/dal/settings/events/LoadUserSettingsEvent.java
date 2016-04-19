package com.samples.songster.dal.settings.events;

import com.samples.songster.dal.settings.SettingsRepository;

/**
 * Created by chrisbraunschweiler1 on 03/03/16.
 */
public class LoadUserSettingsEvent {
    private SettingsRepository.SettingsRepositoryListener mListener;

    public LoadUserSettingsEvent(SettingsRepository.SettingsRepositoryListener listener) {
        this.mListener = listener;
    }

    public SettingsRepository.SettingsRepositoryListener getListener() {
        return mListener;
    }
}
