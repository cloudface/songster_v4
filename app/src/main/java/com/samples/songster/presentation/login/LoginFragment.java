package com.samples.songster.presentation.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samples.songster.R;
import com.samples.songster.dal.login.MockUserDataRepository;
import com.samples.songster.dal.login.UserDto;
import com.samples.songster.databinding.FragmentLoginBinding;

/**
 * Created by chrisbraunschweiler1 on 13/03/16.
 */
public class LoginFragment extends DialogFragment implements LoginView {

    private LoginViewModel mViewModel;
    private LoginFragmentListener mListener;

    public static LoginFragment getInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        //TODO pass arguments into bundle
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle args) {
        super.onCreate(args);
        //TODO retrieve arguments from bundle
        mViewModel = new LoginViewModel(new MockUserDataRepository(), this);
        mListener = (LoginFragmentListener) getTargetFragment();
        if (mListener == null) {
            throw new IllegalStateException("Listener of " + LoginFragment.class.getSimpleName() + " cannot be null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentLoginBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setViewModel(mViewModel);
        View view = binding.getRoot();
        getDialog().setTitle(getString(R.string.label_login));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewModel.onPause();
    }

    @Override
    public void onLoggedInSuccessfully(UserDto userDto) {
        mListener.onLoggedIn(userDto);
    }
}
