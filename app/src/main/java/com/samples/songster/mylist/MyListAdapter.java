package com.samples.songster.mylist;

import com.samples.songster.R;
import com.samples.songster.databinding.ItemMyListBinding;

import java.util.List;

/**
 * Created by chrisbraunschweiler1 on 07/03/16.
 */
public class MyListAdapter extends BindingAdapter<ItemMyListBinding> {
    private List<MyListItemModel> mItems;

    public MyListAdapter(List<MyListItemModel> items) {
        super(R.layout.item_my_list);
        mItems = items;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    protected void updateBinding(ItemMyListBinding binding, int position) {
        binding.setItem(mItems.get(position));
    }
}
