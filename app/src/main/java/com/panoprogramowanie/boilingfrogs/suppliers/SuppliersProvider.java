package com.panoprogramowanie.boilingfrogs.suppliers;

/**
 * Created by Wojciech on 09.01.2016.
 */
public interface SuppliersProvider {
    NavigationSupplier provideNavigator();
    ScheduleSupplier provideScheduleSupplier();
}
