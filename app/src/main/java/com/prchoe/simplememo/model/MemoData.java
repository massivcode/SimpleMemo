package com.prchoe.simplememo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by massivCode on 2015-09-22.
 */
public class MemoData implements Parcelable {

    private int    _id;
    private String title;
    private String contents;
    private String date;

    public MemoData(int _id, String title, String contents, String date) {
        this._id = _id;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(title);
        dest.writeString(contents);
        dest.writeString(date);
    }

    public static final Parcelable.Creator<MemoData> CREATOR
            = new Parcelable.Creator<MemoData>() {
        public MemoData createFromParcel(Parcel in) {
            return new MemoData(in);
        }

        public MemoData[] newArray(int size) {
            return new MemoData[size];
        }
    };

    private MemoData(Parcel in) {
        _id = in.readInt();
        title = in.readString();
        contents = in.readString();
        date = in.readString();
    }
}
