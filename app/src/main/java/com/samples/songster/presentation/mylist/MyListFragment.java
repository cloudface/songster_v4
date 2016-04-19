package com.samples.songster.presentation.mylist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.samples.songster.R;
import com.samples.songster.databinding.FragmentMylistBinding;
import com.samples.songster.presentation.login.LoginFragment;
import com.samples.songster.presentation.login.LoginFragmentListener;
import com.samples.songster.dal.login.UserDto;
import com.samples.songster.dal.mylist.MyListMockRepository;

/**
 * Created by chrisbraunschweiler1 on 04/03/16.
 */
public class MyListFragment extends Fragment implements MyListView, LoginFragmentListener {

    private static final String KEY_VIEW_MODEL = "com.samples.songster.presentation.mylist.MyListFragment.ViewModel";
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
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_VIEW_MODEL, mViewModel);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            //probably orientation change
            mViewModel = savedInstanceState.getParcelable(KEY_VIEW_MODEL);
            if(mViewModel != null) {
                mViewModel.setView(this);
            }
        }

        configureRecyclerView();
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
