package com.stream.weatherproject;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
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
        System.out.println();

        // 2 🔥 Sort cities by temperature in descending order.
        List<CityWeather> sortedCities = weathers.stream()
                .sorted((a, b) -> Double.compare(b.getTemperature(), a.getTemperature()))
                .collect(toList());
        sortedCities.forEach(System.out::println);
        System.out.println();

        // 3 📊 Get average humidity of all cities.
        double averageHumidity = weathers.stream()
                .mapToDouble(CityWeather::getHumidity)
                .average()
                .orElse(0.0);
        System.out.println("Average Humidity for all cities: " + averageHumidity);

        // 4 🌡️ Find the hottest city.
        Optional<CityWeather> hottestCity = weathers.stream()
                .max(Comparator.comparingDouble(CityWeather::getTemperature));
        hottestCity.ifPresent(city -> System.err.println("Hottest city: " + city));

        System.out.println();

        // 5 🌧️ Count how many cities are raining.
        long countCities = weathers.stream()
                .filter(city -> city.getIsRaining())
                .count();
        System.out.println("Number of city raining: " + countCities);
        System.out.println();

        // 6 🚫 List all dry cities (not raining and humidity < 50).
        List<CityWeather> dryCities = weathers.stream()
                .filter(s -> !s.getIsRaining() && s.getHumidity() < 50)
                .collect(toList());
        dryCities.forEach(System.out::println);
        System.out.println();

        // 7 🔠 Create a comma-separated string of all city names in uppercase.
        String weatherToUpperCase = weathers.stream()
                .map(city -> city.getCity().toUpperCase())
                .collect(joining(","));
        System.out.println(weatherToUpperCase);
        System.out.println();

        // 8 📈 Group cities into: "Hot" (temperature > 30°C) "Moderate" (25–30°C) "Cool" (< 25°C)
        Map<String, List<CityWeather>> gradeGroup = weathers.stream()
                .collect(groupingBy(s -> {
                    if (s.getTemperature() > 30) {
                        return "Hot";
                    } else if (s.getTemperature() >= 25) {
                        return "Moderate";
                    } else {
                        return "Cool";
                    }
                }));
        System.out.println("Grouped by temp:");
        gradeGroup.forEach((grade, list) -> {
            System.out.println(grade + ": " + list);
        });
        System.out.println();

        // 9 🧾 Summary Report (use DoubleSummaryStatistics) of all temperatures:
        DoubleSummaryStatistics stats = weathers.stream()
                .mapToDouble(CityWeather::getTemperature)
                .summaryStatistics();

        System.out.println("Temprature Stat:");
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Avg: " + stats.getAverage());
        System.out.println("Count: " + stats.getCount());

    }
}
