package com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.viewholder;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speech;

/**
 * Created by Wojciech on 21.02.2017.
 */

public class SpeechSlotBindings {


  @BindingAdapter({"bind:speechLocationIfNonEmpty"})
  public static void bindSpeechLocationIfNonEmpty(TextView textView, Speech speech) {
    String text = "";
    if (speech.getDescription() != null) {
      String[] locationNames = textView.getResources().getStringArray(R.array.my_schedule_speech_location);
      text = locationNames[speech.getPath() - 1];
    }

    textView.setText(text);
  }
}
