package com.panoprogramowanie.boilingfrogs.dagger;

import android.content.Context;

import com.panoprogramowanie.boilingfrogs.suppliers.NavigationSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.NotificationSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.implementation.GreenDaoScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.implementation.NavigationSupplierImpl;
import com.panoprogramowanie.boilingfrogs.suppliers.implementation.NotificationSupplierImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wdawi on 24.01.2016.
 */
@Module
public class MainModule {

    private ScheduleSupplier scheduleSupplier;

    public MainModule(Context context) {
        scheduleSupplier = new GreenDaoScheduleSupplier(context);
    }

    @Provides
    ScheduleSupplier provideScheduleSupplier() {
        return scheduleSupplier;
    }

    @Singleton
    @Provides
    NavigationSupplier provideNavigationSupplier(ScheduleSupplier scheduleSupplier) {
        return new NavigationSupplierImpl(scheduleSupplier);
    }

    @Provides
    NotificationSupplier provideNotificationSupplier() {
        return new NotificationSupplierImpl();
    }
}
