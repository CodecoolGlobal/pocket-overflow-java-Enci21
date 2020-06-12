package com.example.pocketoverflow.signIn.ui;


import com.example.pocketoverflow.signIn.ui.commonRoom.House;
import com.example.pocketoverflow.signIn.ui.spells.Spell;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("v1/houses/{houseId}")
    Observable<List<House>> getHouseById(@Path("houseId") String houseId, @Query("key") String apiKey);

    @GET("v1/spells")
    Observable<List<Spell>> getSpells(@Query("key") String apiKey);
}
