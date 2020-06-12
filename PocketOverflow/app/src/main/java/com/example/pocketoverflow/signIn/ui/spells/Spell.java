package com.example.pocketoverflow.signIn.ui.spells;

import com.google.gson.annotations.SerializedName;

public class Spell {

    @SerializedName("spell")
    private String spell;

    @SerializedName("effect")
    private String effect;

    @SerializedName("_id")
    private String id;

    @SerializedName("type")
    private String type;

    public String getSpell() {
        return spell;
    }

    public String getEffect() {
        return effect;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}