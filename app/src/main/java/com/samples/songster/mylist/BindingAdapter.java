package com.samples.songster.mylist;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by chrisbraunschweiler1 on 07/03/16.
 */
public abstract class BindingAdapter<T extends ViewDataBinding> extends RecyclerView.Adapter<BindingViewHolder<T>> {

    @Override
    public void onBindViewHolder(BindingViewHolder<T> holder, int position) {
        T binding = holder.getBinding();
        updateBinding(binding, position);
    }

    protected abstract void updateBinding(T binding, int position);
}
