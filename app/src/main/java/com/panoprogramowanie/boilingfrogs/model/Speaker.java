package com.panoprogramowanie.boilingfrogs.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.panoprogramowanie.boilingfrogs.ui.list.ListItemModel;

/**
 * Created by Wojciech on 09.01.2016.
 */
public class Speaker implements Parcelable,ListItemModel {
    int id;
    String name;
    String 	occupation;
    String description;
    String photoUrl;

    String twitter;
    String linkedIn;
    String facebook;

    public Speaker() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    //region ListItemViewModel

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getSubtitle() {
        return occupation;
    }

    //endregion

    //region Parcelable

    protected Speaker(Parcel in) {
        id = in.readInt();
        name = in.readString();
        occupation = in.readString();
        description = in.readString();
        twitter = in.readString();
        linkedIn = in.readString();
        facebook = in.readString();
        photoUrl=in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(occupation);
        dest.writeString(description);
        dest.writeString(twitter);
        dest.writeString(linkedIn);
        dest.writeString(facebook);
        dest.writeString(photoUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Speaker> CREATOR = new Parcelable.Creator<Speaker>() {
        @Override
        public Speaker createFromParcel(Parcel in) {
            return new Speaker(in);
        }

        @Override
        public Speaker[] newArray(int size) {
            return new Speaker[size];
        }
    };
    //endregion
}