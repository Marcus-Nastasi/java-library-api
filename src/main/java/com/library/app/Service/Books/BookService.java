package com.library.app.Service.Books;

import com.library.app.DTOs.Books.NewBookDTO;
import com.library.app.Models.Books.Book;
import com.library.app.Repository.Books.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class BookService {

    @Autowired
    private BooksRepo booksRepo;

    public Book addNewBook(NewBookDTO data) {
        Book b = new Book(data.author(), data.name(), data.price(), data.quantity(), data.status(), data.type(), data.edition(), data.dateOfPublish());
        booksRepo.save(b);
        return b;
    }

    public Book updateBook(BigInteger id, NewBookDTO data) {
        if (booksRepo.findById(id).isEmpty()) return null;

        Book b = booksRepo.findById(id).orElseThrow();

        b.setAuthor(data.author());
        b.setEdition(data.edition());
        b.setName(data.name());
        b.setPrice(data.price());
        b.setQuantity(data.quantity());
        b.setDateOfPublish(data.dateOfPublish());
        b.setStatus(data.status());
        b.setType(data.type());
        booksRepo.save(b);

        return b;
    }

    public String deleteBook(BigInteger id) {
        if (booksRepo.findById(id).isEmpty()) return null;
        booksRepo.deleteById(id);
        return "ok";
    }
}



