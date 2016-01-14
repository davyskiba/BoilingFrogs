package com.panoprogramowanie.boilingfrogs.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wojciech on 09.01.2016.
 */
public class SpeechSlot implements Parcelable{
    String header;

    Speech[] speeches;

    int favoriteSpeechPath =-1;

    public SpeechSlot() {
    }

    public SpeechSlot(Parcel in) {
        header=in.readString();
        speeches=(Speech[])in.readParcelableArray(Speech.class.getClassLoader());
    }

    public SpeechSlot(String header) {
        this.header=header;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Speech[] getSpeeches() {
        return speeches;
    }

    public void setSpeeches(Speech[] speeches) {
        this.speeches = speeches;
    }

    public int getFavoriteSpeechPath() {
        return favoriteSpeechPath;
    }

    public void setFavoriteSpeechPath(int favoriteSpeech) {
        this.favoriteSpeechPath = favoriteSpeech;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(header);
        dest.writeParcelableArray(speeches,flags);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SpeechSlot> CREATOR = new Parcelable.Creator<SpeechSlot>() {
        @Override
        public SpeechSlot createFromParcel(Parcel in) {
            return new SpeechSlot(in);
        }

        @Override
        public SpeechSlot[] newArray(int size) {
            return new SpeechSlot[size];
        }
    };

    public Speech getSpeechForPath(int speechPath) {
        return speeches[speechPath-1];
    }
}
