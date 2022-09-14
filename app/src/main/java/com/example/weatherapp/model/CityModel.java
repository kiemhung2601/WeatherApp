package com.example.weatherapp.model;

public class CityModel {
    private String city;
    private int temp;
    private String time;
    private String weather;
    private int minTemp;
    private int maxTemp;

    public CityModel(String city, int temp, String time, String weather, int minTemp, int maxTemp) {
        this.city = city;
        this.temp = temp;
        this.time = time;
        this.weather = weather;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }
}
