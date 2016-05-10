package com.panoprogramowanie.boilingfrogs.suppliers;

import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;

import android.content.Context;

import java.util.List;

/**
 * Created by Wojciech on 09.01.2016.
 */
public interface ScheduleSupplier{
    Speech getSpeechById(long id);

    List<Speaker> getAllSpeakers();
    Speaker getSpeakerById(long speakerId);

    SpeechSlot getSpeechSlotBytId(long slotId);
    List<SpeechSlot> getAllSpeechSlots();
    void updateSpeechSlot(SpeechSlot speechSlot);
}
