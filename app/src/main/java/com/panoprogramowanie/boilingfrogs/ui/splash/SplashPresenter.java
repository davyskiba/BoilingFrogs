package com.panoprogramowanie.boilingfrogs.ui.splash;

import com.panoprogramowanie.boilingfrogs.api.Schedule;
import com.panoprogramowanie.boilingfrogs.suppliers.NavigationSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.UpdateSupplier;
import com.panoprogramowanie.boilingfrogs.ui.base.Presenter;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;

public class SplashPresenter extends Presenter<SplashActivity> {

    private static int SPLASH_TIME_OUT = 500;

    private final UpdateSupplier updateSupplier;
    private final NavigationSupplier navigationSupplier;

    @Inject
    public SplashPresenter(UpdateSupplier updateSupplier, NavigationSupplier navigationSupplier) {
        this.updateSupplier = updateSupplier;
        this.navigationSupplier = navigationSupplier;
    }

    @Override
    public void takeView(SplashActivity view) {
        super.takeView(view);
        Observable.zip(updateSupplier.performUpdate(),
                        Observable.interval(SPLASH_TIME_OUT, TimeUnit.MILLISECONDS),
                        this::zipScheduleWithInterval)
                .subscribe((schedule)->navigateToMainActivity(),
                        (error)->navigateToMainActivity());
    }

    private Schedule zipScheduleWithInterval(Schedule schedule, Long interval){
        return schedule;
    }

    public void navigateToMainActivity(){
        navigationSupplier.navigateToMain(getContext());
    }
}
