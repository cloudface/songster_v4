package com.samples.songster.presentation.mylist;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.samples.songster.R;
import com.samples.songster.databinding.ItemMylistHeaderBinding;
import com.samples.songster.databinding.ItemMylistResultBinding;

import java.util.List;

/**
 * Created by chrisbraunschweiler1 on 07/03/16.
 */
public class MyListAdapter extends BindingAdapter<ViewDataBinding> {
    private List<MyListItemModel> mItems;

    public MyListAdapter(List<MyListItemModel> items) {
        mItems = items;
    }

    @Override
    public BindingViewHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        MyListItemModel.ItemType itemType = MyListItemModel.ItemType.values()[viewType];
        switch (itemType) {
            case HEADER:
                binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_mylist_header, parent, false);
                break;
            default:
                binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_mylist_result, parent, false);
                break;
        }
        return new BindingViewHolder<>(binding);
    }

    public int getItemViewType(int position) {
        MyListItemModel item = mItems.get(position);
        return item.getItemType().ordinal();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    protected void updateBinding(ViewDataBinding binding, int position) {
        MyListItemModel item = mItems.get(position);
        switch (item.getItemType()) {
            case HEADER:
                ((ItemMylistHeaderBinding) binding).setItem(mItems.get(position));
                break;
            default:
                ((ItemMylistResultBinding) binding).setItem(mItems.get(position));
                break;
        }
    }
}
