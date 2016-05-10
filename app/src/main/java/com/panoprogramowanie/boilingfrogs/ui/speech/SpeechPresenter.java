package com.panoprogramowanie.boilingfrogs.ui.speech;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.suppliers.NotificationSupplier;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.ui.base.Presenter;

import javax.inject.Inject;

/**
 * Created by Wojciech on 20.01.2016.
 */
public class SpeechPresenter extends Presenter<SpeechActivity> {

    private final ScheduleSupplier scheduleSupplier;
    private final NotificationSupplier notificationSupplier;

    private SpeechSlot speechSlot;
    private Speech speech;

    @Inject
    public SpeechPresenter(ScheduleSupplier scheduleSupplier, NotificationSupplier notificationSupplier) {
        this.scheduleSupplier = scheduleSupplier;
        this.notificationSupplier = notificationSupplier;
    }

    public void setSpeech(long speechId) {
        speech = scheduleSupplier.getSpeechById(speechId);
        speechSlot = speech.getSpeechSlot();

        SpeechActivity view = getView();
        view.displaySpeech(speech);
        view.displaySpeakerData(speech.getSpeaker());
        view.displayFavorite(isSpeechFavorite());
    }

    public boolean isSpeechFavorite() {
        return speechSlot.getFavoriteSpeechId()==speech.getId();
    }

    public void favoriteClicked() {
        if (isSpeechFavorite()) {
            speechSlot.setFavoriteSpeech(null);
            displaySnackbar(R.string.speech_removed_from_favorites);
        } else {
            speechSlot.setFavoriteSpeech(speech);
            displaySnackbar(R.string.speech_added_to_favorites);
        }

        getView().displayFavorite(isSpeechFavorite());

        scheduleSupplier.updateSpeechSlot(speechSlot);
    }

    private void displaySnackbar(int textId) {
        notificationSupplier.showLongLastingSnackbar(getView().getContainerView(), textId);
    }
}
