package com.panoprogramowanie.boilingfrogs.api;

import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;

public class Schedule {
    private Speaker[] speakers;
    private SpeechSlot[] speechSlots;
    private Speech[] speeches;

    public Schedule(Speaker[] speakers, SpeechSlot[] speechSlots, Speech[] speeches) {
        this.speakers = speakers;
        this.speechSlots = speechSlots;
        this.speeches = speeches;
    }

    public Speaker[] getSpeakers() {
        return speakers;
    }

    public SpeechSlot[] getSpeechSlots() {
        return speechSlots;
    }

    public Speech[] getSpeeches() {
        return speeches;
    }
}
