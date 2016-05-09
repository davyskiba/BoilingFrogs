package com.panoprogramowanie.boilingfrogs.suppliers.implementation;

import com.google.gson.Gson;

import com.panoprogramowanie.boilingfrogs.model.greendao.Schedule;
import com.panoprogramowanie.boilingfrogs.model.greendao.Speaker;
import com.panoprogramowanie.boilingfrogs.model.greendao.SpeakerDao;
import com.panoprogramowanie.boilingfrogs.model.greendao.Speech;
import com.panoprogramowanie.boilingfrogs.model.greendao.SpeechDao;
import com.panoprogramowanie.boilingfrogs.model.greendao.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.model.greendao.DaoMaster;
import com.panoprogramowanie.boilingfrogs.model.greendao.DaoSession;
import com.panoprogramowanie.boilingfrogs.model.greendao.SpeechSlotDao;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;
import com.panoprogramowanie.boilingfrogs.util.AssetJsonLoader;
import com.panoprogramowanie.boilingfrogs.util.ModelConverter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

public class GreenDaoScheduleSupplier{

    DaoSession daoSession;

    public GreenDaoScheduleSupplier(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "boilingfrogs_db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        saveScheduleFronJson(context);
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

    private void saveScheduleFronJson(Context context) {
        Schedule schedule=loadScheduleFromAssets(context);
        saveSpeakers(schedule.getSpeakers());
        saveSpeeches(schedule.getSpeeches());
        saveSpeechSlots(schedule.getSpeechSlots());
    }

    private void saveSpeakers(Speaker[] speakers){
        SpeakerDao speakerDao=daoSession.getSpeakerDao();
        speakerDao.insertOrReplaceInTx(speakers);
    }

    private void saveSpeeches(Speech[] speeches){
        SpeechDao speechDao=daoSession.getSpeechDao();
        speechDao.insertOrReplaceInTx(speeches);
    }

    private void saveSpeechSlots(SpeechSlot[] speechSlots){
        SpeechSlotDao speechSlotDao=daoSession.getSpeechSlotDao();
        speechSlotDao.insertOrReplaceInTx(speechSlots);
    }

    public static Schedule loadScheduleFromAssets(Context context) {
        Schedule schedule=ModelConverter.inMemoryToGreenDaoSchedule(
                InMemoryScheduleSupplier.loadScheduleFromAssets(context));

        return schedule;
    }
}
