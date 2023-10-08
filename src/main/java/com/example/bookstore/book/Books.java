package com.example.bookstore.book;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Books {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "book_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String title;
    private String author;
    private int numPages;
    private LocalDate publicationDate;
    private double price;
    private String description;
    private String category;
    private int quantity;

    public Books() {
    }

    public Books(Long id, String title, String author, int numPages, LocalDate publicationDate, double price, String description, String category, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.numPages = numPages;
        this.publicationDate = publicationDate;
        this.price = price;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
    }

    public Books(String title, String author, int numPages, LocalDate publicationDate, double price, String description, String category, int quantity) {
        this.title = title;
        this.author = author;
        this.numPages = numPages;
        this.publicationDate = publicationDate;
        this.price = price;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", numPages=" + numPages +
                ", publicationDate=" + publicationDate +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
