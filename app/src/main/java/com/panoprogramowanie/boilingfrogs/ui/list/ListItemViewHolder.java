package com.panoprogramowanie.boilingfrogs.ui.list;

import com.android.databinding.library.baseAdapters.BR;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ListItemViewHolder extends RecyclerView.ViewHolder {

    ViewDataBinding binding;

    public ListItemViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());

        this.binding=binding;
    }

    public void setListItemModel(ListItemModel itemModel){
        binding.setVariable(BR.itemModel,itemModel);
    }
}
