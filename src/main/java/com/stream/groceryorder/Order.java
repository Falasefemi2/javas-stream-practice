package com.stream.groceryorder;

public class Order {

    String name;
    String category;
    double price;
    int quantity;

    public Order(String category, String name, double price, int quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Order [name=" + name + ", category=" + category + ", price=" + price + ", quantity=" + quantity + "]";
    }
}
