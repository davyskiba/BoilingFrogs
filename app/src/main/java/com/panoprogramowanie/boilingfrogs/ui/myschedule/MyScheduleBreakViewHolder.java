package com.panoprogramowanie.boilingfrogs.ui.myschedule;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.util.AvatarLoaderUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 13.01.2016.
 */
public class MyScheduleBreakViewHolder extends MyScheduleSpeechSlotViewHolderBase {

    @Bind(R.id.speech_slot_time)
    TextView speechSlotTime;

    @Bind(R.id.title)
    TextView title;

    SpeechSlot speechSlot;

    public MyScheduleBreakViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void takeSpeechSlot(SpeechSlot speechSlot) {
        this.speechSlot=speechSlot;

        speechSlotTime.setText(speechSlot.getHeader());

        Speech firstSpeech = speechSlot.getSpeeches()[0];
        title.setText(firstSpeech.getTitle());
    }
}
