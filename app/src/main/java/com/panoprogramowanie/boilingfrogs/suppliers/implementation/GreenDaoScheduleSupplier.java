package com.panoprogramowanie.boilingfrogs.suppliers.implementation;

import com.google.gson.Gson;

import com.panoprogramowanie.boilingfrogs.model.greendao.Speaker;
import com.panoprogramowanie.boilingfrogs.model.greendao.Speech;
import com.panoprogramowanie.boilingfrogs.model.greendao.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.model.greendao.DaoMaster;
import com.panoprogramowanie.boilingfrogs.model.greendao.DaoSession;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.util.AssetJsonLoader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class GreenDaoScheduleSupplier{

    DaoSession daoSession;

    public GreenDaoScheduleSupplier(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "boilingfrogs_db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        loadScheduleFromAssets(context);
    }

//    @Override
    public SpeechSlot[] getAllSpeechSlots() {
        return daoSession.getSpeechSlotDao().loadAll().toArray(new SpeechSlot[0]);
    }

//    @Override
    public Speaker[] getAllSpeakers() {
        return daoSession.getSpeakerDao().loadAll().toArray(new Speaker[0]);
    }

//    @Override
    public Speaker getSpeakerById(int id) {
        return daoSession.getSpeakerDao().load((long)id);
    }

//    @Override
    public SpeechSlot getSpeechSlotForPosition(int position) {
        return new SpeechSlot();
    }


//    @Override
    public void speechSlotsFavoritesUpdated(Context context, SpeechSlot speechSlot) {
        daoSession.getSpeechSlotDao().update(speechSlot);
    }

    public static Schedule loadScheduleFromAssets(Context context) {
        String scheduleJson = AssetJsonLoader.readScheduleJsonFromFile(context);

        Gson gson = new Gson();
        Schedule result = gson.fromJson(scheduleJson, Schedule.class);

        return result;
    }

    private static class Schedule{
        Speaker[] speakers;
        SpeechSlot[] speechSlots;

        public Speaker[] getSpeakers() {
            return speakers;
        }

        public void setSpeakers(Speaker[] speakers) {
            this.speakers = speakers;
        }

        public SpeechSlot[] getSpeechSlots() {
            return speechSlots;
        }

        public void setSpeechSlots(SpeechSlot[] speechSlots) {
            this.speechSlots = speechSlots;
        }
    }
}
