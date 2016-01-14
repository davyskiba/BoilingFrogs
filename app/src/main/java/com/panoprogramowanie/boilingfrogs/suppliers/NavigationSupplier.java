package com.panoprogramowanie.boilingfrogs.suppliers;

import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.Speech;

/**
 * Created by Wojciech on 07.01.2016.
 */
public interface NavigationSupplier {
    void navigateToSpeech(int speechSlot, int speechPath);
    void navigateToSpeaker(Speaker speaker);
    void navigateToSlotDetail(int speechSlot);
}
