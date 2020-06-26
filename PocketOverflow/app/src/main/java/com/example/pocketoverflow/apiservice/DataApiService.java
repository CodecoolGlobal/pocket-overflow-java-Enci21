package com.example.pocketoverflow.apiservice;


import com.example.pocketoverflow.signIn.ui.commonRoom.House;
import com.example.pocketoverflow.signIn.ui.spells.Spell;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataApiService {

    @GET("v1/houses/{houseId}")
    Single<List<House>> getHouseById(@Path("houseId") String houseId, @Query("key") String apiKey);

    @GET("v1/spells")
    Single<List<Spell>> getSpells(@Query("key") String apiKey);
}
