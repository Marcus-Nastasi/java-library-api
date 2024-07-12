package com.library.app.Controllers.Librarian;

import com.library.app.DTOs.Librarian.AddLibrarianDTO;
import com.library.app.DTOs.Librarian.UpdLibrarianDTO;
import com.library.app.Models.Librarian.Librarian;
import com.library.app.Repository.Librarian.LibrarianRepo;
import com.library.app.Service.Librarians.LibrarianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/librarian")
public class LibrarianController {

    @Autowired
    private LibrarianRepo librarianRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LibrarianService librarianService;

    @GetMapping(value = "/get")
    public ResponseEntity<List<Librarian>> getAll() {
        return ResponseEntity.ok(librarianRepo.findAll());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Librarian> getSingle(@PathVariable String id) {
        return ResponseEntity.ok(librarianRepo.findById(id).orElseThrow());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Librarian> add(@RequestBody @Valid AddLibrarianDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(librarianService.addNewLibrarian(data));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Librarian> update(@RequestBody @Valid UpdLibrarianDTO data, @PathVariable String id, @RequestHeader Map<String, String> header) {
        String token = header.get("Authorization").replace("Bearer ", "");
        Librarian l = librarianService.updateLibrarian(data, id, token);

        if (l == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(l);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> del(@PathVariable String id, @RequestHeader Map<String, String> header) {
        String token = header.get("Authorization").replace("Bearer ", "");

        if (librarianService.deleteLibrarian(id, token) == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        librarianService.deleteLibrarian(id, token);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}






