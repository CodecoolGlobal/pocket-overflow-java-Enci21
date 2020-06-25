package com.example.pocketoverflow.signIn.ui.commonRoom;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MembersItem implements Parcelable {

    @SerializedName("name")
    private String name;

    @SerializedName("_id")
    private String id;

    public static final Creator<MembersItem> CREATOR = new Creator<MembersItem>() {
        @Override
        public MembersItem createFromParcel(Parcel in) {
            return new MembersItem(in);
        }

        @Override
        public MembersItem[] newArray(int size) {
            return new MembersItem[size];
        }
    };

    protected MembersItem(Parcel in) {
        name = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}