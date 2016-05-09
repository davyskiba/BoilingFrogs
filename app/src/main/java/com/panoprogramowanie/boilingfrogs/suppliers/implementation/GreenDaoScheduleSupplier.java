package com.panoprogramowanie.boilingfrogs.suppliers.implementation;

import com.panoprogramowanie.boilingfrogs.model.greendao.Speaker;
import com.panoprogramowanie.boilingfrogs.model.greendao.Speech;
import com.panoprogramowanie.boilingfrogs.model.greendao.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.model.greendao.DaoMaster;
import com.panoprogramowanie.boilingfrogs.model.greendao.DaoSession;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class GreenDaoScheduleSupplier{

    DaoSession daoSession;

    public GreenDaoScheduleSupplier(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "boilingfrogs_db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
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
}
