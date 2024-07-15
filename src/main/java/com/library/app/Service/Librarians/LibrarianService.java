package com.library.app.Service.Librarians;

import com.library.app.DTOs.Librarian.AddLibrarianDTO;
import com.library.app.DTOs.Librarian.UpdLibrarianDTO;
import com.library.app.Models.Librarian.Librarian;
import com.library.app.Repository.Librarian.LibrarianRepo;
import com.library.app.Service.Auth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LibrarianService {

    @Autowired
    private LibrarianRepo librarianRepo;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Librarian addNewLibrarian(AddLibrarianDTO data) {
        if (librarianRepo.findByCpf(data.cpf()) != null) return null;

        String passEncoded = passwordEncoder.encode(data.password());
        Librarian l = new Librarian(data.name(), data.cpf(), passEncoded);

        librarianRepo.save(l);

        return l;
    }

    public Librarian updateLibrarian(UpdLibrarianDTO data, String id, String token) throws RuntimeException {
        if (librarianRepo.findById(id).isEmpty()) return null;

        Librarian l = librarianRepo.findById(id).get();

        if (!tokenService.validate(token).equals(l.getCpf())) return null;
        if (!passwordEncoder.matches(data.oldPassword(), l.getPassword())) return null;

        l.setName(data.name());
        l.setCpf(data.cpf());
        l.setPassword(passwordEncoder.encode(data.newPassword()));

        librarianRepo.save(l);

        return l;
    }

    public String deleteLibrarian(String id) {
        if (librarianRepo.findById(id).isEmpty()) return null;

        Librarian l = librarianRepo.findById(id).get();

        librarianRepo.deleteById(id);

        return "ok";
    }
}




