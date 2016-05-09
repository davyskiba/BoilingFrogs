package com.panoprogramowanie.boilingfrogs.model.greendao;

import com.panoprogramowanie.boilingfrogs.model.greendao.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "SPEECH".
 */
public class Speech {

    private Long id;
    private String title;
    private String description;
    private Integer path;
    private String youtubeUrl;
    private Long speakerId;
    private Long speechSlotId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient SpeechDao myDao;

    private Speaker speaker;
    private Long speaker__resolvedKey;


    public Speech() {
    }

    public Speech(Long id) {
        this.id = id;
    }

    public Speech(Long id, String title, String description, Integer path, String youtubeUrl, Long speakerId, Long speechSlotId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.path = path;
        this.youtubeUrl = youtubeUrl;
        this.speakerId = speakerId;
        this.speechSlotId = speechSlotId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSpeechDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPath() {
        return path;
    }

    public void setPath(Integer path) {
        this.path = path;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public Long getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(Long speakerId) {
        this.speakerId = speakerId;
    }

    public Long getSpeechSlotId() {
        return speechSlotId;
    }

    public void setSpeechSlotId(Long speechSlotId) {
        this.speechSlotId = speechSlotId;
    }

    /** To-one relationship, resolved on first access. */
    public Speaker getSpeaker() {
        Long __key = this.speakerId;
        if (speaker__resolvedKey == null || !speaker__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SpeakerDao targetDao = daoSession.getSpeakerDao();
            Speaker speakerNew = targetDao.load(__key);
            synchronized (this) {
                speaker = speakerNew;
            	speaker__resolvedKey = __key;
            }
        }
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        synchronized (this) {
            this.speaker = speaker;
            speakerId = speaker == null ? null : speaker.getId();
            speaker__resolvedKey = speakerId;
        }
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

}
