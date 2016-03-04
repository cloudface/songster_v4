package com.samples.songster.settings.events;

import com.samples.songster.settings.SettingsRepository;

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
