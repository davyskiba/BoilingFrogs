package com.panoprogramowanie.boilingfrogs.ui.schedule;

import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.ui.base.Presenter;

import javax.inject.Inject;

/**
 * Created by wdawi on 24.01.2016.
 */
public class SchedulePresenter extends Presenter<ScheduleFragment> {

    private final ScheduleSupplier scheduleSupplier;

    @Inject
    public SchedulePresenter(ScheduleSupplier scheduleSupplier) {
        this.scheduleSupplier = scheduleSupplier;
    }

    @Override
    public void takeView(ScheduleFragment view) {
        super.takeView(view);
        getView().setData(scheduleSupplier.getAllSpeechSlots());
    }

    public void onResume() {
    }
}
