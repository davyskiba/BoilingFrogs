package com.panoprogramowanie.boilingfrogs.ui.myschedule.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.MyScheduleRecyclerViewAdapter;
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

    @Bind(R.id.speech_slot_path)
    TextView speechSlotPath;

    SpeechSlot speechSlot;

    public MyScheduleSpeechSlotViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void takeSpeechSlot(SpeechSlot speechSlot, final MyScheduleRecyclerViewAdapter.OnSlotClickListener onSlotClickListener) {
        this.speechSlot = speechSlot;

        speechSlotTime.setText(speechSlot.getTimeLabel());

        final Speech speech = speechSlot.getFavoriteSpeech();
        AvatarLoaderUtil.loadAvatar(
                speech.getSpeaker().getPhotoUrl(), photo, R.drawable.avatar_placeholder);
        title.setText(speech.getTitle());
        subtitle.setText(speech.getSpeaker().getName());

        if (speech.getDescription() != null) {
            speechSlotPath.setText(itemView.getContext().getString(R.string.my_schedule_path) + " " + speech.getPath());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSlotClickListener.onNonEmptySlotClicked(speech.getId());
                }
            });
        }
    }
}
