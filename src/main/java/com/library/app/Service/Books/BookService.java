package com.library.app.Service.Books;

import com.amazonaws.services.s3.AmazonS3;
import com.library.app.DTOs.Books.NewBookDTO;
import com.library.app.Models.Books.Book;
import com.library.app.Repository.Books.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Objects;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BooksRepo booksRepo;
    @Autowired
    private AmazonS3 s3Client;
    @Value("${aws.bucket.name}")
    private String bucketName;

    public Book addNewBook(NewBookDTO data) {
        String imgUrl = null;

        if (data.image() == null) return null;

        imgUrl = this.uploadImage(data.image());

        Book b = new Book(data.author(), data.name(), data.price(), data.quantity(), imgUrl, data.status(), data.type(), data.edition(), data.dateOfPublish());

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

    private String uploadImage(MultipartFile multipartFile) {
        String imgName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            File file = this.convertMultipartToFile(multipartFile);

            s3Client.putObject(bucketName, imgName, file);
            file.delete();

            return s3Client.getUrl(bucketName, imgName).toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "";
    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File convert = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convert);

        fos.write(multipartFile.getBytes());
        fos.close();

        return convert;
    }
}



