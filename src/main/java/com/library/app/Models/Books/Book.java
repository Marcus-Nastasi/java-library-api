package com.library.app.Models.Books;

import com.library.app.Enums.Books.BookStatus;
import com.library.app.Enums.Books.BookType;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column
    private String author;
    @Column
    private String name;
    @Column
    private double price;
    @Column
    private int quantity;
    @Column
    private String image_url;
    @Column
    private BookStatus status;
    @Column
    private BookType type;
    @Column
    private String edition;
    @Column
    private LocalDate dateOfPublish;

    public Book() {}

    public Book(String author, String name, double price, int quantity, String image_url, BookStatus status, BookType type, String edition, LocalDate dateOfPublish) {
        this.author = author;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image_url = image_url;
        this.status = status;
        this.type = type;
        this.edition = edition;
        this.dateOfPublish = dateOfPublish;
    }

    public Book(String author, String name, double price, int quantity, BookStatus status, BookType type, String edition, LocalDate dateOfPublish) {
        this.author = author;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.type = type;
        this.edition = edition;
        this.dateOfPublish = dateOfPublish;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public LocalDate getDateOfPublish() {
        return dateOfPublish;
    }

    public void setDateOfPublish(LocalDate dateOfPublish) {
        this.dateOfPublish = dateOfPublish;
    }
}





