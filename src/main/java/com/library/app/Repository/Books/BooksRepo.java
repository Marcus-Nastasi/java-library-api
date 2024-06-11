package com.library.app.Repository.Books;

import com.library.app.Models.Books.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface BooksRepo extends JpaRepository<Book, BigInteger> {
}





