package com.samples.songster.dal.settings.events;

import com.samples.songster.crosscut.Event;
import com.samples.songster.dal.settings.SettingsRepository;
import com.samples.songster.presentation.settings.UserModel;

/**
 * Created by chrisbraunschweiler1 on 04/03/16.
 */
public class SaveUserSettingsEvent extends Event<UserModel> {
    private SettingsRepository.SettingsRepositoryListener mListener;

    public SaveUserSettingsEvent(UserModel payload, SettingsRepository.SettingsRepositoryListener listener) {
        super(payload);
        this.mListener = listener;
    }

    public SettingsRepository.SettingsRepositoryListener getListener() {
        return mListener;
    }
}
