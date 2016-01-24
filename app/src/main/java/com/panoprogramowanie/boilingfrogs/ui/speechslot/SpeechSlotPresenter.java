package com.panoprogramowanie.boilingfrogs.ui.speechslot;

import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.suppliers.NavigationSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.SuppliersProvider;
import com.panoprogramowanie.boilingfrogs.ui.base.MvpView;
import com.panoprogramowanie.boilingfrogs.ui.base.Presenter;

/**
 * Created by wdawi on 24.01.2016.
 */
public class SpeechSlotPresenter extends Presenter<SpeechSlotFragment> {

    private SpeechSlot speechSlot;
    private int slotPosition;

    private final NavigationSupplier navigationSupplier;

    public SpeechSlotPresenter(NavigationSupplier navigationSupplier) {
        this.navigationSupplier = navigationSupplier;
    }

    public void onResume(SpeechSlot slot, int slotPosition) {
        speechSlot=slot;
        this.slotPosition=slotPosition;

        getView().setItems(speechSlot.getSpeeches());
    }

    public void speechClicked(Speech clickedSpeech) {
        navigationSupplier.navigateToSpeech(slotPosition, clickedSpeech.getPath());
    }
}