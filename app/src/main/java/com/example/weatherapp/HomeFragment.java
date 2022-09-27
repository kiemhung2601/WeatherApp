package com.example.weatherapp;

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
import com.example.weatherapp.my_interface.GetApiWeatherInterface;
import com.example.weatherapp.presenter.GetApiWeatherPresenter;
import com.example.weatherapp.untils.DownloadImageTask;

public class HomeFragment extends Fragment implements GetApiWeatherInterface.View {

    private FragmentHomeBinding binding;
    private GetApiWeatherPresenter getApiWeatherPresenter;
    private NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        getApiWeatherPresenter = new GetApiWeatherPresenter(this);

        getData();

        setOnClick();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void callApiSuccess(Weather weather) {
        Toast.makeText(getContext(), "Call Api Success", Toast.LENGTH_SHORT).show();

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
    public void callApiError() {
        Toast.makeText(getContext(), "Call Api Error", Toast.LENGTH_SHORT).show();
    }

    private void getData() {
        String key = "6dd477c8298e4243a2030401222609";
        String location = "Paris";

        getApiWeatherPresenter.getApiWeather(key, location);
    }

    private void setOnClick(){
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
}