package com.panoprogramowanie.boilingfrogs.ui.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.databinding.ListitemDrawerBinding;

/**
 * Created by bgorkowy on 27.10.15.
 */
public class DrawerAdapter extends BaseAdapter {

    private Context context;
    NavigationDrawerListEntry[] entries;
    private int currentlySelected = 0;

    public DrawerAdapter(Context context, NavigationDrawerListEntry[] entries) {
        this.context = context;
        this.entries = entries;
    }

    @Override
    public int getCount() {
        return entries.length;
    }

    @Override
    public NavigationDrawerListEntry getItem(int position) {
        return entries[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        NavigationDrawerListEntry currentEntry = getItem(position);

        ListitemDrawerBinding binding;
        if (convertView != null) {
            binding = (ListitemDrawerBinding) convertView.getTag();
        } else {
            binding= DataBindingUtil.inflate(inflater, R.layout.listitem_drawer, parent, false);
            binding.getRoot().setTag(binding);
        }

        binding.setEntry(currentEntry);
        binding.setIsSelected(currentlySelected == position);

        binding.executePendingBindings();

        return binding.getRoot();
    }

    public void setData(NavigationDrawerListEntry[] entries) {
        this.entries = entries;
        notifyDataSetChanged();
    }

    public void setCurrentlySelected(int currentlySelected) {
        this.currentlySelected = currentlySelected;
    }

    public interface NavigationDrawerListEntry {
        int getLabelId();
    }
}