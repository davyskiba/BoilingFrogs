package com.panoprogramowanie.boilingfrogs.suppliers;

import android.content.Context;

import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.main.MainActivity;

/**
 * Created by Wojciech on 09.01.2016.
 */
public interface ScheduleSupplier extends IdScheduleSupplier{

    SpeechSlot getSpeechSlotForPosition(int position);

}
