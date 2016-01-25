package com.panoprogramowanie.boilingfrogs.dagger;

import com.panoprogramowanie.boilingfrogs.ui.main.MainActivity;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.MyScheduleFragment;
import com.panoprogramowanie.boilingfrogs.ui.schedule.ScheduleFragment;
import com.panoprogramowanie.boilingfrogs.ui.speaker.SpeakerActivity;
import com.panoprogramowanie.boilingfrogs.ui.speakers.SpeakersListFragment;
import com.panoprogramowanie.boilingfrogs.ui.speech.SpeechActivity;
import com.panoprogramowanie.boilingfrogs.ui.speechslot.SpeechSlotFragment;
import com.panoprogramowanie.boilingfrogs.ui.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wdawi on 24.01.2016.
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
    void inject(SpeakerActivity speakerActivity);
    void inject(SpeechActivity speechActivity);

    void inject(ScheduleFragment scheduleFragment);
    void inject(SpeechSlotFragment speechSlotFragment);
    void inject(MyScheduleFragment myScheduleFragment);
    void inject(SpeakersListFragment speakersListFragment);
}
