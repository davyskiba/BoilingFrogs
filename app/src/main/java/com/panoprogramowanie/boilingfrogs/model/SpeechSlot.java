package com.panoprogramowanie.boilingfrogs.model;

import java.util.List;
import com.panoprogramowanie.boilingfrogs.model.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "SPEECH_SLOT".
 */
public class SpeechSlot {

    private Long id;
    private String timeLabel;
    private Long favoriteSpeechId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient SpeechSlotDao myDao;

    private Speech favoriteSpeech;
    private Long favoriteSpeech__resolvedKey;

    private List<Speech> speechList;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public SpeechSlot() {
    }

    public SpeechSlot(Long id) {
        this.id = id;
    }

    public SpeechSlot(Long id, String timeLabel, Long favoriteSpeechId) {
        this.id = id;
        this.timeLabel = timeLabel;
        this.favoriteSpeechId = favoriteSpeechId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSpeechSlotDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(String timeLabel) {
        this.timeLabel = timeLabel;
    }

    public Long getFavoriteSpeechId() {
        return favoriteSpeechId;
    }

    public void setFavoriteSpeechId(Long favoriteSpeechId) {
        this.favoriteSpeechId = favoriteSpeechId;
    }

    /** To-one relationship, resolved on first access. */
    public Speech getFavoriteSpeech() {
        Long __key = this.favoriteSpeechId;
        if (favoriteSpeech__resolvedKey == null || !favoriteSpeech__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SpeechDao targetDao = daoSession.getSpeechDao();
            Speech favoriteSpeechNew = targetDao.load(__key);
            synchronized (this) {
                favoriteSpeech = favoriteSpeechNew;
            	favoriteSpeech__resolvedKey = __key;
            }
        }
        return favoriteSpeech;
    }

    public void setFavoriteSpeech(Speech favoriteSpeech) {
        synchronized (this) {
            this.favoriteSpeech = favoriteSpeech;
            favoriteSpeechId = favoriteSpeech == null ? null : favoriteSpeech.getId();
            favoriteSpeech__resolvedKey = favoriteSpeechId;
        }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Speech> getSpeechList() {
        if (speechList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SpeechDao targetDao = daoSession.getSpeechDao();
            List<Speech> speechListNew = targetDao._querySpeechSlot_SpeechList(id);
            synchronized (this) {
                if(speechList == null) {
                    speechList = speechListNew;
                }
            }
        }
        return speechList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetSpeechList() {
        speechList = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here

    public Speech getFavoriteOrOnlySpeech() {
        if(speechList.size()==1){
            return speechList.get(0);
        }
        else{
            return getFavoriteSpeech();
        }
    }
    // KEEP METHODS END

}
