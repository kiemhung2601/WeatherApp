package com.example.weatherapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.weatherapp.databinding.FragmentHomeBinding;
import com.example.weatherapp.model.CityModel;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.untils.DownloadImageTask;
import com.example.weatherapp.viewmodels.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ImageButton imgList, imgSetting;

    private HomeFragmentViewModel homeFragmentViewModel;

    private FragmentHomeBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        imgList = view.findViewById(R.id.img_list);
        imgSetting = view.findViewById(R.id.img_setting);

        imgList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_homeFragment_to_listFragment);
            }
        });

        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_homeFragment_to_settingsFragment);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        homeFragmentViewModel = new HomeFragmentViewModel();
        homeFragmentViewModel.getWeather("6dd477c8298e4243a2030401222609", "Paris").observe(getViewLifecycleOwner(), new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                if(weather != null){
                    binding.setHomeFragmentViewModel(homeFragmentViewModel);
                    homeFragmentViewModel.refreshData(weather);

                    new DownloadImageTask(binding.imgWeather)
                            .execute("https:" + weather.getCurrent().getCondition().getIcon());
                }
            }
        });

        View view = binding.getRoot();
        return view;
    }
}