package com.panoprogramowanie.boilingfrogs.ui.speech;

import android.content.Context;

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
public class SpeechPresenter extends Presenter<SpeechActivity>{

    private final ScheduleSupplier scheduleSupplier;
    private final NotificationSupplier notificationSupplier;

    private SpeechSlot speechSlot;
    private Speech speech;

    private int speechSlotPosition;
    private int speechPath;

    @Inject
    public SpeechPresenter(ScheduleSupplier scheduleSupplier, NotificationSupplier notificationSupplier) {
        this.scheduleSupplier = scheduleSupplier;
        this.notificationSupplier = notificationSupplier;
    }

    public void setSpeech(int speechSlotPosition, int speechPath){
        this.speechSlotPosition=speechSlotPosition;
        this.speechPath=speechPath;

        speechSlot=scheduleSupplier.getSpeechSlotForPosition(speechSlotPosition);
        speech=speechSlot.getSpeechForPath(speechPath);

        SpeechActivity view=getView();
        view.displaySpeech(speech);
        view.displaySpeakerData(speech.getSpeaker());
        view.displayFavorite(isSpeechFavorite());
    }

    public boolean isSpeechFavorite(){
        return speechSlot.getFavoriteSpeechPath()==speech.getPath();
    }

    public void favoriteClicked() {
        if(isSpeechFavorite())
        {
            speechSlot.setFavoriteSpeechPath(-1);
            displaySnackbar(R.string.speech_removed_from_favorites);
        }
        else {
            speechSlot.setFavoriteSpeechPath(speech.getPath());
            displaySnackbar(R.string.speech_added_to_favorites);
        }

        getView().displayFavorite(isSpeechFavorite());
        
        scheduleSupplier.speechSlotsFavoritesUpdated(getContext());
    }

    private void displaySnackbar(int textId)
    {
        notificationSupplier.showLongLastingSnackbar(getView().getContainerView(),textId);
    }
}
