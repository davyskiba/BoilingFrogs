package com.panoprogramowanie.boilingfrogs.ui.myschedule.viewholder;

import android.view.View;
import android.widget.TextView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.MyScheduleRecyclerViewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 13.01.2016.
 */
public class MyScheduleEmptySpeechSlotViewHolder extends MyScheduleSpeechSlotViewHolderBase {

    @Bind(R.id.speech_slot_time)
    TextView speechSlotTime;

    SpeechSlot speechSlot;

    public MyScheduleEmptySpeechSlotViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void takeSpeechSlot(final SpeechSlot speechSlot, final int position, final MyScheduleRecyclerViewAdapter.OnSlotClickListener onSlotClickListener) {
        this.speechSlot = speechSlot;

        speechSlotTime.setText(speechSlot.getTimeLabel());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSlotClickListener.onEmptySlotClicked(speechSlot);
            }
        });
    }
}
