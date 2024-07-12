package com.library.app.Controllers.Books;

import com.library.app.DTOs.Books.NewBookDTO;
import com.library.app.Models.Books.Book;
import com.library.app.Repository.Books.BooksRepo;
import com.library.app.Service.Books.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {

    @Autowired
    private BooksRepo booksRepo;
    @Autowired
    private BookService bookService;

    @GetMapping(value = "/get")
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(booksRepo.findAll());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Book> getSingle(@PathVariable BigInteger id) {
        return ResponseEntity.ok(booksRepo.findById(id).orElseThrow());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Book> add(@RequestBody @Valid NewBookDTO data) {
        Book b = bookService.addNewBook(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(b);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Book> update(@PathVariable BigInteger id, @RequestBody @Valid NewBookDTO data) {
        Book b = bookService.updateBook(id, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(b);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable BigInteger id) {
        booksRepo.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}





