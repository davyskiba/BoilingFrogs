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

    private static final int BREAK_SLOT_VIEW_TYPE =0;
    private static final int EMPTY_SLOT_VIEW_TYPE=1;
    private static final int NONEMPTY_SLOT_VIEW_TYPE=2;

    private OnSlotClickListener onSlotClickListener;

    private SpeechSlot[] speechSlots;

    public MyScheduleRecyclerViewAdapter(SpeechSlot[] speechSlots) {
        this.speechSlots = speechSlots;
    }

    @Override
    public int getItemViewType(int position) {
        SpeechSlot speechSlot=speechSlots[position];
        Speech[] itemSpeeches=speechSlot.getSpeeches();

        if(itemSpeeches.length==1 && itemSpeeches[0].getSpeaker()==null)
        {
            return BREAK_SLOT_VIEW_TYPE;
        }

        if(itemSpeeches.length==1 || speechSlot.getFavoriteSpeechPath()!=-1)
            return NONEMPTY_SLOT_VIEW_TYPE;

        return EMPTY_SLOT_VIEW_TYPE;
    }

    @Override
    public MyScheduleSpeechSlotViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType)
        {
            case BREAK_SLOT_VIEW_TYPE:
                return getBreakSlotViewHolder(parent);
            case EMPTY_SLOT_VIEW_TYPE:
                return getMyScheduleEmptySpeechSlotViewHolder(parent);
            default:
                return getMyScheduleSpeechSlotViewHolder(parent);
        }
    }

    @NonNull
    public MyScheduleBreakViewHolder getBreakSlotViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_break_slot, null);
        MyScheduleBreakViewHolder viewHolder=new MyScheduleBreakViewHolder(view);
        return viewHolder;
    }

    @NonNull
    public MyScheduleEmptySpeechSlotViewHolder getMyScheduleEmptySpeechSlotViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_empty_speech_slot, null);
        MyScheduleEmptySpeechSlotViewHolder viewHolder=new MyScheduleEmptySpeechSlotViewHolder(view);
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
        holder.takeSpeechSlot(speechSlots[position],position,onSlotClickListener);

    }

    @Override
    public int getItemCount() {
        return speechSlots.length;
    }

    public void setOnSlotClickListener(OnSlotClickListener onSlotClickListener) {
        this.onSlotClickListener = onSlotClickListener;
    }

    public SpeechSlot getItem(int slotPosition) {
        return speechSlots[slotPosition];
    }

    public interface OnSlotClickListener{
        void onEmptySlotClicked(int slotPosition);
        void onNonEmptySlotClicked(int slotPosition);
    }
}
