package com.library.app.DTOs.Books;

import com.library.app.Enums.Books.BookStatus;
import com.library.app.Enums.Books.BookType;

import java.time.LocalDate;

public record NewBookDTO(String author, String name, double price, int quantity, BookStatus status, BookType type, String edition, LocalDate dateOfPublish) {
}




