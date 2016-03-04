package com.samples.songster.settings;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.widget.CompoundButton;

import com.samples.songster.BR;

/**
 * Created by chrisbraunschweiler1 on 29/02/16.
 */
public class SettingsViewModel extends BaseObservable {

    @Bindable
    private boolean loading;

    @Bindable
    private boolean editing;

    @Bindable
    private UserModel user;

    @Bindable
    private boolean settingsUpdated;

    @Bindable
    private boolean settingsUpdateSuccessful;

    @Bindable
    private String settingsUpdateMessage;

    private SettingsRepository mRepository;

    public SettingsViewModel(SettingsRepository repository){
        user = new UserModel();
        mRepository = repository;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
        notifyPropertyChanged(BR.user);
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
        notifyPropertyChanged(BR.editing);
        this.user.commitChanges();
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        notifyPropertyChanged(BR.loading);
    }

    public boolean isSettingsUpdated() {
        return settingsUpdated;
    }

    public void setSettingsUpdated(boolean settingsUpdated) {
        this.settingsUpdated = settingsUpdated;
        notifyPropertyChanged(BR.settingsUpdated);
    }

    public boolean isSettingsUpdateSuccessful() {
        return settingsUpdateSuccessful;
    }

    public void setSettingsUpdateSuccessful(boolean settingsUpdateSuccessful) {
        this.settingsUpdateSuccessful = settingsUpdateSuccessful;
        notifyPropertyChanged(BR.settingsUpdateSuccessful);
    }

    public String getSettingsUpdateMessage() {
        return settingsUpdateMessage;
    }

    public void setSettingsUpdateMessage(String settingsUpdateMessage) {
        this.settingsUpdateMessage = settingsUpdateMessage;
        notifyPropertyChanged(BR.settingsUpdateMessage);
    }

    public void onResume(){
        setLoading(true);
        mRepository.start();
        mRepository.loadUserSettings(new LoadedUserSettingsHandler());
    }

    public void onPause() {
        mRepository.pause();
    }

    public CompoundButton.OnCheckedChangeListener getCheckedChangedListener() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setEditing(!isEditing());
                if(!isChecked){
                    mRepository.saveUserSettings(user, new SavedUserSettingsHandler());
                    setSettingsUpdated(true);
                }
            }
        };
    }

    public void onFirstNameTextChanged(CharSequence s, int start, int before, int count) {
        user.setFirstName(s.toString());
    }

    public void onLastNameTextChanged(CharSequence s, int start, int before, int count) {
        user.setLastName(s.toString());
    }

    private class LoadedUserSettingsHandler extends SettingsRepository.SettingsRepositoryListenerAdapter {

        @Override
        public void onLoadedUserSettings(UserModel userModel) {
            setUser(userModel);
            setLoading(false);
        }
    }

    private class SavedUserSettingsHandler extends SettingsRepository.SettingsRepositoryListenerAdapter {

        @Override
        public void onSavedUserSettings() {
            setSettingsUpdateSuccessful(true);
            setSettingsUpdateMessage("Your settings have been saved.");
        }

        @Override
        public void onFailedToSaveUserSettings() {
            //TODO
        }
    }
}