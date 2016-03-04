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
    private UserModel user;

    private SettingsRepository mRepository;

    public SettingsViewModel(SettingsRepository repository){
        mRepository = repository;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
        notifyPropertyChanged(BR.editing);
        this.user.commitChanges();
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
            }
        };
    }

    public void onFirstNameTextChanged(CharSequence s, int start, int before, int count) {
        user.setFirstName(s.toString());
    }

    public void onLastNameTextChanged(CharSequence s, int start, int before, int count) {
        user.setLastName(s.toString());
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        notifyPropertyChanged(BR.loading);
    }

    private class LoadedUserSettingsHandler implements SettingsRepository.SettingsRepositoryListener {

        @Override
        public void onLoadedUserSettings(UserModel userModel) {
            user.setFirstName(userModel.getFirstName());
            user.setLastName(userModel.getLastName());
            user.commitChanges();
            setLoading(false);
        }
    }
}