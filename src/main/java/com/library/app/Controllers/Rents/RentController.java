package com.library.app.Controllers.Rents;

import com.library.app.DTOs.Rents.NewRentDTO;
import com.library.app.Models.Rents.Rent;
import com.library.app.Repository.Rents.RentsRepo;
import com.library.app.Service.Rents.RentsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/api/rents")
public class RentController {

    @Autowired
    private RentsRepo rentsRepo;
    @Autowired
    private RentsService rentsService;

    @GetMapping(value = "/get")
    public ResponseEntity<List<Rent>> getAll() {
        return ResponseEntity.ok(rentsRepo.findAll());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Rent> getSingle(@PathVariable BigInteger id) {
        return ResponseEntity.ok(rentsRepo.findById(id).orElseThrow());
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Rent> newRent(@RequestBody @Valid NewRentDTO data) {
        Rent r = rentsService.addNewRent(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> update(@PathVariable BigInteger id, @RequestBody @Valid NewRentDTO data) {
        Rent r = rentsRepo.findById(id).orElseThrow();
        r.setBook_id(data.book_id());
        r.setLibrarian_id(data.librarian_id());
        r.setMember_id(data.member_id());
        rentsRepo.save(r);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable BigInteger id) {
        rentsRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}




