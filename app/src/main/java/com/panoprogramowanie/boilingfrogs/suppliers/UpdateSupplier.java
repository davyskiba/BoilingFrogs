package com.panoprogramowanie.boilingfrogs.suppliers;

import com.panoprogramowanie.boilingfrogs.api.Schedule;
import com.panoprogramowanie.boilingfrogs.api.ScheduleService;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpdateSupplier {

    private final ScheduleSupplier scheduleSupplier;
    private final ScheduleService scheduleService;

    public UpdateSupplier(ScheduleSupplier scheduleSupplier, ScheduleService scheduleService) {
        this.scheduleSupplier = scheduleSupplier;
        this.scheduleService = scheduleService;
    }

    public Observable<Schedule> performUpdate() {
        return scheduleService.getSchedule()
                .retry(5)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(this::saveSchedule)
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void saveSchedule(Schedule schedule) {
        scheduleSupplier.updateSpeeches(schedule.getSpeeches());
        scheduleSupplier.updateSpeakers(schedule.getSpeakers());
        scheduleSupplier.updateSpeechSlotsAndKeepFavorites(schedule.getSpeechSlots());
    }
}
