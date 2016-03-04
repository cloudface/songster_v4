package com.samples.songster.settings;

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
    private String mUsername;
    private String mEmailAddress;

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getEmailAddress() {
        return mEmailAddress;
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

    public void setEmailAddress(String emailAddress) {
        this.mEmailAddress = emailAddress;
    }

    public void commitChanges() {
        notifyPropertyChanged(BR.firstName);
        notifyPropertyChanged(BR.lastName);
    }
}
