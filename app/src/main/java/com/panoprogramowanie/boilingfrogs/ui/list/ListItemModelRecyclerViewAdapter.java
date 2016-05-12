package com.panoprogramowanie.boilingfrogs.ui.list;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.databinding.ListItemBinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wojciech on 11.01.2016.
 */
public class ListItemModelRecyclerViewAdapter extends RecyclerView.Adapter<ListItemViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<? extends ListItemModel> items;

    public ListItemModelRecyclerViewAdapter() {
        items = new LinkedList<>();
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemBinding binding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item,parent,false);
        return new ListItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        holder.setListItemModel(items.get(position),onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<? extends ListItemModel> newItems) {
        items = newItems;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
            void onItemClick(ListItemModel clickedItem);
    }
}
