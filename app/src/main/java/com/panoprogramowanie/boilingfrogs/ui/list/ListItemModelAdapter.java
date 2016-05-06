package com.panoprogramowanie.boilingfrogs.ui.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.panoprogramowanie.boilingfrogs.R;

import java.util.List;

/**
 * Created by Wojciech on 11.01.2016.
 */
public class ListItemModelAdapter extends BaseAdapter {

    private Context context;
    private int listItemLayoutId;

    private ListItemModel[] items;

    public ListItemModelAdapter(Context context, int resId) {
        this(context, resId, new ListItemModel[0]);
    }

    public ListItemModelAdapter(Context context, int resId, ListItemModel[] objects) {
        listItemLayoutId = resId;
        items = objects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public ListItemModel getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position).isClickable();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ListItemModelView view;
        if (convertView == null) {
            view = (ListItemModelView) inflater.inflate(listItemLayoutId, null);
        } else {
            view = (ListItemModelView) convertView;
        }

        view.takeModel(getItem(position));

        return view;
    }

    public void setItems(ListItemModel[] newItems) {
        items = newItems;
        notifyDataSetChanged();
    }
}
