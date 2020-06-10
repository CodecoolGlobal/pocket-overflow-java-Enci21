package com.example.pocketoverflow.roomDB;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String house;

    private String patronus;
    private String wand;
    private String animagus;
    private boolean dumbledoresArmy;
    private boolean orderOfThePhoenix;

    public User(@NonNull String username, @NonNull String password, @NonNull String house) {
        this.username = username;
        this.password = password;
        this.house = house;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @NonNull
    public String getHouse() {
        return house;
    }

    public String getPatronus() {
        return patronus;
    }

    public String getWand() {
        return wand;
    }

    public String getAnimagus() {
        return animagus;
    }

    public boolean isDumbledoresArmy() {
        return dumbledoresArmy;
    }

    public boolean isOrderOfThePhoenix() {
        return orderOfThePhoenix;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public void setHouse(@NonNull String house) {
        this.house = house;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public void setWand(String wand) {
        this.wand = wand;
    }

    public void setAnimagus(String animagus) {
        this.animagus = animagus;
    }

    public void setDumbledoresArmy(boolean dumbledoresArmy) {
        this.dumbledoresArmy = dumbledoresArmy;
    }

    public void setOrderOfThePhoenix(boolean orderOfThePhoenix) {
        this.orderOfThePhoenix = orderOfThePhoenix;
    }
}
