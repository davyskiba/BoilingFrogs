package com.panoprogramowanie.boilingfrogs.ui.view;

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
    public ListItemModelAdapter(Context context, ListItemModel[] objects) {
        super(context, -1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ListItemModelView view;
        if(convertView==null)
        {
            view=(ListItemModelView)inflater.inflate(R.layout.list_item_model,null);
        }
        else {
            view=(ListItemModelView)convertView;
        }

        view.takeModel(getItem(position));

        return view;
    }


}
