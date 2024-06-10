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

    @GetMapping(value = "/get/{id}/")
    public ResponseEntity<Librarian> getSingle(@PathVariable String id) {
        return ResponseEntity.ok(librarianRepo.findById(id).orElseThrow());
    }

    @PostMapping(value = "/add/")
    public ResponseEntity<String> add(@RequestBody @Valid AddLibrarianDTO data) {
        String passEncoded = passwordEncoder.encode(data.password());
        Librarian l = new Librarian(data.name(), data.cpf(), passEncoded);
        librarianRepo.save(l);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/update/{id}/")
    public ResponseEntity<String> update(@RequestBody @Valid AddLibrarianDTO data, @PathVariable String id) {
        Librarian l = librarianRepo.findById(id).orElseThrow();
        l.setName(data.name());
        l.setCpf(data.cpf());
        l.setPassword(passwordEncoder.encode(data.password()));
        librarianRepo.save(l);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/delete/{id}/")
    public ResponseEntity<String> del(@PathVariable String id) {
        librarianRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}






