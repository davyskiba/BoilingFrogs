package com.panoprogramowanie.boilingfrogs.ui.speaker;

import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.ui.base.Presenter;

/**
 * Created by Wojciech on 20.01.2016.
 */
public class SpeakerPresenter extends Presenter<SpeakerActivity> {

    private Speaker speaker;

    public void setSpeaker(Speaker speaker)
    {
        this.speaker=speaker;
        getView().displaySpeakerData(speaker);
    }
}