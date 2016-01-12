package com.panoprogramowanie.boilingfrogs.ui.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.panoprogramowanie.boilingfrogs.R;

/**
 * Created by Wojciech on 11.01.2016.
 */
public class ListItemModelAdapter extends ArrayAdapter<ListItemModel> {

    private int listItemLayoutId;

    public ListItemModelAdapter(Context context,int resId, ListItemModel[] objects) {
        super(context, resId, objects);
        listItemLayoutId=resId;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position).isClickable();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ListItemModelView view;
        if(convertView==null)
        {
            view=(ListItemModelView)inflater.inflate(listItemLayoutId,null);
        }
        else {
            view=(ListItemModelView)convertView;
        }

        view.takeModel(getItem(position));

        return view;
    }


}
