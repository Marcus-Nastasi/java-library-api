package com.library.app.Service.Librarians;

import com.library.app.DTOs.Librarian.AddLibrarianDTO;
import com.library.app.Models.Librarian.Librarian;
import com.library.app.Repository.Librarian.LibrarianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LibrarianService {

    @Autowired
    private LibrarianRepo librarianRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Librarian addNewLibrarian(AddLibrarianDTO data) {
        String passEncoded = passwordEncoder.encode(data.password());
        Librarian l = new Librarian(data.name(), data.cpf(), passEncoded);
        librarianRepo.save(l);
        return l;
    }

    public Librarian updateLibrarian(AddLibrarianDTO data, String id) {
        Librarian l = librarianRepo.findById(id).orElseThrow();
        l.setName(data.name());
        l.setCpf(data.cpf());
        l.setPassword(passwordEncoder.encode(data.password()));
        librarianRepo.save(l);
        return l;
    }
}




