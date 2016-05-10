package com.panoprogramowanie.boilingfrogs.ui.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.panoprogramowanie.boilingfrogs.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wojciech on 11.01.2016.
 */
public class ListItemModelAdapter extends BaseAdapter {

    private Context context;
    private int listItemLayoutId;

    private List<? extends ListItemModel> items;

    public ListItemModelAdapter(Context context, int resId) {
        this(context, resId, new LinkedList<ListItemModel>());
    }

    public ListItemModelAdapter(Context context, int resId, List<? extends ListItemModel> objects) {
        listItemLayoutId = resId;
        items = objects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ListItemModel getItem(int position) {
        return items.get(position);
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

    public void setItems(List<? extends ListItemModel> newItems) {
        items = newItems;
        notifyDataSetChanged();
    }
}
