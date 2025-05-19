package com.stream.groceryorder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Shopping {

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Apple", "Fruit", 2.5, 10),
                new Order("Apple", "Fruit", 5.5, 30),
                new Order("Milk", "Dairy", 3.0, 2),
                new Order("Milk", "Dairy", 7.0, 5),
                new Order("Bread", "Bakery", 1.5, 4),
                new Order("Cheese", "Dairy", 12.0, 1),
                new Order("Banana", "Fruit", 1.0, 7),
                new Order("Cake", "Bakery", 15.0, 1)
        );

        Map<String, Double> totalPriceByCategory = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCategory,
                        Collectors.summingDouble(Order::getPrice)
                ));

        totalPriceByCategory.forEach((category, total)
                -> System.out.println(category + "\t" + total)
        );

    }
}
