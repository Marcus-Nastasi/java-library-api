package com.library.app.Repository.Books;

import com.library.app.Enums.Books.BookStatus;
import com.library.app.Enums.Books.BookType;
import com.library.app.Models.Books.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface BooksRepo extends JpaRepository<Book, BigInteger> {

    @Query(nativeQuery = true, value = "SELECT * FROM books WHERE(author=?1);")
    List<Book> findBooksByAuthor(String author);

    @Query(nativeQuery = true, value = "SELECT * FROM books WHERE(name=?1);")
    List<Book> findBooksByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM books WHERE(type=?1);")
    List<Book> findBooksByType(BookType type);

    @Query(nativeQuery = true, value = "SELECT * FROM books WHERE(status=?1);")
    List<Book> findBooksByStatus(BookStatus status);
}





