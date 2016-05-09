package com.panoprogramowanie.boilingfrogs.suppliers.implementation;

import com.panoprogramowanie.boilingfrogs.model.greendao.Schedule;
import com.panoprogramowanie.boilingfrogs.model.greendao.Speaker;
import com.panoprogramowanie.boilingfrogs.model.greendao.SpeakerDao;
import com.panoprogramowanie.boilingfrogs.model.greendao.Speech;
import com.panoprogramowanie.boilingfrogs.model.greendao.SpeechDao;
import com.panoprogramowanie.boilingfrogs.model.greendao.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.model.greendao.DaoMaster;
import com.panoprogramowanie.boilingfrogs.model.greendao.DaoSession;
import com.panoprogramowanie.boilingfrogs.model.greendao.SpeechSlotDao;
import com.panoprogramowanie.boilingfrogs.util.ModelConverter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class GreenDaoScheduleSupplier {
    public static final String DB_NAME = "boilingfrogs_db";

    DaoSession daoSession;

    public GreenDaoScheduleSupplier(Context context) {
        ensureDatabaseFileExists(context);

        DaoMaster.OpenHelper helper = new DaoMaster.OpenHelper(context, DB_NAME, null) {
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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
        return daoSession.getSpeakerDao().load((long) id);
    }

    //    @Override
    public SpeechSlot getSpeechSlotForPosition(int position) {
        return new SpeechSlot();
    }


    //    @Override
    public void speechSlotsFavoritesUpdated(Context context, SpeechSlot speechSlot) {
        daoSession.getSpeechSlotDao().update(speechSlot);
    }

    //region FromJsonParsing

    private void saveScheduleFronJson(Context context) {
        Schedule schedule = loadScheduleFromAssets(context);
        saveSpeakers(schedule.getSpeakers());
        saveSpeeches(schedule.getSpeeches());
        saveSpeechSlots(schedule.getSpeechSlots());
    }

    private void saveSpeakers(Speaker[] speakers) {
        SpeakerDao speakerDao = daoSession.getSpeakerDao();
        speakerDao.insertOrReplaceInTx(speakers);
    }

    private void saveSpeeches(Speech[] speeches) {
        SpeechDao speechDao = daoSession.getSpeechDao();
        speechDao.insertOrReplaceInTx(speeches);
    }

    private void saveSpeechSlots(SpeechSlot[] speechSlots) {
        SpeechSlotDao speechSlotDao = daoSession.getSpeechSlotDao();
        speechSlotDao.insertOrReplaceInTx(speechSlots);
    }

    public static Schedule loadScheduleFromAssets(Context context) {
        Schedule schedule = ModelConverter.inMemoryToGreenDaoSchedule(
                InMemoryScheduleSupplier.loadScheduleFromAssets(context));

        return schedule;
    }

    //endregion
}
