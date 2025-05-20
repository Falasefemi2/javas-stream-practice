package com.stream.bookstore;

public class Book {

    private String title;
    private String author;
    private String genre;
    private double price;
    private int quantity;

    public Book(String title, String author, String genre, double price, int quantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book{");
        sb.append("title=").append(title);
        sb.append(", author=").append(author);
        sb.append(", genre=").append(genre);
        sb.append(", price=").append(price);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }

}
