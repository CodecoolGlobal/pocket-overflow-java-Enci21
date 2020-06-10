package com.example.pocketoverflow.roomDB;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity(tableName = "users")
@Data
public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[0];
        }
    };

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String house;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    @NonNull
    Long id;

    private String wand;
    @NonNull
    private String patronus;
    @NonNull
    private String animagus;
    @NonNull
    private boolean dumbledoresArmy;
    @NonNull
    private boolean orderOfThePhoenix;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public User(Parcel parcel) {
        id = parcel.readLong();
        username = parcel.readString();
        password = parcel.readString();
        house = parcel.readString();
        patronus = parcel.readString();
        wand = parcel.readString();
        animagus = parcel.readString();
        dumbledoresArmy = parcel.readBoolean();
        orderOfThePhoenix = parcel.readBoolean();

    }

    public User(@NonNull String username, @NonNull String password, @NonNull String house, @NonNull String patronus, @NonNull String animagus, boolean dumbledoresArmy, boolean orderOfThePhoenix) {
        this.username = username;
        this.password = password;
        this.house = house;
        this.patronus = patronus;
        this.animagus = animagus;
        this.dumbledoresArmy = dumbledoresArmy;
        this.orderOfThePhoenix = orderOfThePhoenix;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(house);
        dest.writeString(patronus);
        dest.writeString(wand);
        dest.writeString(animagus);
        dest.writeBoolean(dumbledoresArmy);
        dest.writeBoolean(orderOfThePhoenix);
    }
}
