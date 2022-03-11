package com.panoprogramowanie.boilingfrogs.ui.list;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.panoprogramowanie.boilingfrogs.databinding.ListItemBinding;

public class ListItemViewHolder extends RecyclerView.ViewHolder {

    private ListItemBinding binding;
    private ListItemModelRecyclerViewAdapter.OnItemClickListener onItemClickListener;

    public ListItemViewHolder(ListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewClicked();
            }
        });
    }

    public void setListItemModel(ListItemModel itemModel, ListItemModelRecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        binding.setItemModel(itemModel);
        this.onItemClickListener = onItemClickListener;
    }

    public void onViewClicked() {
        ListItemModel itemModel = binding.getItemModel();
        if (itemModel.isClickable() && onItemClickListener != null) {
            onItemClickListener.onItemClick(itemModel);
        }
    }
}
