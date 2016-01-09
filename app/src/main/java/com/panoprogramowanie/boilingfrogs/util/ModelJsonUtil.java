package com.panoprogramowanie.boilingfrogs.util;

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

        Speaker speaker=new Speaker();
        speaker.setId(1);
        speaker.setDescription("opis prelegenta");
        speaker.setName("Jan Kowalski");
        speaker.setOccupation("Software craftman");
        speaker.setFacebook("https://www.facebook.com/tomakon.inc");

        Schedule schedule=new Schedule();
        schedule.setSpeakers(new Speaker[]{speaker});

        Speech speech1=new Speech();
        speech1.setDescription("Długi opis");
        speech1.setPath(1);
        speech1.setSpeakerId(1);
        speech1.setTimeString("10:00 - 11:00");
        speech1.setTitle("Super prelekcja");

        SpeechSlot speechSlot=new SpeechSlot();
        speechSlot.setHeader("10:00 - 11:00");
        speechSlot.setPosition(1);
        speechSlot.setSpeeches(new Speech[]{speech1});

        schedule.setSpeechSlots(new SpeechSlot[]{speechSlot});

        return gson.toJson(schedule);
    }
}
