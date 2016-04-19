package com.samples.songster.presentation.settings;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.samples.songster.BR;

/**
 * Created by chrisbraunschweiler1 on 29/02/16.
 */
public class UserModel extends BaseObservable {

    @Bindable
    private String mFirstName;

    @Bindable
    private String mLastName;

    @Bindable
    private String mUsername;

    @Bindable
    private String mPassword;

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public void commitChanges() {
        notifyPropertyChanged(BR.firstName);
        notifyPropertyChanged(BR.lastName);
        notifyPropertyChanged(BR.username);
        notifyPropertyChanged(BR.password);
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }
}
