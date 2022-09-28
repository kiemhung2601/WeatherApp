package com.example.weatherapp.network;

import com.example.weatherapp.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroServiceInterface {

    @GET("current.json")
    Call<Weather> getWeather(@Query("key") String key,
                             @Query("q") String location);
}
