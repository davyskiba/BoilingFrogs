package com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.viewholder;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.databinding.SpeechSlotListItemBinding;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.MyScheduleRecyclerViewAdapter;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Wojciech on 13.01.2016.
 */
public class MyScheduleSpeechSlotViewHolder extends MyScheduleSpeechSlotViewHolderBase {
    private MyScheduleRecyclerViewAdapter.OnSlotClickListener onSlotClickListener;
    private SpeechSlotListItemBinding binding;

    public MyScheduleSpeechSlotViewHolder(SpeechSlotListItemBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void takeSpeechSlot(SpeechSlot speechSlot, final MyScheduleRecyclerViewAdapter.OnSlotClickListener onSlotClickListener) {
        binding.setSpeechSlot(speechSlot);
        this.onSlotClickListener=onSlotClickListener;
    }

    @OnClick(R.id.speech_slot_container)
    public void onSpeechSlotClicked(){
        Speech speech=binding.getSpeechSlot().getFavoriteSpeech();
        if(speech.getDescription()!=null && onSlotClickListener!=null){
            onSlotClickListener.onNonEmptySlotClicked(speech.getId());
        }
    }
}
