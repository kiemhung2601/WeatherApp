package com.example.weatherapp.viewmodels;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
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
    private LifecycleOwner lifecycleOwner;

    public HomeFragmentViewModel(LifecycleOwner lifecycleOwner) {
        weatherRepo = new WeatherRepo();
        this.lifecycleOwner = lifecycleOwner;
    }


    public LiveData<Weather> getWeather(String key, String location) {
        if (weatherMutableLiveData == null) {
            weatherMutableLiveData = weatherRepo.requestWeather(key, location);
        }
        if(weatherMutableLiveData != null){
            weatherMutableLiveData.observe(lifecycleOwner, new Observer<Weather>() {
                @Override
                public void onChanged(Weather weather) {
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
            });
        }
        return weatherMutableLiveData;
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }
}
