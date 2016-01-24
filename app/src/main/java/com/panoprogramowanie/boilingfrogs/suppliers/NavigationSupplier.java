package com.panoprogramowanie.boilingfrogs.suppliers;

import android.support.v7.app.AppCompatActivity;

import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragmentActivity;

/**
 * Created by Wojciech on 07.01.2016.
 */
public interface NavigationSupplier {

    void unregisterFragmentActivity(BoilingFrogsFragmentActivity activity);
    void registerFragmentActivity(BoilingFrogsFragmentActivity activity);

    void navigateToSpeech(int speechSlot, int speechPath);
    void navigateToSpeaker(Speaker speaker);
    void navigateToSlotDetail(int speechSlot);

    void navigateToSchedule();

    void navigateToSpeakersList();

    void navigateToMySchedule();

    boolean isContainterEmpty();

    boolean onBackPressed();
}
