package com.panoprogramowanie.boilingfrogs.model;

/**
 * Created by Wojciech on 09.01.2016.
 */
public class Schedule {
    Speaker[] speakers;
    SpeechSlot[] speechSlots;

    public Speaker[] getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Speaker[] speakers) {
        this.speakers = speakers;
    }

    public SpeechSlot[] getSpeechSlots() {
        return speechSlots;
    }

    public void setSpeechSlots(SpeechSlot[] speechSlots) {
        this.speechSlots = speechSlots;
    }
}
