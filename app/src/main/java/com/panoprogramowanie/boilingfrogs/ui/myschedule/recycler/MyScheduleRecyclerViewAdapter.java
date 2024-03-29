package com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.databinding.SpeechSlotEmptyListItemBinding;
import com.panoprogramowanie.boilingfrogs.databinding.SpeechSlotListItemBinding;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.viewholder.MyScheduleEmptySpeechSlotViewHolder;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.viewholder.MyScheduleSpeechSlotViewHolder;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.viewholder.MyScheduleSpeechSlotViewHolderBase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wojciech on 13.01.2016.
 */
public class MyScheduleRecyclerViewAdapter extends RecyclerView.Adapter<MyScheduleSpeechSlotViewHolderBase> {

    private static final int EMPTY_SLOT_VIEW_TYPE = 0;
    private static final int NONEMPTY_SLOT_VIEW_TYPE = 1;

    private OnSlotClickListener onSlotClickListener;

    private List<SpeechSlot> speechSlots;

    public MyScheduleRecyclerViewAdapter() {
        speechSlots = new LinkedList<>();
    }

    @Override
    public int getItemViewType(int position) {
        SpeechSlot speechSlot = speechSlots.get(position);
        List<Speech> speechList = speechSlot.getSpeechList();

        if (speechList.size() == 1 || speechSlot.getFavoriteSpeech() != null)
            return NONEMPTY_SLOT_VIEW_TYPE;

        return EMPTY_SLOT_VIEW_TYPE;
    }

    //region ViewHolders

    @Override
    public MyScheduleSpeechSlotViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EMPTY_SLOT_VIEW_TYPE:
                return getMyScheduleEmptySpeechSlotViewHolder(parent);
            default:
                return getMyScheduleSpeechSlotViewHolder(parent);
        }
    }

    @NonNull
    public MyScheduleEmptySpeechSlotViewHolder getMyScheduleEmptySpeechSlotViewHolder(ViewGroup parent) {
        SpeechSlotEmptyListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.speech_slot_empty_list_item, null, false);
        MyScheduleEmptySpeechSlotViewHolder viewHolder = new MyScheduleEmptySpeechSlotViewHolder(binding);
        return viewHolder;
    }

    @NonNull
    public MyScheduleSpeechSlotViewHolder getMyScheduleSpeechSlotViewHolder(ViewGroup parent) {
        SpeechSlotListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.speech_slot_list_item, null, false);
        MyScheduleSpeechSlotViewHolder viewHolder = new MyScheduleSpeechSlotViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyScheduleSpeechSlotViewHolderBase holder, int position) {
        holder.takeSpeechSlot(speechSlots.get(position), onSlotClickListener);
    }

    //endregion

    @Override
    public int getItemCount() {
        return speechSlots.size();
    }

    public SpeechSlot getItem(int slotPosition) {
        return speechSlots.get(slotPosition);
    }

    public void setItems(List<SpeechSlot> items) {
        speechSlots = items;
        notifyDataSetChanged();
    }

    //region OnSlotClickListener

    public void setOnSlotClickListener(OnSlotClickListener onSlotClickListener) {
        this.onSlotClickListener = onSlotClickListener;
    }

    public interface OnSlotClickListener {
        void onEmptySlotClicked(SpeechSlot slot);

        void onNonEmptySlotClicked(long speechId);
    }

    //endregion
}
