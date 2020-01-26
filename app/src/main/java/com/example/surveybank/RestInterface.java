package com.example.surveybank;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestInterface {
@GET("api/Anketor")
    Call<List<Anketör>> GetAnketörListesi();

    @POST("api/Deneme")
    Call<List<Anket>> GetAnketListesi(
            @Query("anketorid") int id


    );

}

