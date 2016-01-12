package com.panoprogramowanie.boilingfrogs.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.panoprogramowanie.boilingfrogs.ui.list.ListItemModel;

/**
 * Created by Wojciech on 09.01.2016.
 */
public class Speech implements Parcelable, ListItemModel {
    String timeString;

    int speakerId;
    int path;

    String title;
    String description;
    private Speaker speaker;

    public Speech()
    {

    }

    protected Speech(Parcel in) {
        timeString = in.readString();
        speakerId = in.readInt();
        path = in.readInt();
        title = in.readString();
        description = in.readString();
        speaker=in.readParcelable(Speaker.class.getClassLoader());
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public int getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
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

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    //region ListItemModel

    @Override
    public String getSubtitle() {
        return speaker!=null ? speaker.getName() : null;
    }

    @Override
    public String getPhotoUrl() {
        return speaker!=null ? speaker.photoUrl : null;
    }

    @Override
    public boolean isClickable() {
        return description!=null;
    }

    //endregion

    //region Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(timeString);
        dest.writeInt(speakerId);
        dest.writeInt(path);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeParcelable(speaker, flags);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Speech> CREATOR = new Parcelable.Creator<Speech>() {
        @Override
        public Speech createFromParcel(Parcel in) {
            return new Speech(in);
        }

        @Override
        public Speech[] newArray(int size) {
            return new Speech[size];
        }
    };

    //endregion
}
