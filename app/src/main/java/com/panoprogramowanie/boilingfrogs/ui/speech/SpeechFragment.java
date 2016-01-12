package com.panoprogramowanie.boilingfrogs.ui.speech;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragment;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class SpeechFragment extends BoilingFrogsFragment {

    private static String SPEECH_ARG_KEY ="speech_arg";

    public static SpeechFragment createInstance(Speech speech){
        Bundle args=new Bundle();
        args.putParcelable(SPEECH_ARG_KEY, speech);

        SpeechFragment result=new SpeechFragment();
        result.setArguments(args);

        return result;
    }

    @Override
    public String getToolbarTitle(Context context) {
        Speech speech=getArguments().getParcelable(SPEECH_ARG_KEY);
        return speech.getTitle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Speech speech=getArguments().getParcelable(SPEECH_ARG_KEY);
        Log.d("WTF",speech.toString());
        return inflater.inflate(R.layout.fragment_speech,container,false);
    }
}
