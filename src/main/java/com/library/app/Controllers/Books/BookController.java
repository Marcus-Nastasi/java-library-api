package com.library.app.Controllers.Books;

import com.google.gson.Gson;
import com.library.app.DTOs.Books.*;
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
@CrossOrigin(origins = "http://localhost:3000, http://192.168.0.76:3000")
@RequestMapping(value = "/api/books")
public class BookController {

    @Autowired
    private BooksRepo booksRepo;
    @Autowired
    private BookService bookService;
    @Autowired
    private Gson gson;

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

    @PostMapping(value = "/search/name")
    public ResponseEntity<List<Book>> searchByName(@RequestBody @Valid SearchNameDTO data) {
        List<Book> books = bookService.searchByName(data.name());
        if (books.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(books);
    }

    @PostMapping(value = "/search/author")
    public ResponseEntity<List<Book>> searchByAuthor(@RequestBody @Valid SearchAuthorDTO data) {
        List<Book> books = bookService.searchByAuthor(data.author());
        if (books.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(books);
    }

    @PostMapping(value = "/search/type")
    public ResponseEntity<List<Book>> searchByType(@RequestBody @Valid SearchTypeDTO data) {
        BookType type = BookType.valueOf(data.type());
        List<Book> books = bookService.searchByType(type);
        if (books.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(books);
    }

    @PostMapping(value = "/search/status")
    public ResponseEntity<List<Book>> searchByStatus(@RequestBody @Valid SearchStatusDTO data) {
        BookStatus status = BookStatus.valueOf(data.status());
        List<Book> books = bookService.searchByStatus(status);
        if (books.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(books);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Book> register(@RequestParam("author") String author,
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
        if (b == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(b);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Book> update(@PathVariable BigInteger id, @RequestBody @Valid UpdateBookDTO data) {
        Book b = bookService.updateBook(id, data);
        if (b == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(b);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable BigInteger id) {
        boolean deletion = bookService.deleteBook(id);
        if (!deletion) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.accepted().build();
    }
}

