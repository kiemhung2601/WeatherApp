package com.example.weatherapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.weatherapp.databinding.FragmentHomeBinding;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.network.ApiService;
import com.example.weatherapp.untils.DownloadImageTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        callApi();

        binding.includeSettings.imgList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_homeFragment_to_listFragment);
            }
        });

        binding.includeSettings.imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_homeFragment_to_settingsFragment);
            }
        });

    }

    private void callApi() {
        ApiService.apiService.getWeather("6dd477c8298e4243a2030401222609", "Paris").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Toast.makeText(getContext(), "Call Api Success", Toast.LENGTH_SHORT).show();

                Weather weather = response.body();
                if(weather != null){
                    Log.e("WEATHER", weather.toString());
                    binding.txtLocation.setText(weather.getLocation().getName());
                    binding.txtLocalTime.setText(weather.getLocation().getLocaltime());
                    binding.txtWeatherStatus.setText(weather.getCurrent().getCondition().getText());
                    binding.txtTemperature.setText(weather.getCurrent().getTemp_c() + "°C");
                    binding.includeTemp.txtMinTemp.setText(weather.getCurrent().getTemp_c() + "°C");
                    binding.includeTemp.txtMaxTemp.setText(weather.getCurrent().getTemp_f() + "°C");
                    binding.includeInfor.txtUv.setText(String.valueOf(weather.getCurrent().getUv()));
                    binding.includeInfor.txtFeelsLike.setText(weather.getCurrent().getFeelslike_c() + "°C");
                    binding.includeInfor.txtPressure.setText(weather.getCurrent().getPressure_mb() + " hPa");

                    new DownloadImageTask(binding.imgWeather)
                            .execute("https:" + weather.getCurrent().getCondition().getIcon());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(getContext(), "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container,false);
        return binding.getRoot();
    }
}