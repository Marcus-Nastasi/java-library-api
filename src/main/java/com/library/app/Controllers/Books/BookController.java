package com.library.app.Controllers.Books;

import com.library.app.DTOs.Books.NewBookDTO;
import com.library.app.Enums.Books.BookStatus;
import com.library.app.Enums.Books.BookType;
import com.library.app.Models.Books.Book;
import com.library.app.Repository.Books.BooksRepo;
import com.library.app.Service.Books.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.time.LocalDate;
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
        if (booksRepo.findAll().isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(booksRepo.findAll());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Book> getSingle(@PathVariable BigInteger id) {
        if (booksRepo.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(booksRepo.findById(id).orElseThrow());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Book> add(@RequestParam("author") String author,
                                    @RequestParam("name") String name,
                                    @RequestParam("price") double price,
                                    @RequestParam("quantity") int quantity,
                                    @RequestParam("status") BookStatus status,
                                    @RequestParam("type") BookType type,
                                    @RequestParam("edition") String edition,
                                    @RequestParam("dateOfPublish") LocalDate dateOfPublish,
                                    @RequestParam("image") MultipartFile image) {
        NewBookDTO data = new NewBookDTO(author, name, price, quantity, status, type, edition, dateOfPublish, image);
        Book b = bookService.addNewBook(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(b);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Book> update(@PathVariable BigInteger id, @RequestBody @Valid NewBookDTO data) {
        Book b = bookService.updateBook(id, data);
        if (b == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(b);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable BigInteger id) {
        String deletion = bookService.deleteBook(id);
        if (deletion == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.accepted().build();
    }
}





