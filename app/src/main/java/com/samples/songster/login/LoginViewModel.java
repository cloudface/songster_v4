package com.samples.songster.login;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.samples.songster.BR;
import com.samples.songster.login.events.LoggedInEvent;
import com.samples.songster.login.events.LoginEvent;
import com.samples.songster.login.events.LoginFailedEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by chrisbraunschweiler1 on 13/03/16.
 */
public class LoginViewModel extends BaseObservable{
    private static final EventBus EVENT_BUS = EventBus.getDefault();

    @Bindable
    private boolean mLoading;

    @Bindable
    private String mLoginErrorMessage;
    private UserDto mUser;
    private UserDataRepository mUserRepository;
    private LoginView mView;

    public LoginViewModel(UserDataRepository userDataRepository, LoginView loginView){
        mUserRepository = userDataRepository;
        mView = loginView;
        mUser = new UserDto();
    }

    public void onResume(){
        EVENT_BUS.register(this);
    }

    public void onPause(){
        EVENT_BUS.unregister(this);
    }

    public void onClickLogin(View view) {
        setLoading(true);
        EVENT_BUS.post(new LoginEvent());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onLogin(LoginEvent event){
        mUserRepository.login(mUser.getUsername(), mUser.getPassword(), new LoginHandler());
    }

    public void onUsernameTextChanged(CharSequence s, int start, int before, int count) {
        mUser.setUsername(s.toString());
    }

    public void onPasswordTextChanged(CharSequence s, int start, int before, int count) {
        mUser.setPassword(s.toString());
    }

    public UserDto getUser() {
        return mUser;
    }

    public void setUser(UserDto user) {
        this.mUser = user;
    }

    public boolean isLoading() {
        return mLoading;
    }

    public void setLoading(boolean loading) {
        this.mLoading = loading;
        notifyPropertyChanged(BR.loading);
    }

    public String getLoginErrorMessage() {
        return mLoginErrorMessage;
    }

    public void setLoginErrorMessage(String loginErrorMessage) {
        this.mLoginErrorMessage = loginErrorMessage;
        notifyPropertyChanged(BR.loginErrorMessage);
    }

    public class LoginHandler implements UserDataRepositoryListener{

        @Override
        public void onLoginSuccess(UserDto userDto) {
            EVENT_BUS.post(new LoggedInEvent(userDto));
        }

        @Override
        public void onLoginFailed(String message) {
            EVENT_BUS.post(new LoginFailedEvent(message));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoggedIn(LoggedInEvent event){
        setLoading(false);
        mView.onLoggedInSuccessfully(event.getPayload());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginFailed(LoginFailedEvent event){
        setLoading(false);
        setLoginErrorMessage(event.getPayload());
    }
}
