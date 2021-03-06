package com.example.surveybank;

import java.util.List;

import okhttp3.Response;
import okhttp3.ResponseBody;
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

    @POST("api/Denek")
    Call<Integer> PostDenekKaydi(
            @Query("Ad") String ad ,
            @Query("Soyad") String soyad ,
            @Query("Dogum_Tarihi") String  dogum_Tarihi ,
            @Query("Cinsiyet") String cinsiyet ,
            @Query("Cep_Tel") String cep_Tel,
            @Query("Meslek") String meslek,
            @Query("Mail") String mail,
            @Query("Egitim_Durumu") String egitim_Durumu,
            @Query("Medeni_Hali") String medeni_Hali,
            @Query("Bolge") String bolge,
            @Query("Sehir") String sehir
    );
    @POST("api/Anketler")
    Call<ResponseBody> PostCevap(
            @Query("Anket_ID") int anket_id,
            @Query("Anketor_ID") int anketor_id,
            @Query("Soru_ID") int soru_id,
            @Query("Cevap_Secenek_ID") int cevap_secenek_id,
            @Query("Denek_ID") int denek_id

    );


}

