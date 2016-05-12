package com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.viewholder;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.databinding.SpeechSlotEmptyListItemBinding;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.MyScheduleRecyclerViewAdapter;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Wojciech on 13.01.2016.
 */
public class MyScheduleEmptySpeechSlotViewHolder extends MyScheduleSpeechSlotViewHolderBase {

    private MyScheduleRecyclerViewAdapter.OnSlotClickListener onSlotClickListener;

    private SpeechSlotEmptyListItemBinding binding;

    public MyScheduleEmptySpeechSlotViewHolder(SpeechSlotEmptyListItemBinding binding) {
        super(binding.getRoot());

        this.binding=binding;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void takeSpeechSlot(final SpeechSlot speechSlot, final MyScheduleRecyclerViewAdapter.OnSlotClickListener onSlotClickListener) {
        binding.setSpeechSlot(speechSlot);
        this.onSlotClickListener=onSlotClickListener;
    }

    @OnClick(R.id.speech_slot_container)
    public void onSpeechSlotContainerClicked(){
        if(onSlotClickListener!=null){
            onSlotClickListener.onEmptySlotClicked(binding.getSpeechSlot());
        }
    }
}
