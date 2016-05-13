package com.panoprogramowanie.boilingfrogs.api;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface ScheduleService {
    @GET("/schedule")
    Observable<Schedule> getSchedule();
}
