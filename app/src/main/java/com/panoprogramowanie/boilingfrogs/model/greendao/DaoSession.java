package com.panoprogramowanie.boilingfrogs.model.greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.panoprogramowanie.boilingfrogs.model.greendao.Speaker;
import com.panoprogramowanie.boilingfrogs.model.greendao.Speech;
import com.panoprogramowanie.boilingfrogs.model.greendao.SpeechSlot;

import com.panoprogramowanie.boilingfrogs.model.greendao.SpeakerDao;
import com.panoprogramowanie.boilingfrogs.model.greendao.SpeechDao;
import com.panoprogramowanie.boilingfrogs.model.greendao.SpeechSlotDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig speakerDaoConfig;
    private final DaoConfig speechDaoConfig;
    private final DaoConfig speechSlotDaoConfig;

    private final SpeakerDao speakerDao;
    private final SpeechDao speechDao;
    private final SpeechSlotDao speechSlotDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        speakerDaoConfig = daoConfigMap.get(SpeakerDao.class).clone();
        speakerDaoConfig.initIdentityScope(type);

        speechDaoConfig = daoConfigMap.get(SpeechDao.class).clone();
        speechDaoConfig.initIdentityScope(type);

        speechSlotDaoConfig = daoConfigMap.get(SpeechSlotDao.class).clone();
        speechSlotDaoConfig.initIdentityScope(type);

        speakerDao = new SpeakerDao(speakerDaoConfig, this);
        speechDao = new SpeechDao(speechDaoConfig, this);
        speechSlotDao = new SpeechSlotDao(speechSlotDaoConfig, this);

        registerDao(Speaker.class, speakerDao);
        registerDao(Speech.class, speechDao);
        registerDao(SpeechSlot.class, speechSlotDao);
    }
    
    public void clear() {
        speakerDaoConfig.getIdentityScope().clear();
        speechDaoConfig.getIdentityScope().clear();
        speechSlotDaoConfig.getIdentityScope().clear();
    }

    public SpeakerDao getSpeakerDao() {
        return speakerDao;
    }

    public SpeechDao getSpeechDao() {
        return speechDao;
    }

    public SpeechSlotDao getSpeechSlotDao() {
        return speechSlotDao;
    }

}
