package com.panoprogramowanie.boilingfrogs.model;

import java.util.List;
import com.panoprogramowanie.boilingfrogs.model.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table "SPEAKER".
 */
public class Speaker {

    private Long id;
    private String name;
    private String occupation;
    private String description;
    private String photoUrl;
    private String twitter;
    private String linkedin;
    private String facebook;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient SpeakerDao myDao;

    private List<Speech> speechList;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Speaker() {
    }

    public Speaker(Long id) {
        this.id = id;
    }

    public Speaker(Long id, String name, String occupation, String description, String photoUrl, String twitter, String linkedin, String facebook) {
        this.id = id;
        this.name = name;
        this.occupation = occupation;
        this.description = description;
        this.photoUrl = photoUrl;
        this.twitter = twitter;
        this.linkedin = linkedin;
        this.facebook = facebook;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSpeakerDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Speech> getSpeechList() {
        if (speechList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SpeechDao targetDao = daoSession.getSpeechDao();
            List<Speech> speechListNew = targetDao._querySpeaker_SpeechList(id);
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
    // KEEP METHODS END

}
