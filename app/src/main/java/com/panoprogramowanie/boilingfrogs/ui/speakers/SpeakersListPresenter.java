package com.panoprogramowanie.boilingfrogs.ui.speakers;

import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.suppliers.NavigationSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.ui.base.Presenter;

/**
 * Created by wdawi on 24.01.2016.
 */
public class SpeakersListPresenter extends Presenter<SpeakersListFragment> {

    private final ScheduleSupplier scheduleSupplier;
    private final NavigationSupplier navigationSupplier;

    public SpeakersListPresenter(ScheduleSupplier scheduleSupplier, NavigationSupplier navigationSupplier) {
        this.scheduleSupplier = scheduleSupplier;
        this.navigationSupplier = navigationSupplier;
    }

    public void onResume() {
        Speaker[] speakers=scheduleSupplier.getAllSpeakers();
        getView().setItems(speakers);
    }

    public void speakerClicked(Speaker clickedSpeaker) {
        navigationSupplier.navigateToSpeaker(clickedSpeaker);
    }
}
