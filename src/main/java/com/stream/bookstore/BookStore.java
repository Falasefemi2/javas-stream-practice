package com.stream.bookstore;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.toList;

public class BookStore {

    public static void main(String[] args) {

        List<Book> purchases = List.of(
                new Book("The Alchemist", "Paulo Coelho", "Fiction", 10.99, 5),
                new Book("1984", "George Orwell", "Dystopian", 8.99, 8),
                new Book("To Kill a Mockingbird", "Harper Lee", "Classic", 12.50, 3),
                new Book("Brave New World", "Aldous Huxley", "Dystopian", 9.99, 7),
                new Book("The Power of Now", "Eckhart Tolle", "Self-Help", 15.00, 2),
                new Book("Becoming", "Michelle Obama", "Biography", 18.00, 6),
                new Book("The Alchemist", "Paulo Coelho", "Fiction", 10.99, 4),
                new Book("Animal Farm", "George Orwell", "Dystopian", 7.99, 10),
                new Book("Sapiens", "Yuval Noah Harari", "History", 20.00, 5),
                new Book("Educated", "Tara Westover", "Biography", 17.00, 4)
        );

        // 1. Total revenue by genre
        Map<String, Double> totalRevenue = purchases.stream()
                .collect(Collectors.groupingBy(
                        Book::getGenre,
                        summingDouble(book -> book.getPrice() * book.getQuantity())
                ));
        totalRevenue.forEach((t, q) -> System.out.println(t + ": " + q));

        System.out.println();

        // 2. Top selling book(by quantity
        Book topSellingBook = purchases.stream()
                .max((o1, o2) -> Double.compare(o1.getQuantity(), o2.getQuantity()))
                .orElse(null);
        if (topSellingBook != null) {
            System.out.println("Top Selling book: " + topSellingBook);
        }

        System.out.println();

        // 3. Books sold more than 5 times
        List<Book> bookSoldeMoreThanFive = purchases.stream()
                .filter(b -> b.getQuantity() > 5)
                .collect(toList());
        bookSoldeMoreThanFive.forEach(System.out::println);

        System.out.println();

        // 4. Average book price by genre
        Map<String, Double> averagePrice = purchases.stream()
                .collect(groupingBy(Book::getGenre, averagingDouble(Book::getPrice)));
        averagePrice.forEach((a, b) -> System.out.println(a + ": " + b));

        System.out.println();

        // 5. Total purchase cost by author
        Map<String, Double> bookPurchaseByAuthor = purchases.stream()
                .collect(groupingBy(Book::getAuthor, summingDouble(b -> b.getPrice() * b.getQuantity())));
        bookPurchaseByAuthor.forEach((a, b) -> System.out.println(a + ": " + b));

        System.out.println();

        // 6. Group all books by genre into a list
        Map<String, List<Book>> groupedBook = purchases.stream()
                .collect(groupingBy(Book::getGenre));
        groupedBook.forEach((a, b) -> System.out.println(a + ": " + b));

        System.out.println();

        // 7. Total books purchased per author
        Map<String, Integer> amountOfBookPurchased = purchases.stream()
                .collect(groupingBy(Book::getAuthor, Collectors.summingInt(Book::getQuantity)));
        amountOfBookPurchased.forEach((author, count) -> System.out.println(author + ": " + count));

        System.out.println();

        // 8. Get a list of unique book titles sold
        Set<String> uniqueTitles = purchases.stream()
                .map(Book::getTitle)
                .collect(Collectors.toSet());

        uniqueTitles.forEach(System.out::println);

        System.out.println();

        // 9. Find the most expensive book
        Book mostExpensiveBook = purchases.stream()
                .max((b1, b2) -> Double.compare(b1.getPrice(), b2.getPrice()))
                .orElse(null);

        if (mostExpensiveBook != null) {
            System.out.println("Most expensive book: " + mostExpensiveBook);
        }

    }
}
