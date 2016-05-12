package com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.MyScheduleRecyclerViewAdapter;

/**
 * Created by Wojciech on 13.01.2016.
 */
public abstract class MyScheduleSpeechSlotViewHolderBase extends RecyclerView.ViewHolder {
    public MyScheduleSpeechSlotViewHolderBase(View itemView) {
        super(itemView);
    }

    public abstract void takeSpeechSlot(SpeechSlot speechSlot, MyScheduleRecyclerViewAdapter.OnSlotClickListener onSlotClickListener);
}
