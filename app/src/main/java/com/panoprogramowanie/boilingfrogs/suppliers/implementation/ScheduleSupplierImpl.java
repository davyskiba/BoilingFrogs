package com.panoprogramowanie.boilingfrogs.suppliers.implementation;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import com.panoprogramowanie.boilingfrogs.model.Schedule;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Wojciech on 09.01.2016.
 */
public class ScheduleSupplierImpl implements ScheduleSupplier {

    private static final String SCHEDULE_PREFS_NAME = "shedule";
    private static final String SCHEDULE_PREFS_KEY = "shedule";

    private static final String SCHEDULE_ASSET_FILENAME = "agenda.json";

    private Schedule schedule;

    public ScheduleSupplierImpl(Context context) {
        loadSchedule(context);
    }

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
        return schedule.getSpeakers()[id - 1];
    }

    @Override
    public SpeechSlot getSpeechSlotForPosition(int position) {
        return schedule.getSpeechSlots()[position];
    }

    public void loadSchedule(Context context) {
        loadScheduleFromAssets(context);

        try {
            SpeechSlot[] slots = schedule.getSpeechSlots();
            int[] favoritePaths = loadFavoriteSlotsFromSharedPreferences(context);
            for (int i = 0; i < favoritePaths.length; i++) {
                slots[i].setFavoriteSpeechPath(favoritePaths[i]);
            }
        } catch (ArrayIndexOutOfBoundsException exc) {
        }
    }

    public void loadScheduleFromAssets(Context context) {
        String scheduleJson = readAssetsFile(context, SCHEDULE_ASSET_FILENAME);

        Gson gson = new Gson();
        schedule = gson.fromJson(scheduleJson, Schedule.class);

        fillSpeechSpeakers();
    }

    public void fillSpeechSpeakers() {
        for (SpeechSlot slot : schedule.getSpeechSlots()) {
            for (Speech speech : slot.getSpeeches()) {
                int speakerId = speech.getSpeakerId();
                if (speakerId > 0) {
                    speech.setSpeaker(getSpeakerById(speakerId));
                }
            }
        }
    }

    private String readAssetsFile(Context context, String fileName) {
        StringBuilder builder = new StringBuilder();
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

    //region SharedPreferences

    @Override
    public void speechSlotsFavoritesUpdated(Context context, SpeechSlot speechSlot) {
        saveFavoriteSlotsToSharedPreferences(context);
    }

    private void saveFavoriteSlotsToSharedPreferences(Context context) {
        SpeechSlot[] speechSlots = schedule.getSpeechSlots();
        int[] favoritePaths = new int[speechSlots.length];
        for (int i = 0; i < speechSlots.length; i++) {
            favoritePaths[i] = speechSlots[i].getFavoriteSpeechPath();
        }

        String arrayString = Arrays.toString(favoritePaths);

        SharedPreferences.Editor editor = context.getSharedPreferences(SCHEDULE_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(SCHEDULE_PREFS_KEY, arrayString);
        editor.commit();
    }

    private int[] loadFavoriteSlotsFromSharedPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(SCHEDULE_PREFS_NAME, Context.MODE_PRIVATE);
        String savedFavorite = prefs.getString(SCHEDULE_PREFS_KEY, null);
        if (savedFavorite == null)
            return new int[0];

        String[] items = savedFavorite.replaceAll("\\[", "").replaceAll("\\]", "").split(",");

        int[] results = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i].trim());
            } catch (NumberFormatException nfe) {
            }
            ;
        }


        return results;
    }

    //endregion
}
