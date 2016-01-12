package com.panoprogramowanie.boilingfrogs.suppliers.implementation;

import android.content.Context;

import com.google.gson.Gson;
import com.panoprogramowanie.boilingfrogs.model.Schedule;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Wojciech on 09.01.2016.
 */
public class ScheduleSupplierImpl implements ScheduleSupplier {

    private static final String SCHEDULE_ASSET_FILENAME="agenda.json";

    private Schedule schedule;

    @Override
    public SpeechSlot[] getAllSpeechSlots() {
        return schedule.getSpeechSlots();
    }

    @Override
    public Speaker[] getAllSpeakers() {
        return schedule.getSpeakers();
    }

    @Override
    public Speaker getSpeakerById(int id) {
        return schedule.getSpeakers()[id-1];
    }

    @Override
    public void loadSchedule(Context context) {
       loadScheduleFromAssets(context);
    }

    public void loadScheduleFromAssets(Context context) {
        String scheduleJson=readAssetsFile(context,SCHEDULE_ASSET_FILENAME);

        Gson gson=new Gson();
        schedule=gson.fromJson(scheduleJson, Schedule.class);

        fillSpeechSpeakers();
    }

    public void fillSpeechSpeakers() {
        for(SpeechSlot slot:schedule.getSpeechSlots())
        {
            for(Speech speech:slot.getSpeeches())
            {
                int speakerId=speech.getSpeakerId();
                if(speakerId>0)
                {
                    speech.setSpeaker(getSpeakerById(speakerId));
                }
            }
        }
    }

    private String readAssetsFile(Context context, String fileName)
    {
        StringBuilder builder=new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(fileName), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                builder.append(mLine);
            }
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        return builder.toString();
    }
}
