package com.library.app.Controllers.Librarian;

import com.google.gson.Gson;
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
import org.springframework.web.ErrorResponse;
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
    @Autowired
    private Gson gson;

    @GetMapping(value = "/get")
    public ResponseEntity<List<Librarian>> getAll() {
        if (librarianRepo.findAll().isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(librarianRepo.findAll());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Librarian> getSingle(@PathVariable String id) {
        if (librarianRepo.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok(librarianRepo.findById(id).get());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> add(@RequestBody @Valid AddLibrarianDTO data) {
        Librarian l = librarianService.addNewLibrarian(data);

        if (l == null) return ResponseEntity.status(HttpStatus.CONFLICT)
            .header("Content-Type", "application/json")
                .body(gson.toJson("{\"data\": [\"error\": \"librarian already registered\"]}"));

        return ResponseEntity.status(HttpStatus.CREATED).body(gson.toJson(l));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Librarian> update(@RequestBody @Valid UpdLibrarianDTO data, @PathVariable String id, @RequestHeader Map<String, String> header) {
        String token = header.get("authorization").replace("Bearer ", "");
        Librarian l = librarianService.updateLibrarian(data, id, token);

        if (l == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(l);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> del(@PathVariable String id) {
        String deletion = librarianService.deleteLibrarian(id);

        if (deletion == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}






