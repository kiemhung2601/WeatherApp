package com.example.weatherapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.weatherapp.model.CityModel;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private ListView listView;
    private List<CityModel> lstCity;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.lst_view);

        lstCity = getListCity();

        ListCityAdapter listCityAdapter = new ListCityAdapter(getActivity(), lstCity);
        listView.setAdapter(listCityAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    private List<CityModel> getListCity(){
        List<CityModel> lstCity = new ArrayList<>();
        lstCity.add(new CityModel("Escondido", 49, "3:21", "Cloudy", 29, 60));
        lstCity.add(new CityModel("Lille", 53, "11:12", "Sunny", 40, 70));
        lstCity.add(new CityModel("Calais", 45, "12:11", "Rainy", 40, 67));

        return lstCity;
    }
}