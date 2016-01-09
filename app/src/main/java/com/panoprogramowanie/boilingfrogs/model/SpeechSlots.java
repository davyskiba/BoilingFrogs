package com.panoprogramowanie.boilingfrogs.model;

import com.panoprogramowanie.boilingfrogs.R;

/**
 * Created by Wojciech on 07.01.2016.
 */
public enum SpeechSlots {

    First(R.string.speech_slots_first),
    Second(R.string.speech_slots_second),
    Third(R.string.speech_slots_third),
    Fourth(R.string.speech_slots_fourth),
    Fifth(R.string.speech_slots_fifth),
    Sixth(R.string.speech_slots_sixth);

    private int labedId;

    SpeechSlots(int labelId) {
        this.labedId = labelId;
    }

    public int getLabelId() {
        return labedId;
    }
}