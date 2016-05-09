package com.panoprogramowanie.boilingfrogs.model.greendao;

public class Schedule {
    Speaker[] speakers;
    Speech[] speeches;
    SpeechSlot[] speechSlots;

    public Speaker[] getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Speaker[] speakers) {
        this.speakers = speakers;
    }

    public Speech[] getSpeeches() {
        return speeches;
    }

    public void setSpeeches(Speech[] speeches) {
        this.speeches = speeches;
    }

    public SpeechSlot[] getSpeechSlots() {
        return speechSlots;
    }

    public void setSpeechSlots(SpeechSlot[] speechSlots) {
        this.speechSlots = speechSlots;
    }
}
