package com.stream.weatherproject;

public class CityWeather {

    private String city;
    private double temperature;
    private double humidity;
    private boolean isRaining;

    public CityWeather(String city, double temperature, double humidity, boolean isRaining) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.isRaining = isRaining;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public boolean getIsRaining() {
        return isRaining;
    }

    @Override
    public String toString() {
        return "CityWeather [city=" + city + ", temperature=" + temperature + ", humidity=" + humidity + ", isRaining="
                + isRaining + "]";
    }

}
