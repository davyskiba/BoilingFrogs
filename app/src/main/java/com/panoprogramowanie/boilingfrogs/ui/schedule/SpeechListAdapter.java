package com.panoprogramowanie.boilingfrogs.ui.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.ui.view.SpeechListItem;

/**
 * Created by Wojciech on 11.01.2016.
 */
public class SpeechListAdapter extends ArrayAdapter<Speech> {
    public SpeechListAdapter(Context context, Speech[] objects) {
        super(context, -1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        SpeechListItem view;
        if(convertView==null)
        {
            view=(SpeechListItem)inflater.inflate(R.layout.listitem_speech,null);
        }
        else {
            view=(SpeechListItem)convertView;
        }

        view.takeSpeech(getItem(position));

        return view;
    }


}
