package com.library.app.Controllers.Books;

import com.library.app.DTOs.Books.NewBookDTO;
import com.library.app.Models.Books.Book;
import com.library.app.Repository.Books.BooksRepo;
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

    @GetMapping(value = "/get/")
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(booksRepo.findAll());
    }

    @GetMapping(value = "/get/{id}/")
    public ResponseEntity<Book> getSingle(@PathVariable BigInteger id) {
        return ResponseEntity.ok(booksRepo.findById(id).orElseThrow());
    }

    @PostMapping(value = "/add/")
    public ResponseEntity<String> add(@RequestBody @Valid NewBookDTO data) {
        Book b = new Book(data.author(), data.name(), data.price(), data.quantity(), data.status(), data.type(), data.edition(), data.dateOfPublish());
        booksRepo.save(b);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/update/{id}/")
    public ResponseEntity<String> update(@PathVariable BigInteger id, @RequestBody @Valid NewBookDTO data) {
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
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/delete/{id}/")
    public ResponseEntity<String> delete(@PathVariable BigInteger id) {
        booksRepo.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}





