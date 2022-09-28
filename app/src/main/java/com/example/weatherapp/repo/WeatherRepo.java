package com.example.weatherapp.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.model.Weather;
import com.example.weatherapp.network.RetroInstance;
import com.example.weatherapp.network.RetroServiceInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepo {

    private final String TAG = getClass().getSimpleName();

    public MutableLiveData<Weather> requestWeather(String key, String location){
        final MutableLiveData<Weather> mutableLiveData = new MutableLiveData<>();

        RetroServiceInterface retroServiceInterface = RetroInstance.getRetroInstance().create(RetroServiceInterface.class);
        Call<Weather> call = retroServiceInterface.getWeather(key, location);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if(response.isSuccessful()){
                    mutableLiveData.postValue(response.body());
                } else {
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                mutableLiveData.postValue(null);
            }
        });
        return mutableLiveData;
    }

}
