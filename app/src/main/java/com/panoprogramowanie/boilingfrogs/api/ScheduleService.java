package com.panoprogramowanie.boilingfrogs.api;

import retrofit2.http.GET;
import rx.Observable;

public interface ScheduleService {
    @GET("agenda.json")
    Observable<Schedule> getSchedule();
}
