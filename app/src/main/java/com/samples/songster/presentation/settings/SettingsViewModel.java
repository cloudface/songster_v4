package com.samples.songster.presentation.settings;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.samples.songster.BR;
import com.samples.songster.dal.settings.SettingsRepository;

/**
 * Created by chrisbraunschweiler1 on 29/02/16.
 */
public class SettingsViewModel extends BaseObservable {

    @Bindable
    private boolean mUserRegistered;

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

    @Bindable
    private String mUsernameError;

    @Bindable
    private String mPasswordError;

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

    public boolean isUserRegistered() {
        return mUserRegistered;
    }

    public void setUserRegistered(boolean userRegistered) {
        this.mUserRegistered = userRegistered;
        notifyPropertyChanged(BR.userRegistered);
    }

    public String getUsernameError() {
        return mUsernameError;
    }

    public void setUsernameError(String usernameError) {
        this.mUsernameError = usernameError;
        notifyPropertyChanged(BR.usernameError);
    }

    public String getPasswordError() {
        return mPasswordError;
    }

    public void setPasswordError(String passwordError) {
        this.mPasswordError = passwordError;
        notifyPropertyChanged(BR.passwordError);
    }

    public void onResume(){
        setLoading(true);
        mRepository.start();
        mRepository.loadUserSettings(new LoadedUserSettingsHandler());
    }

    public void onPause() {
        mRepository.pause();
    }

    public void onStartRegistration(View view) {
        setEditing(true);
    }

    public void onFirstNameTextChanged(CharSequence s, int start, int before, int count) {
        user.setFirstName(s.toString());
    }

    public void onLastNameTextChanged(CharSequence s, int start, int before, int count) {
        user.setLastName(s.toString());
    }

    public void onUsernameTextChanged(CharSequence s, int start, int before, int count) {
        user.setUsername(s.toString());
    }

    public void onPasswordTextChanged(CharSequence s, int start, int before, int count) {
        user.setPassword(s.toString());
    }

    public void onClickSave(View view) {
        //Perform some validation (such as checking if user name empty or not)
        if(user.getUsername() != null && !user.getUsername().isEmpty() &&
            user.getPassword() != null && !user.getPassword().isEmpty()){
            setUsernameError(null);
            setPasswordError(null);
            setLoading(true);
            mRepository.saveUserSettings(user, new SavedUserSettingsHandler());
        } else{
            if(user.getUsername() == null || user.getUsername().isEmpty()){
                setUsernameError("Please enter a username");
            }
            if(user.getPassword() == null || user.getPassword().isEmpty()){
                setPasswordError("Please enter a password");
            }
        }
    }

    public void onEditSettings(View view) {
        setEditing(true);
    }

    private class LoadedUserSettingsHandler extends SettingsRepository.SettingsRepositoryListenerAdapter {

        @Override
        public void onLoadedUserSettings(UserModel userModel) {
            setUser(userModel);
            setLoading(false);
            if(userModel.getUsername() != null){
                setUserRegistered(true);
            } else {
                setUserRegistered(false);
            }
        }
    }

    private class SavedUserSettingsHandler extends SettingsRepository.SettingsRepositoryListenerAdapter {

        @Override
        public void onSavedUserSettings() {
            setUserRegistered(true);
            setLoading(false);
            setEditing(false);
            setSettingsUpdated(true);
            setSettingsUpdateSuccessful(true);
            setSettingsUpdateMessage("Your settings have been saved.");
            user.commitChanges();
        }

        @Override
        public void onFailedToSaveUserSettings() {
            setLoading(false);
            setSettingsUpdated(true);
            setSettingsUpdateSuccessful(false);
            setSettingsUpdateMessage("Failed to save your settings :(. Please try again.");
        }
    }
}