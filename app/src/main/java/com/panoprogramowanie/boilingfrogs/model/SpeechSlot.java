package com.panoprogramowanie.boilingfrogs.model;

/**
 * Created by Wojciech on 09.01.2016.
 */
public class SpeechSlot {
    int position;
    String header;

    Speech[] speeches;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Speech[] getSpeeches() {
        return speeches;
    }

    public void setSpeeches(Speech[] speeches) {
        this.speeches = speeches;
    }
}
