package com.library.app.Controllers.Librarian;

import com.library.app.DTOs.Librarian.AddLibrarianDTO;
import com.library.app.Models.Librarian.Librarian;
import com.library.app.Repository.Librarian.LibrarianRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/librarian")
public class LibrarianController {

    @Autowired
    private LibrarianRepo librarianRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/get/")
    public ResponseEntity<List<Librarian>> getAll() {
        return ResponseEntity.ok(librarianRepo.findAll());
    }

    @PostMapping(value = "/add/")
    public ResponseEntity<String> add(@RequestBody @Valid AddLibrarianDTO data) {
        String passEncoded = passwordEncoder.encode(data.password());
        Librarian l = new Librarian(data.name(), data.cpf(), passEncoded);
        librarianRepo.save(l);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}





