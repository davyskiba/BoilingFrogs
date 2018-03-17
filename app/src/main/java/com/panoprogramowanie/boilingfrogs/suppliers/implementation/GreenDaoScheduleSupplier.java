package com.panoprogramowanie.boilingfrogs.suppliers.implementation;

import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.model.SpeakerDao;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechDao;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.model.DaoMaster;
import com.panoprogramowanie.boilingfrogs.model.DaoSession;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlotDao;
import com.panoprogramowanie.boilingfrogs.suppliers.ScheduleSupplier;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class GreenDaoScheduleSupplier implements ScheduleSupplier{
    public static final String DB_NAME = "boilingfrogs_db";

    DaoSession daoSession;

    public GreenDaoScheduleSupplier(Context context) {
        ensureDatabaseFileExists(context);

        DaoMaster.OpenHelper helper = new DaoMaster.OpenHelper(context, DB_NAME, null) {
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DELETE FROM SPEAKER");
                db.execSQL("DELETE FROM SPEECH");
                db.execSQL("DELETE FROM SPEECH_SLOT");
            }
        };
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    //region DBFile Handling

    private void ensureDatabaseFileExists(Context context) {
        String dbPath = context.getApplicationInfo().dataDir + "/databases/" + DB_NAME;
        File dbFile = new File(dbPath);

        if (!dbFile.exists()) {
            copyDatabaseFromAssets(context, dbFile);
        }
    }

    private void copyDatabaseFromAssets(Context context, File dbFile) {
        try {
            dbFile.getParentFile().mkdirs();
            copyStream(context.getAssets().open(DB_NAME), new FileOutputStream(dbFile));
        }catch (IOException exc){

        }
    }

    private void copyStream(InputStream is, OutputStream os) throws IOException {
        byte buf[] = new byte[1024];
        int c = 0;
        while (true) {
            c = is.read(buf);
            if (c == -1)
                break;
            os.write(buf, 0, c);
        }

        is.close();
        os.close();
    }

    //endregion

    @Override
    public Speech getSpeechById(long id) {
        return daoSession.getSpeechDao().load(id);
    }

    @Override
    public void updateSpeeches(Speech[] speeches) {
        SpeechDao speechDao = daoSession.getSpeechDao();
        for(Speech speech : speeches){
            speechDao.insertOrReplace(speech);
        }
    }

    @Override
    public List<Speaker> getAllSpeakers() {
        return daoSession.getSpeakerDao().loadAll();
    }

    @Override
    public Speaker getSpeakerById(long speakerId) {
        return daoSession.getSpeakerDao().load(speakerId);
    }

    @Override
    public void updateSpeakers(Speaker[] speakers) {
        SpeakerDao speakerDao = daoSession.getSpeakerDao();
        for(Speaker speaker : speakers){
            speakerDao.insertOrReplace(speaker);
        }
    }

    @Override
    public SpeechSlot getSpeechSlotBytId(long slotId) {
        return daoSession.getSpeechSlotDao().load(slotId);
    }

    @Override
    public List<SpeechSlot> getAllSpeechSlots() {
        return daoSession.getSpeechSlotDao().loadAll();
    }

    @Override
    public void updateSpeechSlot(SpeechSlot speechSlot) {
        daoSession.getSpeechSlotDao().insertOrReplace(speechSlot);
    }

    @Override
    public void updateSpeechSlotsAndKeepFavorites(SpeechSlot[] speechSlots) {
        SpeechSlotDao speechSlotDao = daoSession.getSpeechSlotDao();
        for(SpeechSlot speechSlot : speechSlots){
            updateSpeechSlot(speechSlotDao, speechSlot);
        }
    }

    private void updateSpeechSlot(SpeechSlotDao speechSlotDao, SpeechSlot speechSlot) {
        SpeechSlot localSpeechSlot = speechSlotDao.load(speechSlot.getId());
        if(localSpeechSlot!=null){
            speechSlot.setFavoriteSpeechId(localSpeechSlot.getFavoriteSpeechId());
        }

        speechSlotDao.insertOrReplace(speechSlot);
    }
}
