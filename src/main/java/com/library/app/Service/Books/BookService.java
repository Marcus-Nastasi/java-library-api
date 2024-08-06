package com.library.app.Service.Books;

import com.amazonaws.services.s3.AmazonS3;
import com.library.app.DTOs.Books.NewBookDTO;
import com.library.app.DTOs.Books.UpdateBookDTO;
import com.library.app.Enums.Books.BookStatus;
import com.library.app.Enums.Books.BookType;
import com.library.app.Models.Books.Book;
import com.library.app.Repository.Books.BooksRepo;
import com.library.app.Util.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class BookService {

    // to-do: create books filters (already on repo)

    @Autowired
    private BooksRepo booksRepo;
    @Autowired
    private AmazonS3 s3Client;
    @Value("${aws.bucket.name}")
    private String bucketName;
    @Autowired
    private File file;

    public Book addNewBook(NewBookDTO data) {
        String imgUrl = null;
        if (data.image() != null) imgUrl = this.file.uploadImage(data.image());
        Book b = new Book(
            data.author(), data.name(), data.price(), data.quantity(), imgUrl, data.status(), data.type(), data.edition(), data.dateOfPublish()
        );
        booksRepo.save(b);
        return b;
    }

    public Book updateBook(BigInteger id, UpdateBookDTO data) {
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

    public List<Book> searchByName(String name) {
        return this.booksRepo.findBooksByName(name);
    }

    public List<Book> searchByAuthor(String author) {
        return booksRepo.findBooksByAuthor(author);
    }

    public List<Book> searchByType(BookType type) {
        if (type.equals(BookType.REGULAR)) return booksRepo.findBooksByType("0");
        if (type.equals(BookType.STUDY)) return booksRepo.findBooksByType("1");
        if (type.equals(BookType.JOURNAL)) return booksRepo.findBooksByType("2");
        if (type.equals(BookType.MAGAZINE)) return booksRepo.findBooksByType("3");
        return null;
    }

    public List<Book> searchByStatus(BookStatus status) {
        if (status.equals(BookStatus.STOCK)) return booksRepo.findBooksByType("0");
        if (status.equals(BookStatus.AVAILABLE)) return booksRepo.findBooksByType("1");
        if (status.equals(BookStatus.UNAVAILABLE)) return booksRepo.findBooksByType("2");
        return null;
    }
}


