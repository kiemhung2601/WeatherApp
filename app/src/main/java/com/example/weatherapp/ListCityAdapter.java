package com.example.weatherapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.weatherapp.model.CityModel;

import java.util.List;

public class ListCityAdapter extends BaseAdapter {

    private Activity activity;
    private List<CityModel> lstCity;

    public ListCityAdapter(Activity activity, List<CityModel> lstCity) {
        this.activity = activity;
        this.lstCity = lstCity;
    }

    @Override
    public int getCount() {
        return lstCity.size();
    }

    @Override
    public Object getItem(int i) {
        return lstCity.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.item_city_widget, null);

        TextView txtCity = view.findViewById(R.id.txt_name_city);
        TextView txtTemp = view.findViewById(R.id.txt_temperature);
        TextView txtTime = view.findViewById(R.id.txt_time);
        TextView txtWeather = view.findViewById(R.id.txt_weather);
        TextView txtLimit = view.findViewById(R.id.txt_limit_temp);

        txtCity.setText(lstCity.get(i).getCity());
        txtTemp.setText(lstCity.get(i).getTemp() + "°C");
        txtTime.setText(lstCity.get(i).getTime());
        txtWeather.setText(lstCity.get(i).getWeather());
        txtLimit.setText("Max. " + lstCity.get(i).getMaxTemp() +"°C Min " + lstCity.get(i).getMinTemp() + "°C");

        return view;
    }
}
