package com.stream.groceryorder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

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

        // ✅ 1. Group by category and sum total value (price × quantity)
        System.out.println("Total value per category:");
        Map<String, Double> totalPriceByCategory = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCategory,
                        summingDouble(order -> order.getPrice() * order.getQuantity())
                ));

        // ✅ 2. Filter items with price > 10
        System.out.println("Items with unit price > 10:");
        totalPriceByCategory.forEach((category, total)
                -> System.out.println(category + "\t" + total)
        );
        System.out.println();

        // ✅ 3. Sort by total cost (price × quantity) descending
        System.out.println("Sorted by total cost (price × quantity):");
        List<Order> priceGreaterThan10 = orders.stream()
                .filter(p -> p.getPrice() > 10)
                .collect(toList());
        priceGreaterThan10.forEach(System.out::println);
        System.out.println();

        // ✅ 4. Items bought more than 5 times
        System.out.println("Items bought more than 5 times:");
        List<Order> sortedByTotalCost = orders.stream()
                .sorted((o1, o2) -> Double.compare(
                o2.getPrice() * o2.getQuantity(),
                o1.getPrice() * o1.getQuantity()
        ))
                .collect(toList());
        sortedByTotalCost.forEach(System.out::println);
        System.out.println();

        // ✅ 5. Average item price per category
        System.out.println("Average unit price per category:");
        List<Order> boughtFiveTimes = orders.stream()
                .filter(q -> q.getQuantity() > 5)
                .collect(toList());
        boughtFiveTimes.forEach(System.out::println);
        System.out.println();

        // Total quantity bought per product name
        Map<String, Double> averageItem = orders.stream()
                .collect(groupingBy(Order::getCategory, averagingDouble(Order::getPrice)));
        averageItem.forEach((a, b) -> System.err.println(a + ": " + b));
        System.out.println();

        Map<String, Integer> quantityPerName = orders.stream()
                .collect(groupingBy(Order::getName, summingInt(Order::getQuantity)));
        quantityPerName.forEach((a, b) -> System.out.println(a + ": " + b));
        System.out.println();

        // Find most expensive item (by unit price
        Order mostExpensive = orders.stream()
                .max((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()))
                .orElse(null);

        if (mostExpensive != null) {
            System.out.println("Most expensive item: " + mostExpensive);
        }
        System.out.println();

        //  Group by category where quantity > 3
        Map<String, List<Order>> filteredByQuantity = orders.stream()
                .filter(order -> order.getQuantity() > 3)
                .collect(Collectors.groupingBy(Order::getCategory));
        System.out.println("Orders with quantity > 3 grouped by category:");
        filteredByQuantity.forEach((category, items) -> {
            System.out.println(category + ": " + items);
        });

    }
}
