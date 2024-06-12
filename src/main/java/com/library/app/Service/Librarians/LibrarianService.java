package com.library.app.Service.Librarians;

import com.library.app.Repository.Books.BooksRepo;
import com.library.app.Repository.Librarian.LibrarianRepo;
import com.library.app.Repository.Members.MembersRepo;
import com.library.app.Repository.Rents.RentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibrarianService {

    @Autowired
    private LibrarianRepo librarianRepo;
    @Autowired
    private RentsRepo rentsRepo;
    @Autowired
    private BooksRepo booksRepo;
    @Autowired
    private MembersRepo membersRepo;

    // to-do: business rules implementation.

    // to-do: librarian will add rents and updating book status

    // add rents
    public void addRent() {

    }
}




