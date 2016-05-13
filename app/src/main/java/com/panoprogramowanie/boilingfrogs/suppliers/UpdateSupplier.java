package com.panoprogramowanie.boilingfrogs.suppliers;

import com.panoprogramowanie.boilingfrogs.api.Schedule;
import com.panoprogramowanie.boilingfrogs.api.ScheduleService;

import android.util.Log;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpdateSupplier {

    private final ScheduleSupplier scheduleSupplier;
    private final ScheduleService scheduleService;

    @Inject
    public UpdateSupplier(ScheduleSupplier scheduleSupplier, ScheduleService scheduleService) {
        this.scheduleSupplier = scheduleSupplier;
        this.scheduleService = scheduleService;
    }

    public Observable<Schedule> performUpdate() {
        return scheduleService.getSchedule()
                .retry(5)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(this::saveSchedule)
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Schedule saveSchedule(Schedule schedule){
        scheduleSupplier.updateSpeeches(schedule.getSpeeches());
        scheduleSupplier.updateSpeakers(schedule.getSpeakers());
        scheduleSupplier.updateSpeechSlots(schedule.getSpeechSlots());

        Log.d("FrogsSplash","saved");
        return schedule;
    }
}
