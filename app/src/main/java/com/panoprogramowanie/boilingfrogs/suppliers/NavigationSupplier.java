package com.panoprogramowanie.boilingfrogs.suppliers;

import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragmentActivity;

/**
 * Created by Wojciech on 07.01.2016.
 */
public interface NavigationSupplier {

    void unregisterFragmentActivity(BoilingFrogsFragmentActivity activity);

    void registerFragmentActivity(BoilingFrogsFragmentActivity activity);

    void navigateToSpeech(long speechId);

    void navigateToSpeaker(Speaker speaker);

    void navigateToSlotDetail(SpeechSlot speechSlot);

    void navigateToSchedule();

    void navigateToSpeakersList();

    void navigateToMySchedule();

    boolean isContainterEmpty();

    boolean onBackPressed();
}
