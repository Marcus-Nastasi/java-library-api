package com.library.app;

import com.library.app.DTOs.Books.NewBookDTO;
import com.library.app.DTOs.Books.UpdateBookDTO;
import com.library.app.Enums.Books.BookStatus;
import com.library.app.Enums.Books.BookType;
import com.library.app.Models.Books.Book;
import com.library.app.Repository.Books.BooksRepo;
import com.library.app.Service.Books.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

    @Mock
    private BooksRepo booksRepo;
    @InjectMocks
    private BookService bookService;

    @Test
    void newBookTest() {
        Book book = new Book("Author", "Name", 12.99, 1, "imgUrl", BookStatus.STOCK, BookType.REGULAR, "unique", LocalDate.of(2024, 7, 15));
        NewBookDTO newBookDTO = new NewBookDTO("Author", "Name", 12.99, 1, BookStatus.STOCK, BookType.REGULAR, "unique", LocalDate.of(2024, 7, 15));
        when(booksRepo.save(any(Book.class))).thenReturn(book);
        assertDoesNotThrow(() -> {
            bookService.addNewBook(newBookDTO);
        });
    }

    @Test
    void updateBookTest() {
        Book book = new Book("Author", "Name", 12.99, 1, BookStatus.STOCK, BookType.REGULAR, "unique", LocalDate.of(2024, 7, 15));
        UpdateBookDTO updateBookDTO = new UpdateBookDTO("Author", "Name", 12.99, 1, BookStatus.STOCK, BookType.REGULAR, "unique", LocalDate.of(2024, 7, 15));
        when(booksRepo.findById(any(BigInteger.class))).thenReturn(Optional.of(book));
        when(booksRepo.save(any(Book.class))).thenReturn(book);
        assertDoesNotThrow(() -> {
            bookService.updateBook(BigInteger.valueOf(2455), updateBookDTO);
        });
    }

    @Test
    void deleteBookTest() {
        Book book = new Book("Author", "Name", 12.99, 1, BookStatus.STOCK, BookType.REGULAR, "unique", LocalDate.of(2024, 7, 15));
        when(booksRepo.findById(BigInteger.valueOf(2500))).thenReturn(Optional.of(book));
        when(booksRepo.findById(BigInteger.valueOf(2501))).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> {
            bookService.deleteBook(BigInteger.valueOf(2500));
        });
        assertEquals("ok", bookService.deleteBook(BigInteger.valueOf(2500)));
        assertNull(bookService.deleteBook(BigInteger.valueOf(2501)));
        verify(booksRepo, times(2)).deleteById(BigInteger.valueOf(2500));
    }

    @Test
    void searchByNameTest() {
        Book book = new Book("Author", "Name", 12.99, 1, BookStatus.STOCK, BookType.REGULAR, "unique", LocalDate.of(2024, 7, 15));
        when(booksRepo.findBooksByName(any(String.class))).thenReturn(List.of(book));
        assertDoesNotThrow(() -> {
            bookService.searchByName("Name test");
        });
        assertEquals(List.of(book), bookService.searchByName("name"));
    }

    @Test
    void searchByAuthorTest() {
        Book book = new Book("Author", "Name", 12.99, 1, BookStatus.STOCK, BookType.REGULAR, "unique", LocalDate.of(2024, 7, 15));
        when(booksRepo.findBooksByAuthor(any(String.class))).thenReturn(List.of(book));
        assertDoesNotThrow(() -> {
            bookService.searchByAuthor("Name test");
        });
        assertEquals(List.of(book), bookService.searchByAuthor("name"));
    }
}



