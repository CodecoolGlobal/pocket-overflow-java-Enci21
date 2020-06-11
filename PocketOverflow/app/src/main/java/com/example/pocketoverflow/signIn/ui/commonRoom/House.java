package com.example.pocketoverflow.signIn.ui.commonRoom;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class House {

    @SerializedName("headOfHouse")
    private String headOfHouse;

    @SerializedName("houseGhost")
    private String houseGhost;

    @SerializedName("mascot")
    private String mascot;

    @SerializedName("school")
    private String school;

    @SerializedName("founder")
    private String founder;

    @SerializedName("__v")
    private int V;

    @SerializedName("members")
    private List<MembersItem> members;

    @SerializedName("values")
    private List<String> values;

    @SerializedName("name")
    private String name;

    @SerializedName("_id")
    private String id;

    @SerializedName("colors")
    private List<String> colors;

    public String getHeadOfHouse() {
        return headOfHouse;
    }

    public String getHouseGhost() {
        return houseGhost;
    }

    public String getMascot() {
        return mascot;
    }

    public String getSchool() {
        return school;
    }

    public String getFounder() {
        return founder;
    }

    public int getV() {
        return V;
    }

    public List<MembersItem> getMembers() {
        return members;
    }

    public List<String> getValues() {
        return values;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<String> getColors() {
        return colors;
    }
}