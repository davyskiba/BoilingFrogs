package com.panoprogramowanie.boilingfrogs.dagger;

import com.panoprogramowanie.boilingfrogs.ui.main.MainActivity;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.MyScheduleFragment;
import com.panoprogramowanie.boilingfrogs.ui.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wdawi on 24.01.2016.
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MyScheduleFragment myScheduleFragment);

    void inject(MainActivity mainActivity);

    void inject(SplashActivity splashActivity);
}
