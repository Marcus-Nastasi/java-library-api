package com.library.app.DTOs.Books;

import com.library.app.Enums.Books.BookStatus;
import com.library.app.Enums.Books.BookType;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record NewBookDTO(String author, String name, double price, int quantity, BookStatus status, BookType type, String edition, LocalDate dateOfPublish, MultipartFile image) {

    // Construtor principal
    public NewBookDTO(String author, String name, double price, int quantity, BookStatus status, BookType type, String edition, LocalDate dateOfPublish, MultipartFile image) {
        this.author = author;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.type = type;
        this.edition = edition;
        this.dateOfPublish = dateOfPublish;
        this.image = image;
    }

    // Construtor alternativo sem o campo 'image'
    public NewBookDTO(String author, String name, double price, int quantity, BookStatus status, BookType type, String edition, LocalDate dateOfPublish) {
        this(author, name, price, quantity, status, type, edition, dateOfPublish, null);
    }
}





