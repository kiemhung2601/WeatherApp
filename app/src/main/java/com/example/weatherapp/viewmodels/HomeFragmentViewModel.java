package com.example.weatherapp.viewmodels;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.model.Weather;
import com.example.weatherapp.repo.WeatherRepo;

public class HomeFragmentViewModel extends ViewModel {

    private WeatherRepo weatherRepo;
    private MutableLiveData<Weather> weatherMutableLiveData;

    public String txtLocation;
    public String txtLocalTime;
    public String txtWeatherStatus;
    public String txtTemperature;
    public String txtMinTemp;
    public String txtMaxTemp;
    public String txtUv;
    public String txtFeelsLike;
    public String txtPressure;
    public String icon;

    public HomeFragmentViewModel() {
        weatherRepo = new WeatherRepo();
    }


    public LiveData<Weather> getWeather(String key, String location) {
        if (weatherMutableLiveData == null) {
            weatherMutableLiveData = weatherRepo.requestWeather(key, location);
        }

        return weatherMutableLiveData;
    }

    public void refreshData(Weather weather) {
        if(weather != null){
            txtLocation = (weather.getLocation().getName());
            txtLocalTime = (weather.getLocation().getLocaltime());
            txtWeatherStatus = (weather.getCurrent().getCondition().getText());
            txtTemperature = (weather.getCurrent().getTemp_c() + "°C");
            txtMinTemp = (weather.getCurrent().getTemp_c() + "°C");
            txtMaxTemp = (weather.getCurrent().getTemp_f() + "°C");
            txtUv = (String.valueOf(weather.getCurrent().getUv()));
            txtFeelsLike = (weather.getCurrent().getFeelslike_c() + "°C");
            txtPressure = (weather.getCurrent().getPressure_mb() + " hPa");
            icon = ("https:" + weather.getCurrent().getCondition().getIcon());
        }
    }
}
