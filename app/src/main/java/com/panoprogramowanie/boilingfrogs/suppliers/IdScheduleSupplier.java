package com.panoprogramowanie.boilingfrogs.suppliers;

import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;

import android.content.Context;

public interface IdScheduleSupplier {

    SpeechSlot[] getAllSpeechSlots();

    Speaker[] getAllSpeakers();

    Speech getSpeechById(long id);

    void speechSlotsFavoritesUpdated(Context context, SpeechSlot speechSlot);
}
