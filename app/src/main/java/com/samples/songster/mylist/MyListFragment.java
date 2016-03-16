package com.samples.songster.mylist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.samples.songster.R;
import com.samples.songster.databinding.FragmentMylistBinding;
import com.samples.songster.login.LoginFragment;
import com.samples.songster.login.LoginFragmentListener;
import com.samples.songster.login.UserDto;
import com.samples.songster.mylist.repository.MyListMockRepository;

/**
 * Created by chrisbraunschweiler1 on 04/03/16.
 */
public class MyListFragment extends Fragment implements MyListView, LoginFragmentListener {

    private static final String TAG_LOGIN_FRAGMENT = "LoginFragment";
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyListViewModel mViewModel;
    private MyListAdapter mMyListAdapter;

    public static MyListFragment getInstance() {
        MyListFragment fragment = new MyListFragment();
        Bundle args = new Bundle();
        //TODO pass arguments into bundle
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle args) {
        super.onCreate(args);
        //TODO retrieve arguments from bundle
        mViewModel = new MyListViewModel(new MyListMockRepository(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentMylistBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mylist, container, false);
        binding.setViewModel(mViewModel);
        View view = binding.getRoot();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.myListRecyclerView);
        configureRecyclerView();
        return view;
    }

    private void configureRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mMyListAdapter = new MyListAdapter(mViewModel.getMyItems());
        mRecyclerView.setAdapter(mMyListAdapter);
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
    public void updateRecyclerView() {
        mMyListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoginView() {
        FragmentManager fm = getFragmentManager();
        LoginFragment loginFragment = LoginFragment.getInstance();
        loginFragment.setTargetFragment(this, 0);
        loginFragment.show(fm, TAG_LOGIN_FRAGMENT);
    }

    @Override
    public void onLoggedIn(UserDto user) {
        LoginFragment loginFragment = (LoginFragment) getFragmentManager().findFragmentByTag(TAG_LOGIN_FRAGMENT);
        if(loginFragment != null){
            loginFragment.dismiss();
        }
        mViewModel.onLoggedIn(user);
        Toast.makeText(getActivity(), "Logged in as " + user.getUsername(), Toast.LENGTH_LONG).show();
    }
}
