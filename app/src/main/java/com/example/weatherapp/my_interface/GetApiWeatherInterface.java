package com.example.weatherapp.my_interface;

import com.example.weatherapp.model.Weather;

import retrofit2.Response;

public interface GetApiWeatherInterface {

    interface View {
        void callApiSuccess(Weather weather);
        void callApiError();
    }

    interface Presenter {
        void getApiWeather(String key, String location);
    }


}
