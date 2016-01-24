package com.panoprogramowanie.boilingfrogs.ui.myschedule;

import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.suppliers.NavigationSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.SuppliersProvider;
import com.panoprogramowanie.boilingfrogs.ui.base.Presenter;

import javax.inject.Inject;

/**
 * Created by wdawi on 24.01.2016.
 */
public class MySchedulePresenter extends Presenter<MyScheduleFragment> {
    private final ScheduleSupplier scheduleSupplier;
    private final NavigationSupplier navigationSupplier;

    @Inject
    public MySchedulePresenter(ScheduleSupplier scheduleSupplier, NavigationSupplier navigationSupplier) {
        this.scheduleSupplier = scheduleSupplier;
        this.navigationSupplier = navigationSupplier;
    }

    public void onResume() {
        getView().setSpeechSlots(scheduleSupplier.getAllSpeechSlots());
    }

    public void onEmptySlotClicked(int slotPosition) {
        navigationSupplier.navigateToSlotDetail(slotPosition);
    }

    public void onNonEmptySlotClicked(int slotPosition, SpeechSlot tappedSlot) {
        navigationSupplier.navigateToSpeech(slotPosition, tappedSlot.getFavoriteSpeechPath());
    }
}
