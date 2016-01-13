package com.panoprogramowanie.boilingfrogs.ui.myschedule;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;

/**
 * Created by Wojciech on 13.01.2016.
 */
public class MyScheduleRecyclerViewAdapter extends RecyclerView.Adapter<MyScheduleSpeechSlotViewHolderBase> {

    private static final int EMPTY_SLOT_VIEW_TYPE=0;
    private static final int NONEMPTY_SLOT_VIEW_TYPE=1;

    private SpeechSlot[] speechSlots;

    public MyScheduleRecyclerViewAdapter(SpeechSlot[] speechSlots) {
        this.speechSlots = speechSlots;
    }

    @Override
    public int getItemViewType(int position) {
        Speech[] itemSpeeches=speechSlots[position].getSpeeches();

        if(itemSpeeches.length==1 && itemSpeeches[0].getSpeaker()==null)
        {
            return EMPTY_SLOT_VIEW_TYPE;
        }

        return NONEMPTY_SLOT_VIEW_TYPE;
    }

    @Override
    public MyScheduleSpeechSlotViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType)
        {
            case EMPTY_SLOT_VIEW_TYPE:
                return getEmptySpeechSlotViewHolder(parent);
            default:
                return getMyScheduleSpeechSlotViewHolder(parent);
        }
    }

    @NonNull
    public MyScheduleEmpySpeechSlotViewHolder getEmptySpeechSlotViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_break_slot, null);
        MyScheduleEmpySpeechSlotViewHolder viewHolder=new MyScheduleEmpySpeechSlotViewHolder(view);
        return viewHolder;
    }


    @NonNull
    public MyScheduleSpeechSlotViewHolder getMyScheduleSpeechSlotViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_speech_slot, null);
        MyScheduleSpeechSlotViewHolder viewHolder=new MyScheduleSpeechSlotViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyScheduleSpeechSlotViewHolderBase holder, int position) {
        holder.takeSpeechSlot(speechSlots[position]);

    }

    @Override
    public int getItemCount() {
        return speechSlots.length;
    }
}
