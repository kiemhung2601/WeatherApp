package com.example.weatherapp.model;

public class Weather {
    private Location location;
    private Current current;

    public Weather(Location location, Current current) {
        this.location = location;
        this.current = current;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "location=" + location.toString() +
                ", current=" + current.toString() +
                '}';
    }
}