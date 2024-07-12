package com.library.app;

import com.library.app.DTOs.Books.NewBookDTO;
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
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

    @Mock
    private BooksRepo booksRepo;
    @InjectMocks
    private BookService bookService;

    @Test
    void newBookTest() {
        Book book = new Book("Author", "Name", 12.99, 1, BookStatus.STOCK, BookType.REGULAR, "unique", LocalDate.of(2024, 7, 15));
        NewBookDTO newBookDTO = new NewBookDTO("Author", "Name", 12.99, 1, BookStatus.STOCK, BookType.REGULAR, "unique", LocalDate.of(2024, 7, 15));

        when(booksRepo.save(any(Book.class))).thenReturn(book);

        assertDoesNotThrow(() -> {
            bookService.addNewBook(newBookDTO);
        });
    }
}



