package com.panoprogramowanie.boilingfrogs.ui.speaker;

import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.ui.base.Presenter;

import javax.inject.Inject;

/**
 * Created by Wojciech on 20.01.2016.
 */
public class SpeakerPresenter extends Presenter<SpeakerActivity> {

    private Speaker speaker;

    private final ScheduleSupplier scheduleSupplier;

    @Inject
    public SpeakerPresenter(ScheduleSupplier scheduleSupplier) {
        this.scheduleSupplier = scheduleSupplier;
    }

    public void setSpeakerId(long speakerId) {
        this.speaker = scheduleSupplier.getSpeakerById(speakerId);
        getView().displaySpeakerData(speaker);
    }
}
