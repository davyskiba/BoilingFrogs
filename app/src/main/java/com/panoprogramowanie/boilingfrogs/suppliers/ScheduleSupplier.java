package com.panoprogramowanie.boilingfrogs.suppliers;

import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;

/**
 * Created by Wojciech on 09.01.2016.
 */
public interface ScheduleSupplier {
    SpeechSlot[] getAllSpeechSlots();

    Speaker[] getAllSpeakers();
    Speaker getSpeakerById(int id);
}
