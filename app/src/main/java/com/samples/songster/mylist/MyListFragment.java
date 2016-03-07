package com.samples.songster.mylist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samples.songster.R;
import com.samples.songster.search.SearchAdapter;

/**
 * Created by chrisbraunschweiler1 on 04/03/16.
 */
public class MyListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyListViewModel mViewModel;

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
        mViewModel = new MyListViewModel();

        //Add some mock data
        MyListItemModel item1 = new MyListItemModel();
        item1.setItemType(MyListItemModel.ItemType.HEADER);
        item1.setSomeText("Hello");
        mViewModel.getMyItems().add(item1);
        MyListItemModel item2 = new MyListItemModel();
        item2.setItemType(MyListItemModel.ItemType.RESULT);
        item2.setSomeText("Nirvana");
        mViewModel.getMyItems().add(item2);
        MyListItemModel item3 = new MyListItemModel();
        item3.setItemType(MyListItemModel.ItemType.RESULT);
        item3.setSomeText("Normal key");
        mViewModel.getMyItems().add(item3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mylist, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.myListRecyclerView);
        configureRecyclerView();
        return view;
    }

    private void configureRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        MyListAdapter myListAdapter = new MyListAdapter(mViewModel.getMyItems());
        mRecyclerView.setAdapter(myListAdapter);
    }
}
