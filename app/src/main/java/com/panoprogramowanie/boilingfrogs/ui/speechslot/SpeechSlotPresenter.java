package com.panoprogramowanie.boilingfrogs.ui.speechslot;

import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.suppliers.NavigationSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.ui.base.Presenter;

import javax.inject.Inject;

/**
 * Created by wdawi on 24.01.2016.
 */
public class SpeechSlotPresenter extends Presenter<SpeechSlotFragment> {

    private SpeechSlot speechSlot;

    private final NavigationSupplier navigationSupplier;
    private final ScheduleSupplier scheduleSupplier;

    @Inject
    public SpeechSlotPresenter(NavigationSupplier navigationSupplier, ScheduleSupplier scheduleSupplier) {
        this.navigationSupplier = navigationSupplier;
        this.scheduleSupplier = scheduleSupplier;
    }

    public void onResume(long slotId) {
        speechSlot=scheduleSupplier.getSpeechSlotBytId(slotId);
        getView().setItems(speechSlot.getSpeeches());
    }

    public void speechClicked(Speech clickedSpeech) {
        navigationSupplier.navigateToSpeech(clickedSpeech.getId());
    }
}
