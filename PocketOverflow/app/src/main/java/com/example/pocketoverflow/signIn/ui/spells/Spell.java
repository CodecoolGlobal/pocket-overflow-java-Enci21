package com.example.pocketoverflow.signIn.ui.spells;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Spell implements Parcelable {

    @SerializedName("spell")
    private String spell;

    @SerializedName("effect")
    private String effect;

    @SerializedName("_id")
    private String id;

    @SerializedName("type")
    private String type;

    public static final Creator<Spell> CREATOR = new Creator<Spell>() {
        @Override
        public Spell createFromParcel(Parcel in) {
            return new Spell(in);
        }

        @Override
        public Spell[] newArray(int size) {
            return new Spell[size];
        }
    };

    public Spell(Parcel parcel) {
        this.spell = parcel.readString();
        this.effect = parcel.readString();
        this.id = parcel.readString();
        this.type = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(spell);
        dest.writeString(effect);
        dest.writeString(id);
        dest.writeString(type);
    }
}