package com.stream.weatherproject;

import java.util.List;
import static java.util.stream.Collectors.toList;

public class Temprature {

    public static void main(String[] args) {
        List<CityWeather> weathers = List.of(
                new CityWeather("Lagos", 32.5, 75.0, false),
                new CityWeather("Abuja", 28.0, 65.0, true),
                new CityWeather("Kano", 37.0, 40.0, false),
                new CityWeather("Port Harcourt", 29.5, 80.0, true),
                new CityWeather("Ibadan", 31.0, 60.0, false),
                new CityWeather("Enugu", 33.0, 70.0, true)
        );

        // 1 ✅ Filter and list all cities where it’s currently raining.
        List<CityWeather> listCities = weathers.stream().filter(s -> s.getIsRaining()).collect(toList());
        listCities.forEach(System.out::println);
    }
}
