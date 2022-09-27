package com.example.weatherapp.presenter;

import com.example.weatherapp.model.Weather;
import com.example.weatherapp.my_interface.GetApiWeatherInterface;
import com.example.weatherapp.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetApiWeatherPresenter implements GetApiWeatherInterface.Presenter{

    private GetApiWeatherInterface.View mView;

    public GetApiWeatherPresenter(GetApiWeatherInterface.View mView) {
        this.mView = mView;
    }

    @Override
    public void getApiWeather(String key, String location) {
        ApiService.apiService.getWeather(key, location).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weather = response.body();
                mView.callApiSuccess(weather);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                mView.callApiError();
            }
        });
    }
}
