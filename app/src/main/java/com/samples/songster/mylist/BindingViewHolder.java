package com.samples.songster.mylist;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by chrisbraunschweiler1 on 07/03/16.
 */
public class BindingViewHolder <T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    final T mLayoutBinding;

    public BindingViewHolder(T layoutBinding) {
        super(layoutBinding.getRoot());
        mLayoutBinding = layoutBinding;
    }

    public T getBinding() {
        return mLayoutBinding;
    }
}
