package com.panoprogramowanie.boilingfrogs.ui.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Wojciech on 11.01.2016.
 */
public class ListItemModelRecyclerViewAdapter extends RecyclerView.Adapter<ListItemViewHolder> {

    private Context context;
    private int listItemLayoutId;

    private List<? extends ListItemModel> items;

    public ListItemModelRecyclerViewAdapter(Context context, int resId) {
        this(context, resId, new LinkedList<ListItemModel>());
    }

    public ListItemModelRecyclerViewAdapter(Context context, int resId, List<? extends ListItemModel> objects) {
        listItemLayoutId = resId;
        items = objects;
        this.context = context;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),listItemLayoutId,parent,false);
        return new ListItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        holder.setListItemModel(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<? extends ListItemModel> newItems) {
        items = newItems;
        notifyDataSetChanged();
    }
}
