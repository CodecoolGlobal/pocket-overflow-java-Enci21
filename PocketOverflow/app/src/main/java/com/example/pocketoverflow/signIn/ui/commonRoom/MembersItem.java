package com.example.pocketoverflow.signIn.ui.commonRoom;

import com.google.gson.annotations.SerializedName;

public class MembersItem {

    @SerializedName("name")
    private String name;

    @SerializedName("_id")
    private String id;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}