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
public class MyScheduleSpeechSlotViewHolder extends MyScheduleSpeechSlotViewHolderBase {

    @Bind(R.id.photo)
    ImageView photo;

    @Bind(R.id.speech_slot_time)
    TextView speechSlotTime;

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.subtitle)
    TextView subtitle;

    SpeechSlot speechSlot;

    public MyScheduleSpeechSlotViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void takeSpeechSlot(SpeechSlot speechSlot, final int position, final MyScheduleRecyclerViewAdapter.OnSlotClickListener onSlotClickListener) {
        this.speechSlot = speechSlot;

        speechSlotTime.setText(speechSlot.getHeader());

        Speech firstSpeech = speechSlot.getSpeechForPath(speechSlot.getFavoriteSpeechPath());
        AvatarLoaderUtil.loadAvatar(
                this.itemView.getContext(), firstSpeech.getPhotoUrl(), photo, R.drawable.avatar_placeholder);
        title.setText(firstSpeech.getTitle());
        subtitle.setText(firstSpeech.getSubtitle());

        if (firstSpeech.getDescription() != null)
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSlotClickListener.onNonEmptySlotClicked(position);
                }
            });
    }
}
