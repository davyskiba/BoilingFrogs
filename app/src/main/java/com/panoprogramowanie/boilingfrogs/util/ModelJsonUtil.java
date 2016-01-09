package com.panoprogramowanie.boilingfrogs.util;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.panoprogramowanie.boilingfrogs.model.Schedule;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;

/**
 * Created by Wojciech on 09.01.2016.
 */
public class ModelJsonUtil {
    public static String getSampleModelJson()
    {
        Gson gson=new Gson();

        Schedule schedule = getSampleSchedule();

        return gson.toJson(schedule);
    }

    @NonNull
    public static Schedule getSampleSchedule() {
        Speaker[] speakers = getSampleSpeakers();

        Schedule schedule=new Schedule();
        schedule.setSpeakers(speakers);
        schedule.setSpeechSlots(getSampleSpeechSlots());
        return schedule;
    }

    @NonNull
    public static SpeechSlot[] getSampleSpeechSlots() {
        Speech speech1=new Speech();
        speech1.setDescription("Długi opis");
        speech1.setPath(1);
        speech1.setSpeakerId(1);
        speech1.setTimeString("10:00 - 11:00");
        speech1.setTitle("Super prelekcja");

        SpeechSlot speechSlot=new SpeechSlot();
        speechSlot.setHeader("10:00 - 11:00");
        speechSlot.setSpeeches(new Speech[]{speech1});

        Speech speech2=new Speech();
        speech2.setDescription("Długi opis");
        speech2.setPath(1);
        speech2.setSpeakerId(1);
        speech2.setTimeString("11:00 - 12:00");
        speech2.setTitle("Super prelekcja");

        SpeechSlot speechSlot2=new SpeechSlot();
        speechSlot2.setHeader("11:00 - 12:00");
        speechSlot2.setSpeeches(new Speech[]{speech1});

        return new SpeechSlot[]{speechSlot,speechSlot2};
    }

    @NonNull
    public static Speaker getSampleSpeaker() {
        Speaker speaker=new Speaker();
        speaker.setId(1);
        speaker.setDescription("opis prelegenta");
        speaker.setName("Jan Kowalski");
        speaker.setOccupation("Software craftman");
        speaker.setFacebook("https://www.facebook.com/tomakon.inc");
        return speaker;
    }

    public static Speaker[] getSampleSpeakers()
    {
        return new Speaker[]{getSampleSpeaker(),getSampleSpeaker()};
    }
}
