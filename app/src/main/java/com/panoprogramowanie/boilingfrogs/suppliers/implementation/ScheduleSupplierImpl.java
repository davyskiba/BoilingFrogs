package com.panoprogramowanie.boilingfrogs.suppliers.implementation;

import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.util.ModelJsonUtil;

/**
 * Created by Wojciech on 09.01.2016.
 */
public class ScheduleSupplierImpl implements ScheduleSupplier {
    @Override
    public SpeechSlot[] getAllSpeechSlots() {
        return ModelJsonUtil.getSampleSpeechSlots();
    }

    @Override
    public Speaker[] getAllSpeakers() {
        return ModelJsonUtil.getSampleSpeakers();
    }

    @Override
    public Speaker getSpeakerById(int id) {
        return ModelJsonUtil.getSampleSpeaker();
    }
}
