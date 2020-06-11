package com.example.pocketoverflow.signIn.ui.commonRoom;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("v1/houses/{houseId}")
    Call<List<House>> getHouseById(@Path("houseId") String houseId, @Query("key") String apiKey);
}
