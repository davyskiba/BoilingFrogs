package com.panoprogramowanie.boilingfrogs.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speech;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 11.01.2016.
 */
public class SpeechListItem extends RelativeLayout {

    @Bind(R.id.text)
    TextView text;


    private Speech speech;

    public SpeechListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void takeSpeech(Speech speech)
    {
        this.speech=speech;
        text.setText(speech.getTitle());
    }

    public Speech getSpeech() {
        return speech;
    }
}
