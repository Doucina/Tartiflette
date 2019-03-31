package com.example.tartiflette;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HoroscopeRestApi {

    // Je définis mon interface avec ma méthode getListTrololo
    // qui retourne ma liste de String (Signe Astrologique)
    @GET("Horoscope_Nesrine_API.json")
    Call<List<Sunsign>> getListTrololo();
}
// Question HYPER IMPORTANTE OLALAAAA MON DIEU !!!!

